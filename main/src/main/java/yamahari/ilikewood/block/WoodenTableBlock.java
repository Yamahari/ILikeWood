package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public final class WoodenTableBlock extends WoodenBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final List<VoxelShape> SHAPES;

    static {
        final VoxelShape top = Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);

        final VoxelShape nwLeg = Block.box(0.0, 0.0, 0.0, 2.0, 13.0, 2.0);
        final VoxelShape neLeg = Block.box(14.0, 0.0, 0.0, 16.0, 13.0, 2.0);
        final VoxelShape seLeg = Block.box(14.0, 0.0, 14.0, 16.0, 13.0, 16.0);
        final VoxelShape swLeg = Block.box(0.0, 0.0, 14.0, 2.0, 13.0, 16.0);

        final VoxelShape nBeam = Block.box(0.0, 10.0, 0.5, 16.0, 12.0, 1.5);
        final VoxelShape eBeam = Block.box(14.5, 10.0, 0.0, 15.5, 12.0, 16.0);
        final VoxelShape sBeam = Block.box(0.0, 10.0, 14.5, 16.0, 12.0, 15.5);
        final VoxelShape wBeam = Block.box(0.5, 10.0, 0.0, 1.5, 12.0, 16.0);

        final VoxelShape shape1 = Shapes.or(top, neLeg, seLeg, wBeam);
        final VoxelShape shape2 = Util.rotateShape(Direction.NORTH, Direction.EAST, shape1);
        final VoxelShape shape3 = Util.rotateShape(Direction.NORTH, Direction.SOUTH, shape1);
        final VoxelShape shape4 = Util.rotateShape(Direction.NORTH, Direction.WEST, shape1);
        final VoxelShape shape5 = Shapes.or(top, neLeg, swLeg, sBeam, wBeam);
        final VoxelShape shape6 = Util.rotateShape(Direction.NORTH, Direction.EAST, shape5);
        final VoxelShape shape7 = Util.rotateShape(Direction.NORTH, Direction.SOUTH, shape5);
        final VoxelShape shape8 = Util.rotateShape(Direction.NORTH, Direction.WEST, shape5);
        final VoxelShape shape9 = Shapes.or(top, eBeam, wBeam);
        final VoxelShape shape10 = Shapes.or(top, nBeam, sBeam);
        final VoxelShape shape11 = Shapes.or(top, seLeg, swLeg, eBeam, wBeam, sBeam);
        final VoxelShape shape12 = Util.rotateShape(Direction.NORTH, Direction.EAST, shape11);
        final VoxelShape shape13 = Util.rotateShape(Direction.NORTH, Direction.SOUTH, shape11);
        final VoxelShape shape14 = Util.rotateShape(Direction.NORTH, Direction.WEST, shape11);
        final VoxelShape shape15 = Shapes.or(top, nwLeg, neLeg, seLeg, swLeg, nBeam, eBeam, sBeam, wBeam);

        final List<VoxelShape> shapes = List.of(shape15,
            shape11,
            shape12,
            shape5,
            shape13,
            shape9,
            shape6,
            shape1,
            shape14,
            shape8,
            shape10,
            shape4,
            shape7,
            shape3,
            shape2,
            top);

        SHAPES = Collections.unmodifiableList(shapes);
    }

    public WoodenTableBlock(final IWoodType type) {
        super(type, BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F));
        this.registerDefaultState(this
            .defaultBlockState()
            .setValue(NORTH, false)
            .setValue(EAST, false)
            .setValue(SOUTH, false)
            .setValue(WEST, false)
            .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, WATERLOGGED);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter getter,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        final boolean n = state.getValue(NORTH);
        final boolean e = state.getValue(EAST);
        final boolean s = state.getValue(SOUTH);
        final boolean w = state.getValue(WEST);

        final int index = (n ? 1 : 0) | ((e ? 1 : 0) << 1) | ((s ? 1 : 0) << 2) | ((w ? 1 : 0) << 3);

        return SHAPES.get(index);
    }

    @Nonnull
    @Override
    public BlockState updateShape(@Nonnull final BlockState state, @Nonnull final Direction direction,
                                  @Nonnull final BlockState facingState, @Nonnull final LevelAccessor accessor,
                                  @Nonnull final BlockPos pos, @Nonnull final BlockPos facingPos) {
        if (direction.getAxis().isHorizontal()) {
            return state.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), facingState.is(this));
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull final BlockPlaceContext context) {
        final BlockPos clickedPos = context.getClickedPos();
        final Level level = context.getLevel();
        final FluidState fluidstate = level.getFluidState(clickedPos);

        return this
            .defaultBlockState()
            .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
            .setValue(NORTH, level.getBlockState(clickedPos.north()).is(this))
            .setValue(EAST, level.getBlockState(clickedPos.east()).is(this))
            .setValue(SOUTH, level.getBlockState(clickedPos.south()).is(this))
            .setValue(WEST, level.getBlockState(clickedPos.west()).is(this));
    }
}
