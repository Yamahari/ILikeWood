package yamahari.ilikewood.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.blockentity.WoodenCrateBlockEntity;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Stream;

public final class WoodenCrateBlock
    extends BaseEntityBlock
    implements IWooden
{
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final ResourceLocation CONTENTS = new ResourceLocation(Constants.MOD_ID, "contents");
    private static final VoxelShape SHAPE = Stream
        .of(Block.box(0, 0, 0, 2, 16, 2), Block.box(0, 0, 14, 2, 16, 16), Block.box(14, 0, 14, 16, 16, 16), Block.box(14, 0, 0, 16, 16, 2),
            Block.box(2, 2, 2, 14, 14, 14), Block.box(0, 14, 2, 2, 16, 14), Block.box(0, 0, 2, 2, 2, 14), Block.box(14, 0, 2, 16, 2, 14),
            Block.box(14, 14, 2, 16, 16, 14), Block.box(2, 14, 14, 14, 16, 16), Block.box(2, 0, 14, 14, 2, 16), Block.box(2, 0, 0, 14, 2, 2),
            Block.box(2, 14, 0, 14, 16, 2)
        )
        .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
        .get();
    private final IWoodType woodType;

    public WoodenCrateBlock(final IWoodType woodType)
    {
        super(BlockBehaviour.Properties.copy(Blocks.BARREL));
        this.woodType = woodType;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nonnull
    @Override
    public InteractionResult use(
        @Nonnull final BlockState state,
        final Level level,
        @Nonnull final BlockPos pos,
        @Nonnull final Player player,
        @Nonnull final InteractionHand hand,
        @Nonnull final BlockHitResult hitResult
    )
    {
        if (level.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }
        else if (player.isSpectator())
        {
            return InteractionResult.CONSUME;
        }
        else
        {
            final var blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WoodenCrateBlockEntity woodenCrateBlockEntity)
            {
                player.openMenu(woodenCrateBlockEntity);

                return InteractionResult.CONSUME;
            }
            else
            {
                return InteractionResult.PASS;
            }
        }
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull final BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public void playerWillDestroy(
        final Level level,
        @Nonnull final BlockPos pos,
        @Nonnull final BlockState state,
        @Nonnull final Player player
    )
    {
        final var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WoodenCrateBlockEntity woodenCrateBlockEntity)
        {
            if (!level.isClientSide && player.isCreative() && !woodenCrateBlockEntity.isEmpty())
            {
                final var stack = new ItemStack(ILikeWood.BLOCK_REGISTRY.getObject(this.getWoodType(), WoodenBlockType.CRATE));
                blockEntity.saveToItem(stack);
                if (woodenCrateBlockEntity.hasCustomName())
                {
                    stack.setHoverName(woodenCrateBlockEntity.getCustomName());
                }

                final var itemEntity = new ItemEntity(level, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, stack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
            else
            {
                woodenCrateBlockEntity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(
        final @Nonnull BlockState blockState,
        @Nonnull LootContext.Builder builder
    )
    {
        BlockEntity blockentity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof WoodenCrateBlockEntity woodenCrateBlockEntity)
        {
            builder = builder.withDynamicDrop(CONTENTS, (p_56218_, p_56219_) ->
            {
                for (int i = 0; i < woodenCrateBlockEntity.getContainerSize(); ++i)
                {
                    p_56219_.accept(woodenCrateBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(blockState, builder);
    }

    @Override
    public void setPlacedBy(
        @Nonnull final Level level,
        @Nonnull final BlockPos pos,
        @Nonnull final BlockState state,
        @Nullable LivingEntity entity,
        final ItemStack stack
    )
    {
        if (stack.hasCustomHoverName())
        {
            final BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WoodenCrateBlockEntity woodenCrateBlockEntity)
            {
                woodenCrateBlockEntity.setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    public void onRemove(
        final BlockState stateOld,
        @Nonnull final Level level,
        @Nonnull final BlockPos pos,
        final BlockState stateNew,
        final boolean isMoving
    )
    {
        if (!stateOld.is(stateNew.getBlock()))
        {
            final var blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WoodenCrateBlockEntity)
            {
                level.updateNeighbourForOutputSignal(pos, stateOld.getBlock());
            }

            super.onRemove(stateOld, level, pos, stateNew, isMoving);
        }
    }

    @Override
    public void appendHoverText(
        @Nonnull final ItemStack stack,
        @Nullable final BlockGetter blockGetter,
        @Nonnull final List<Component> components,
        @Nonnull final TooltipFlag tooltipFlag
    )
    {
        super.appendHoverText(stack, blockGetter, components, tooltipFlag);
        final var compoundTag = BlockItem.getBlockEntityData(stack);
        if (compoundTag != null)
        {
            if (compoundTag.contains("LootTable", 8))
            {
                components.add(Component.literal("???????"));
            }

            if (compoundTag.contains("Items", 9))
            {
                final NonNullList<ItemStack> stacks = NonNullList.withSize(15, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundTag, stacks);
                int i = 0;
                int j = 0;

                for (final var stack1 : stacks)
                {
                    if (!stack1.isEmpty())
                    {
                        ++j;
                        if (i <= 4)
                        {
                            ++i;
                            final var hoverName = stack1.getHoverName().copy();
                            hoverName.append(" x").append(String.valueOf(stack1.getCount()));
                            components.add(hoverName);
                        }
                    }
                }

                if (j - i > 0)
                {
                    components.add(Component.translatable("container.shulkerBox.more", j - i).withStyle(ChatFormatting.ITALIC));
                }
            }
        }

    }

    @Nonnull
    @Override
    public PushReaction getPistonPushReaction(@Nonnull final BlockState state)
    {
        return PushReaction.DESTROY;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(
        @Nonnull final BlockState state,
        @Nonnull final BlockGetter blockGetter,
        @Nonnull final BlockPos blockPos,
        @Nonnull final CollisionContext collisionContext
    )
    {
        return SHAPE;
    }

    @Override
    public boolean hasAnalogOutputSignal(@Nonnull final BlockState state)
    {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(
        @Nonnull final BlockState state,
        final Level level,
        @Nonnull final BlockPos pos
    )
    {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Nonnull
    @Override
    public ItemStack getCloneItemStack(
        @Nonnull final BlockGetter blockGetter,
        @Nonnull final BlockPos pos,
        @Nonnull final BlockState state
    )
    {
        final var stack = super.getCloneItemStack(blockGetter, pos, state);
        blockGetter.getBlockEntity(pos, WoodenBlockEntityTypes.WOODEN_CRATE.get()).ifPresent(blockEntity -> blockEntity.saveToItem(stack));

        return stack;
    }

    @Nonnull
    @Override
    public BlockState rotate(
        final BlockState state,
        final Rotation rotation
    )
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    public BlockState mirror(
        final BlockState state,
        final Mirror mirror
    )
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull final StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(
        @Nonnull final BlockPos pos,
        @Nonnull final BlockState state
    )
    {
        return new WoodenCrateBlockEntity(pos, state);
    }

    @Override
    public IWoodType getWoodType()
    {
        return this.woodType;
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }
}
