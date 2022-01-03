package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenChairEntity;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class WoodenChairBlock extends HorizontalDirectionalBlock implements IWooden, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final Map<Direction, VoxelShape> SHAPES;

    static {
        final Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);

        final VoxelShape seat = Block.box(3.0, 6.0, 3.0, 13.0, 8.0, 13.0);

        final VoxelShape frontLeftLeg = Block.box(11.0, 0.0, 3.0, 13.0, 6.0, 5.0);
        final VoxelShape frontRightLeg = Block.box(3.0, 0.0, 3.0, 5.0, 6.0, 5.0);
        final VoxelShape rearLeftLeg = Block.box(11.0, 0.0, 11.0, 13.0, 16.0, 13.0);
        final VoxelShape rearRightLeg = Block.box(3.0, 0.0, 11.0, 5.0, 16.0, 13.0);

        final VoxelShape westCrossBeam = Block.box(11.5, 1.0, 5.0, 12.5, 3.0, 11.0);
        final VoxelShape eastCrossBeam = Block.box(3.5, 1.0, 5.0, 4.5, 3.0, 11.0);
        final VoxelShape southCrossBeam = Block.box(5.0, 1.0, 3.5, 11.0, 3.0, 4.5);
        final VoxelShape northCrossBeam = Block.box(5.0, 1.0, 11.5, 11.0, 3.0, 12.5);

        final VoxelShape lowerBackBeam = Block.box(5.0, 10.0, 11.5, 11.0, 12, 12.5);
        final VoxelShape upperBackBeam = Block.box(5.0, 13.0, 11.5, 11.0, 15.0, 12.5);

        final VoxelShape shape = Shapes.or(seat,
            frontLeftLeg,
            frontRightLeg,
            rearLeftLeg,
            rearRightLeg,
            westCrossBeam,
            eastCrossBeam,
            southCrossBeam,
            northCrossBeam,
            lowerBackBeam,
            upperBackBeam);

        Direction.Plane.HORIZONTAL
            .stream()
            .forEach(direction -> shapes.put(direction, Util.rotateShape(Direction.NORTH, direction, shape)));

        SHAPES = Collections.unmodifiableMap(shapes);
    }

    private final IWoodType woodType;

    public WoodenChairBlock(final IWoodType woodType) {
        super(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F));
        this.woodType = woodType;
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull final BlockPlaceContext context) {
        final FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this
            .defaultBlockState()
            .setValue(FACING, context.getHorizontalDirection().getOpposite())
            .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter getter,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull final BlockState state, @Nonnull final Level level,
                                 @Nonnull final BlockPos pos, @Nonnull final Player player,
                                 @Nonnull final InteractionHand hand, @Nonnull final BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            final List<WoodenChairEntity> entities = level.getEntitiesOfClass(WoodenChairEntity.class, new AABB(pos));
            if (entities.isEmpty()) {
                final WoodenChairEntity entity =
                    new WoodenChairEntity(ILikeWood.ENTITY_TYPE_REGISTRY.getObject(this.getWoodType(),
                        WoodenEntityType.CHAIR), level);
                entity.setPos(pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
                level.addFreshEntity(entity);
                player.startRiding(entity);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
