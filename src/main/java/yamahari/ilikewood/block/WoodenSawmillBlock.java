package yamahari.ilikewood.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
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

import static net.minecraftforge.common.util.Constants.BlockFlags.DEFAULT;

public class WoodenSawmillBlock extends WoodenBlock {
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

        final VoxelShape logPileLeft = VoxelShapes.or(logPile0Left, logPile1Left, logPile2Left, logPile3Left);
        final VoxelShape logPileRight = VoxelShapes.or(logPile0Right, logPile1Right, logPile2Right);

        final VoxelShape bottomLeft =
            VoxelShapes.or(tableTop, legNW, legSw, crossBeamLeftN, crossBeamLeftS, crossBeamLeftW, logPileLeft);
        final VoxelShape bottomRight =
            VoxelShapes.or(tableTop, legNe, legSe, crossBeamRightN, crossBeamRightS, crossBeamRightE, logPileRight);
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

    private final ITextComponent containerName;

    public WoodenSawmillBlock(final IWoodType woodType) {
        super(woodType,
            AbstractBlock.Properties
                .of(Material.WOOD)
                .sound(SoundType.WOOD)
                .strength(2.0F)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE));
        this.registerDefaultState(this
            .defaultBlockState()
            .setValue(MODEL, WoodenSawmillModel.BOTTOM_LEFT)
            .setValue(HORIZONTAL_FACING, Direction.NORTH));
        this.containerName = new TranslationTextComponent(StringUtils.joinWith(".",
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
                                  @Nonnull final BlockState facingState, @Nonnull final IWorld world,
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
    public VoxelShape getShape(final BlockState state, @Nonnull final IBlockReader worldIn, @Nonnull final BlockPos pos,
                               @Nonnull final ISelectionContext context) {
        return SHAPES.get(state.getValue(MODEL)).get(state.getValue(HORIZONTAL_FACING));
    }

    @Override
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(MODEL, HORIZONTAL_FACING);
    }

    @Override
    public void setPlacedBy(final World worldIn, @Nonnull final BlockPos pos, @Nonnull final BlockState state,
                            final LivingEntity placer, @Nonnull final ItemStack stack) {
        if (!worldIn.isClientSide) {
            final Direction direction = state.getValue(HORIZONTAL_FACING);
            final BlockPos bottomRight = pos.relative(direction.getCounterClockWise());
            final BlockPos topLeft = pos.above();
            final BlockPos topRight = bottomRight.above();

            worldIn.setBlock(bottomRight, state.setValue(MODEL, WoodenSawmillModel.BOTTOM_RIGHT), DEFAULT);
            worldIn.setBlock(topLeft, state.setValue(MODEL, WoodenSawmillModel.TOP_LEFT), DEFAULT);
            worldIn.setBlock(topRight, state.setValue(MODEL, WoodenSawmillModel.TOP_RIGHT), DEFAULT);
            worldIn.blockUpdated(pos, Blocks.AIR);
            state.updateNeighbourShapes(worldIn, pos, DEFAULT);
        }
    }

    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
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
    public ActionResultType use(@Nonnull final BlockState state, final World world, @Nonnull final BlockPos pos,
                                @Nonnull final PlayerEntity player, @Nonnull final Hand hand,
                                @Nonnull final BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public INamedContainerProvider getMenuProvider(@Nonnull final BlockState state, @Nonnull final World world,
                                                   @Nonnull final BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new WoodenSawmillContainer(id,
            inventory,
            IWorldPosCallable.create(world, pos)), containerName);
    }

    public enum WoodenSawmillModel implements IStringSerializable {
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
