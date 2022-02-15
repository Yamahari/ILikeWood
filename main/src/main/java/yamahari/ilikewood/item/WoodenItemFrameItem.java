package yamahari.ilikewood.item;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class WoodenItemFrameItem extends WoodenItem {
    private final Supplier<EntityType<? extends ItemFrame>> entityType;

    public WoodenItemFrameItem(final IWoodType woodType) {
        super(woodType, WoodenItemType.ITEM_FRAME, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
        // noinspection unchecked
        this.entityType =
            Suppliers.memoize(() -> (EntityType<? extends ItemFrame>) ILikeWood.ENTITY_TYPE_REGISTRY.getObject(this.getWoodType(),
                WoodenEntityType.ITEM_FRAME));
    }

    @Nonnull
    @Override
    public InteractionResult useOn(final UseOnContext context) {
        final BlockPos blockPos = context.getClickedPos();
        final Direction direction = context.getClickedFace();
        final BlockPos offsetPos = blockPos.relative(direction);
        final Player player = context.getPlayer();
        final ItemStack itemStack = context.getItemInHand();

        if (player != null && !this.canPlace(player, direction, itemStack, offsetPos)) {
            return InteractionResult.FAIL;
        } else {
            final Level world = context.getLevel();
            final HangingEntity hangingEntity =
                new WoodenItemFrameEntity(this.getWoodType(), this.entityType.get(), world, offsetPos, direction);

            final CompoundTag compoundNBT = itemStack.getTag();
            if (compoundNBT != null) {
                EntityType.updateCustomEntityTag(world, player, hangingEntity, compoundNBT);
            }

            if (hangingEntity.survives()) {
                if (!world.isClientSide) {
                    hangingEntity.playPlacementSound();
                    world.addFreshEntity(hangingEntity);
                }

                itemStack.shrink(1);
                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.CONSUME;
            }
        }
    }

    private boolean canPlace(final Player player, final Direction direction, final ItemStack itemStack,
                             final BlockPos blockPos) {
        return !player.level.isOutsideBuildHeight(blockPos) && player.mayUseItemAt(blockPos, direction, itemStack);
    }
}
