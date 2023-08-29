package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class WoodenSingleDresserBlock extends HorizontalDirectionalBlock implements IWooden, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final Map<Direction, VoxelShape> SHAPES;

    static {
        final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);

        final VoxelShape shape = Shapes.or(Block.box(12, 0, 2, 16, 13, 16),
            Block.box(0, 0, 2, 4, 13, 16),
            Block.box(4, 0, 3, 12, 13, 16),
            Block.box(3, 7, 1, 13, 12, 3),
            Block.box(3, 1, 1, 13, 6, 3),
            Block.box(0, 13, 0, 16, 16, 16),
            Block.box(7, 9, 0.5, 9, 10, 1.5),
            Block.box(7, 3, 0.5, 9, 4, 1.5));

        Direction.Plane.HORIZONTAL.stream().forEach(direction -> shapes.put(direction, Util.rotateShape(Direction.NORTH, direction, shape)));

        SHAPES = Collections.unmodifiableMap(shapes);
    }

    private final IWoodType woodType;

    public WoodenSingleDresserBlock(final IWoodType woodType) {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(2.0F));

        this.woodType = woodType;
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull final BlockPlaceContext context) {
        final FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter getter,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
