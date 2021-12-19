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
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;

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

        final VoxelShape verticalPost = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        final VoxelShape nsPost = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);
        final VoxelShape ewPost = Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

        final double apothem = 0.25D;
        final VoxelShape[] sides = new VoxelShape[FACING_VALUES.length];

        for (int i = 0; i < FACING_VALUES.length; ++i) {
            final Direction direction = FACING_VALUES[i];
            sides[i] = VoxelShapes.box(0.5D + Math.min(-apothem, (double) direction.getStepX() * 0.5D),
                0.5D + Math.min(-apothem, (double) direction.getStepY() * 0.5D),
                0.5D + Math.min(-apothem, (double) direction.getStepZ() * 0.5D),
                0.5D + Math.max(apothem, (double) direction.getStepX() * 0.5D),
                0.5D + Math.max(apothem, (double) direction.getStepY() * 0.5D),
                0.5D + Math.max(apothem, (double) direction.getStepZ() * 0.5D));
        }

        VERTICAL_AABBS = createShapes(verticalPost, sides, Direction.Axis.Y);
        NS_AABBS = createShapes(nsPost, sides, Direction.Axis.Z);
        EW_AABBS = createShapes(ewPost, sides, Direction.Axis.X);
    }

    private final IWoodType woodType;

    public WoodenStrippedPostBlock(final IWoodType woodType) {
        super(Block.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD));
        this.woodType = woodType;
        this.registerDefaultState(this
            .defaultBlockState()
            .setValue(AXIS, Direction.Axis.Y)
            .setValue(WATERLOGGED, false)
            .setValue(NORTH, false)
            .setValue(EAST, false)
            .setValue(SOUTH, false)
            .setValue(WEST, false)
            .setValue(UP, false)
            .setValue(DOWN, false));
    }

    private static VoxelShape[] createShapes(final VoxelShape post, final VoxelShape[] sides,
                                             final Direction.Axis axis) {
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
        final Direction.Axis axis = blockState.getValue(AXIS);
        for (final Direction direction : FACING_VALUES) {
            if (axis != direction.getAxis()) {
                if (blockState.getValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(direction))) {
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
    protected void createBlockStateDefinition(@Nonnull final StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public VoxelShape getShape(@Nonnull final BlockState blockState, @Nonnull final IBlockReader blockReader,
                               @Nonnull final BlockPos blockPos, @Nonnull final ISelectionContext selectionContext) {
        return getVoxelShape(blockState);
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public VoxelShape getCollisionShape(@Nonnull final BlockState blockState, @Nonnull final IBlockReader blockReader,
                                        @Nonnull final BlockPos blockPos,
                                        @Nonnull final ISelectionContext selectionContext) {
        return getVoxelShape(blockState);
    }

    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext blockItemUseContext) {
        final IBlockReader blockReader = blockItemUseContext.getLevel();
        final BlockPos blockPos = blockItemUseContext.getClickedPos();

        final Block down = blockReader.getBlockState(blockPos.below()).getBlock();
        final Block up = blockReader.getBlockState(blockPos.above()).getBlock();
        final Block north = blockReader.getBlockState(blockPos.north()).getBlock();
        final Block east = blockReader.getBlockState(blockPos.east()).getBlock();
        final Block south = blockReader.getBlockState(blockPos.south()).getBlock();
        final Block west = blockReader.getBlockState(blockPos.west()).getBlock();

        final FluidState fluidState = blockItemUseContext.getLevel().getFluidState(blockItemUseContext.getClickedPos());

        final Direction.Axis axis = blockItemUseContext.getClickedFace().getAxis();
        return this
            .defaultBlockState()
            .setValue(AXIS, blockItemUseContext.getClickedFace().getAxis())
            .setValue(DOWN, down instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Y)
            .setValue(UP, up instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Y)
            .setValue(NORTH, north instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Z)
            .setValue(EAST, east instanceof WoodenStrippedPostBlock && axis != Direction.Axis.X)
            .setValue(SOUTH, south instanceof WoodenStrippedPostBlock && axis != Direction.Axis.Z)
            .setValue(WEST, west instanceof WoodenStrippedPostBlock && axis != Direction.Axis.X)
            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public BlockState updateShape(final BlockState blockState0, @Nonnull final Direction direction,
                                  final BlockState blockState1, @Nonnull final IWorld world,
                                  @Nonnull final BlockPos blockPos, @Nonnull final BlockPos blockPos1) {
        return blockState0.setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(direction),
            blockState1.getBlock() instanceof WoodenStrippedPostBlock &&
            direction.getAxis() != blockState0.getValue(AXIS));
    }
}
