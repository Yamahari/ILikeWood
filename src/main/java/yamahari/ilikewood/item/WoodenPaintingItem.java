package yamahari.ilikewood.item;

import com.google.common.base.Suppliers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenPaintingEntity;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class WoodenPaintingItem extends HangingEntityItem implements IWooden {
    private final IWoodType woodType;
    private final Supplier<EntityType<? extends WoodenPaintingEntity>> entityType;


    public WoodenPaintingItem(final IWoodType woodType) {
        super(EntityType.PAINTING, new Item.Properties());
        this.woodType = woodType;
        // noinspection unchecked
        this.entityType = Suppliers.memoize(() -> (EntityType<? extends WoodenPaintingEntity>) ILikeWood.ENTITY_TYPE_REGISTRY.getObject(this.getWoodType(),
            WoodenEntityType.PAINTING));
    }

    @Nonnull
    @Override
    public InteractionResult useOn(@Nonnull final UseOnContext context) {
        final var clickedPos = context.getClickedPos();
        final var clickedFace = context.getClickedFace();
        final var relative = clickedPos.relative(clickedFace);
        final var player = context.getPlayer();
        final var itemStack = context.getItemInHand();

        if (player != null && !this.mayPlace(player, clickedFace, itemStack, relative)) {
            return InteractionResult.FAIL;
        }
        else {
            final var level = context.getLevel();
            final HangingEntity hangingEntity;
            final var optional = WoodenPaintingEntity.create(level, relative, clickedFace, this.woodType, this.entityType.get());
            if (optional.isEmpty()) {
                return InteractionResult.CONSUME;
            }
            hangingEntity = optional.get();


            final var tag = itemStack.getTag();
            if (tag != null) {
                EntityType.updateCustomEntityTag(level, player, hangingEntity, tag);
            }

            if (hangingEntity.survives()) {
                if (!level.isClientSide) {
                    hangingEntity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                    level.addFreshEntity(hangingEntity);
                }

                itemStack.shrink(1);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
