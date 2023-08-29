package yamahari.ilikewood.entity;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.WoodenPaintingItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class WoodenPaintingEntity extends Painting implements IWooden {
    private static final EntityDataAccessor<String> DATA_WOOD_TYPE_RESOURCE_LOCATION = SynchedEntityData.defineId(WoodenPaintingEntity.class, EntityDataSerializers.STRING);
    private IWoodType woodType;

    public WoodenPaintingEntity(final EntityType<? extends WoodenPaintingEntity> entityType, final Level level) {
        super(entityType, level);
        this.woodType = VanillaWoodTypes.OAK;
    }

    private static int getVariantArea(final Holder<PaintingVariant> holder) {
        return holder.value().getWidth() * holder.value().getHeight();
    }

    public static Optional<WoodenPaintingEntity> create(final Level level, final BlockPos blockPos, final Direction direction, final IWoodType woodType, final EntityType<? extends WoodenPaintingEntity> entityType) {
        final var painting = new WoodenPaintingEntity(entityType, level);
        painting.setWoodType(woodType);
        painting.pos = blockPos;

        final List<Holder<PaintingVariant>> list = new ArrayList<>();
        BuiltInRegistries.PAINTING_VARIANT.getTagOrEmpty(PaintingVariantTags.PLACEABLE).forEach(list::add);

        if (list.isEmpty()) {
            return Optional.empty();
        }
        else {
            painting.setDirection(direction);
            list.removeIf(holder -> {
                painting.setVariant(holder);
                return !painting.survives();
            });
            if (list.isEmpty()) {
                return Optional.empty();
            }
            else {
                final var maxArea = list.stream().mapToInt(WoodenPaintingEntity::getVariantArea).max().orElse(0);
                list.removeIf((p_218883_) -> getVariantArea(p_218883_) < maxArea);
                final var optional = Util.getRandomSafe(list, painting.random);
                if (optional.isEmpty()) {
                    return Optional.empty();
                }
                else {
                    painting.setVariant(optional.get());
                    painting.setDirection(direction);
                    return Optional.of(painting);
                }
            }
        }
    }

    @Nonnull
    @Override
    public InteractionResult interact(@Nonnull final Player player, @Nonnull final InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && !player.isSecondaryUseActive()) {
            final var itemInHand = player.getItemInHand(hand);
            if (!itemInHand.isEmpty()) {
                if (itemInHand.getItem() instanceof WoodenPaintingItem) {
                    final var currentVariant = this.entityData.get(DATA_PAINTING_VARIANT_ID);
                    final List<Holder<PaintingVariant>> list = new ArrayList<>();
                    BuiltInRegistries.PAINTING_VARIANT.getTagOrEmpty(PaintingVariantTags.PLACEABLE).forEach(variant -> {
                        if (currentVariant != variant && variant.get().getWidth() == currentVariant.get().getWidth() && variant.get().getHeight() == currentVariant.get()
                            .getHeight()) {
                            list.add(variant);
                        }
                    });

                    final var level = player.level();

                    if (!level.isClientSide()) {
                        if (list.isEmpty()) {
                            return InteractionResult.FAIL;
                        }
                        this.entityData.set(DATA_PAINTING_VARIANT_ID, Util.getRandom(list, this.random));
                        return InteractionResult.SUCCESS;
                    }
                    else {
                        return list.isEmpty() ? InteractionResult.PASS : InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WOOD_TYPE_RESOURCE_LOCATION, new ResourceLocation(VanillaWoodTypes.OAK.getModId(), VanillaWoodTypes.OAK.getName()).toString());
    }

    @Override
    public void onSyncedDataUpdated(@Nonnull final EntityDataAccessor<?> dataAccessor) {
        super.onSyncedDataUpdated(dataAccessor);
        if (DATA_WOOD_TYPE_RESOURCE_LOCATION.equals(dataAccessor)) {
            this.woodType = ILikeWood.WOOD_TYPE_REGISTRY.get(new ResourceLocation(this.entityData.get(DATA_WOOD_TYPE_RESOURCE_LOCATION)));
        }
    }

    @Override
    public void addAdditionalSaveData(@Nonnull final CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putString("wood_type", this.entityData.get(DATA_WOOD_TYPE_RESOURCE_LOCATION));
    }

    @Override
    public void readAdditionalSaveData(@Nonnull final CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        final var woodType = compoundTag.getString("wood_type");
        this.entityData.set(DATA_WOOD_TYPE_RESOURCE_LOCATION, woodType.isEmpty() ? new ResourceLocation(Constants.MOD_ID, VanillaWoodTypes.OAK.getName()).toString() : woodType);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    public void setWoodType(final IWoodType woodType) {
        this.entityData.set(DATA_WOOD_TYPE_RESOURCE_LOCATION, new ResourceLocation(woodType.getModId(), woodType.getName()).toString());
    }
}
