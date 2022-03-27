package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.container.WoodenSawmillContainer;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenSawmillBlock extends WoodenBlock {
    public static final EnumProperty<WoodenSawmillModel> MODEL = EnumProperty.create("model", WoodenSawmillModel.class);
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final Map<WoodenSawmillModel, Map<Direction, VoxelShape>> SHAPES;

    static {
        final Map<WoodenSawmillModel, Map<Direction, VoxelShape>> shapes = new EnumMap<>(WoodenSawmillModel.class);
        final Map<Direction, VoxelShape> bottomLeftShapes = new EnumMap<>(Direction.class);
        final Map<Direction, VoxelShape> bottomRightShapes = new EnumMap<>(Direction.class);
        final Map<Direction, VoxelShape> topLeftShapes = new EnumMap<>(Direction.class);
        final Map<Direction, VoxelShape> topRightShapes = new EnumMap<>(Direction.class);

        final VoxelShape tableTop = Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);

        final VoxelShape legNW = Block.box(1.0, 0.0, 1.0, 3.0, 13.0, 3.0);
        final VoxelShape legSw = Block.box(1.0, 0.0, 13.0, 3.0, 13.0, 15.0);
        final VoxelShape legNe = Block.box(13.0, 0.0, 1.0, 15.0, 13.0, 3.0);
        final VoxelShape legSe = Block.box(13.0, 0.0, 13.0, 15.0, 13.0, 15.0);

        final VoxelShape crossBeamLeftN = Block.box(3.0, 10.0, 1.5, 16.0, 12.0, 2.5);
        final VoxelShape crossBeamLeftS = Block.box(3.0, 10.0, 13.5, 16.0, 12.0, 14.5);
        final VoxelShape crossBeamLeftW = Block.box(1.5, 2.0, 3.0, 2.5, 4.0, 13.0);

        final VoxelShape crossBeamRightN = Block.box(0.0, 10.0, 1.5, 13.0, 12.0, 2.5);
        final VoxelShape crossBeamRightS = Block.box(0.0, 10.0, 13.5, 13.0, 12.0, 14.5);
        final VoxelShape crossBeamRightE = Block.box(13.5, 2.0, 3.0, 14.5, 4.0, 13.0);

        final VoxelShape logPile0Left = Block.box(6.0, 0.0, 0.0, 10.0, 4.0, 16.0);
        final VoxelShape logPile1Left = Block.box(14.0, 0.0, 0.0, 16.0, 4.0, 16.0);
        final VoxelShape logPile2Left = Block.box(4.0, 3.0, 10.0, 16.0, 7.0, 14.0);
        final VoxelShape logPile3Left = Block.box(4.0, 3.0, 2.0, 16.0, 7.0, 6.0);

        final VoxelShape logPile0Right = Block.box(0.0, 0.0, 0.0, 2.0, 4.0, 16.0);
        final VoxelShape logPile1Right = Block.box(0.0, 3.0, 10.0, 4.0, 7.0, 14.0);
        final VoxelShape logPile2Right = Block.box(0.0, 3.0, 2.0, 4.0, 7.0, 6.0);

        final VoxelShape logPileLeft = Shapes.or(logPile0Left, logPile1Left, logPile2Left, logPile3Left);
        final VoxelShape logPileRight = Shapes.or(logPile0Right, logPile1Right, logPile2Right);

        final VoxelShape bottomLeft =
            Shapes.or(tableTop, legNW, legSw, crossBeamLeftN, crossBeamLeftS, crossBeamLeftW, logPileLeft);
        final VoxelShape bottomRight =
            Shapes.or(tableTop, legNe, legSe, crossBeamRightN, crossBeamRightS, crossBeamRightE, logPileRight);
        final VoxelShape topLeft = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        final VoxelShape topRight = Block.box(0.0, 0.0, 8.0, 16.0, 5.0, 8.0);

        Direction.Plane.HORIZONTAL.stream().forEach(direction -> {
            bottomLeftShapes.put(direction, Util.rotateShape(Direction.NORTH, direction, bottomLeft));
            bottomRightShapes.put(direction, Util.rotateShape(Direction.NORTH, direction, bottomRight));
            topLeftShapes.put(direction, Util.rotateShape(Direction.NORTH, direction, topLeft));
            topRightShapes.put(direction, Util.rotateShape(Direction.NORTH, direction, topRight));
        });

        shapes.put(WoodenSawmillModel.BOTTOM_LEFT, bottomLeftShapes);
        shapes.put(WoodenSawmillModel.BOTTOM_RIGHT, bottomRightShapes);
        shapes.put(WoodenSawmillModel.TOP_LEFT, topLeftShapes);
        shapes.put(WoodenSawmillModel.TOP_RIGHT, topRightShapes);

        SHAPES = Collections.unmodifiableMap(shapes);
    }

    private final Component containerName;

    public WoodenSawmillBlock(final IWoodType woodType) {
        super(woodType, BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F));
        this.registerDefaultState(this
            .defaultBlockState()
            .setValue(MODEL, WoodenSawmillModel.BOTTOM_LEFT)
            .setValue(HORIZONTAL_FACING, Direction.NORTH));
        this.containerName = new TranslatableComponent(StringUtils.joinWith(".",
            "container",
            Constants.MOD_ID,
            Util.toRegistryName(this.getWoodType().getName(), WoodenBlockType.SAWMILL.getName())));
    }

    private static Direction getDirectionToNext(final WoodenSawmillModel model, final Direction facing) {
        switch (model) {
        default:
            throw new IllegalStateException("Non-exhaustive switch case over WoodenSawmillModel");
        case BOTTOM_LEFT:
            return facing.getClockWise();
        case BOTTOM_RIGHT:
            return Direction.UP;
        case TOP_LEFT:
            return Direction.DOWN;
        case TOP_RIGHT:
            return facing.getCounterClockWise();
        }
    }

    @Nonnull
    @Override
    public BlockState updateShape(final BlockState state, @Nonnull final Direction facing,
                                  @Nonnull final BlockState facingState, @Nonnull final LevelAccessor world,
                                  @Nonnull final BlockPos currentPos, @Nonnull final BlockPos facingPos) {
        final WoodenSawmillModel model = state.getValue(MODEL);
        if (facing == getDirectionToNext(state.getValue(MODEL), state.getValue(HORIZONTAL_FACING))) {
            return facingState.is(this) && model != facingState.getValue(MODEL)
                   ? state
                   : Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(final BlockState state, @Nonnull final BlockGetter worldIn, @Nonnull final BlockPos pos,
                               @Nonnull final CollisionContext context) {
        return SHAPES.get(state.getValue(MODEL)).get(state.getValue(HORIZONTAL_FACING));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MODEL, HORIZONTAL_FACING);
    }

    @Override
    public void setPlacedBy(final Level worldIn, @Nonnull final BlockPos pos, @Nonnull final BlockState state,
                            final LivingEntity placer, @Nonnull final ItemStack stack) {
        if (!worldIn.isClientSide) {
            final Direction direction = state.getValue(HORIZONTAL_FACING);
            final BlockPos bottomRight = pos.relative(direction.getClockWise());
            final BlockPos topLeft = pos.above();
            final BlockPos topRight = bottomRight.above();

            worldIn.setBlock(bottomRight, state.setValue(MODEL, WoodenSawmillModel.BOTTOM_RIGHT), Block.UPDATE_ALL);
            worldIn.setBlock(topLeft, state.setValue(MODEL, WoodenSawmillModel.TOP_LEFT), Block.UPDATE_ALL);
            worldIn.setBlock(topRight, state.setValue(MODEL, WoodenSawmillModel.TOP_RIGHT), Block.UPDATE_ALL);
            worldIn.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(worldIn, pos, Block.UPDATE_ALL);
        }
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        final Direction direction = context.getHorizontalDirection();
        final BlockPos bottomLeft = context.getClickedPos();
        final BlockPos bottomRight = bottomLeft.relative(direction.getClockWise());
        final BlockPos topLeft = bottomLeft.above();
        final BlockPos topRight = bottomRight.above();

        return Stream
                   .of(bottomRight, topLeft, topRight)
                   .allMatch(blockPos -> context.getLevel().getBlockState(blockPos).canBeReplaced(context)) ? this
                   .defaultBlockState()
                   .setValue(MODEL, WoodenSawmillModel.BOTTOM_LEFT)
                   .setValue(HORIZONTAL_FACING, direction) : null;
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull final BlockState state, final Level world, @Nonnull final BlockPos pos,
                                 @Nonnull final Player player, @Nonnull final InteractionHand hand,
                                 @Nonnull final BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(@Nonnull final BlockState state, @Nonnull final Level world,
                                        @Nonnull final BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new WoodenSawmillContainer(id,
            inventory,
            ContainerLevelAccess.create(world, pos)), containerName);
    }

    public enum WoodenSawmillModel implements StringRepresentable {
        BOTTOM_LEFT("bottom_left"), BOTTOM_RIGHT("bottom_right"), TOP_LEFT("top_left"), TOP_RIGHT("top_right");

        private final String name;

        WoodenSawmillModel(final String name) {
            this.name = name;
        }

        @Nonnull
        @Override
        public String getSerializedName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
