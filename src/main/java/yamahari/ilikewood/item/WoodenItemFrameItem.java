package yamahari.ilikewood.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;

public final class WoodenItemFrameItem extends WoodenItem {
    private final LazyValue<EntityType<? extends ItemFrameEntity>> entityType;

    @SuppressWarnings("unchecked")
    public WoodenItemFrameItem(final IWoodType woodType) {
        super(woodType, WoodenItemType.ITEM_FRAME, new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
        this.entityType =
            new LazyValue<>(() -> (EntityType<? extends ItemFrameEntity>) ILikeWood.ENTITY_TYPE_REGISTRY.getObject(this.getWoodType(),
                WoodenEntityType.ITEM_FRAME));
    }

    @Nonnull
    @Override
    public ActionResultType useOn(final ItemUseContext context) {
        final BlockPos blockPos = context.getClickedPos();
        final Direction direction = context.getClickedFace();
        final BlockPos offsetPos = blockPos.relative(direction);
        final PlayerEntity player = context.getPlayer();
        final ItemStack itemStack = context.getItemInHand();

        if (player != null && !this.canPlace(player, direction, itemStack, offsetPos)) {
            return ActionResultType.FAIL;
        } else {
            final World world = context.getLevel();
            final HangingEntity hangingEntity =
                new WoodenItemFrameEntity(this.getWoodType(), this.entityType.get(), world, offsetPos, direction);

            final CompoundNBT compoundNBT = itemStack.getTag();
            if (compoundNBT != null) {
                EntityType.updateCustomEntityTag(world, player, hangingEntity, compoundNBT);
            }

            if (hangingEntity.survives()) {
                if (!world.isClientSide) {
                    hangingEntity.playPlacementSound();
                    world.addFreshEntity(hangingEntity);
                }

                itemStack.shrink(1);
                return ActionResultType.sidedSuccess(world.isClientSide);
            } else {
                return ActionResultType.CONSUME;
            }
        }
    }

    private boolean canPlace(final PlayerEntity player, final Direction direction, final ItemStack itemStack,
                             final BlockPos blockPos) {
        return !World.isOutsideBuildHeight(blockPos) && player.mayUseItemAt(blockPos, direction, itemStack);
    }
}
