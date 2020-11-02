package yamahari.ilikewood.block.post;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenStrippedPostBlock extends RotatedPillarBlock implements IWooden, IWaterLoggable {
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty WATERLOGGED;
    private static final Direction[] FACING_VALUES = Direction.values();

    private static final VoxelShape[] VERTICAL_AABBS;
    private static final VoxelShape[] NS_AABBS;
    private static final VoxelShape[] EW_AABBS;

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        NORTH = BlockStateProperties.NORTH;
        EAST = BlockStateProperties.EAST;
        SOUTH = BlockStateProperties.SOUTH;
        WEST = BlockStateProperties.WEST;
        UP = BlockStateProperties.UP;
        DOWN = BlockStateProperties.DOWN;

        final VoxelShape verticalPost = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        final VoxelShape nsPost = Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);
        final VoxelShape ewPost = Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

        final double apothem = 0.25D;
        final VoxelShape[] sides = new VoxelShape[FACING_VALUES.length];

        for (int i = 0; i < FACING_VALUES.length; ++i) {
            final Direction direction = FACING_VALUES[i];
            sides[i] = VoxelShapes.create(
                    0.5D + Math.min(-apothem, (double) direction.getXOffset() * 0.5D),
                    0.5D + Math.min(-apothem, (double) direction.getYOffset() * 0.5D),
                    0.5D + Math.min(-apothem, (double) direction.getZOffset() * 0.5D),
                    0.5D + Math.max(apothem, (double) direction.getXOffset() * 0.5D),
                    0.5D + Math.max(apothem, (double) direction.getYOffset() * 0.5D),
                    0.5D + Math.max(apothem, (double) direction.getZOffset() * 0.5D)
            );
        }

        VERTICAL_AABBS = createShapes(verticalPost, sides, Direction.Axis.Y);
        NS_AABBS = createShapes(nsPost, sides, Direction.Axis.Z);
        EW_AABBS = createShapes(ewPost, sides, Direction.Axis.X);
    }

    private final IWoodType woodType;

    public WoodenStrippedPostBlock(final IWoodType woodType) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
        this.woodType = woodType;
        this.setDefaultState(this.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    private static VoxelShape[] createShapes(final VoxelShape post, final VoxelShape[] sides, final Direction.Axis axis) {
        final VoxelShape[] shapes = new VoxelShape[16];
        for (int i = 0; i < shapes.length; ++i) {
            int offset = 0;
            VoxelShape shape = post;
            for (int j = 0; j < FACING_VALUES.length; ++j) {
                if (FACING_VALUES[j].getAxis() != axis) {
                    if ((i & 1 << offset) != 0) {
                        shape = VoxelShapes.or(shape, sides[j]);
                    }
                    ++offset;
                }
            }
            shapes[i] = shape;
        }
        return shapes;
    }

    private static VoxelShape getVoxelShape(final BlockState blockState) {
        int i = 0;
        int offset = 0;
        final Direction.Axis axis = blockState.get(AXIS);
        for (final Direction direction : FACING_VALUES) {
            if (axis != direction.getAxis()) {
                if (blockState.get(SixWayBlock.FACING_TO_PROPERTY_MAP.get(direction))) {
                    i |= 1 << offset;
                }
                ++offset;
            }
        }
        switch (axis) {
            case X:
            default:
                return EW_AABBS[i];
            case Z:
                return NS_AABBS[i];
            case Y:
                return VERTICAL_AABBS[i];
        }
    }

    @Override
    protected void fillStateContainer(@SuppressWarnings("NullableProblems") final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public FluidState getFluidState(final BlockState blockState) {
        return blockState.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(blockState);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public VoxelShape getShape(final BlockState blockState, final IBlockReader blockReader, final BlockPos blockPos, final ISelectionContext selectionContext) {
        return getVoxelShape(blockState);
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public VoxelShape getCollisionShape(final BlockState blockState, final IBlockReader blockReader, final BlockPos blockPos, final ISelectionContext selectionContext) {
        return getVoxelShape(blockState);
    }

    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext blockItemUseContext) {
        final IBlockReader blockReader = blockItemUseContext.getWorld();
        final BlockPos blockPos = blockItemUseContext.getPos();

        final Block down = blockReader.getBlockState(blockPos.down()).getBlock();
        final Block up = blockReader.getBlockState(blockPos.up()).getBlock();
        final Block north = blockReader.getBlockState(blockPos.north()).getBlock();
        final Block east = blockReader.getBlockState(blockPos.east()).getBlock();
        final Block south = blockReader.getBlockState(blockPos.south()).getBlock();
        final Block west = blockReader.getBlockState(blockPos.west()).getBlock();

        final FluidState fluidState = blockItemUseContext.getWorld().getFluidState(blockItemUseContext.getPos());

        final Direction.Axis axis = blockItemUseContext.getFace().getAxis();
        return this.getDefaultState().with(AXIS, blockItemUseContext.getFace().getAxis())
                .with(DOWN, down instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Y)
                .with(UP, up instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Y)
                .with(NORTH, north instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Z)
                .with(EAST, east instanceof WoodenStrippedPostBlock && axis != Direction.Axis.X)
                .with(SOUTH, south instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Z)
                .with(WEST, west instanceof WoodenStrippedPostBlock && axis != Direction.Axis.X)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public BlockState updatePostPlacement(final BlockState blockState0, final Direction direction, final BlockState blockState1, final IWorld world, final BlockPos blockPos, final BlockPos blockPos1) {
        return blockState0.with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(direction),
                blockState1.getBlock() instanceof WoodenStrippedPostBlock && direction.getAxis() != blockState0.get(AXIS));
    }
}
