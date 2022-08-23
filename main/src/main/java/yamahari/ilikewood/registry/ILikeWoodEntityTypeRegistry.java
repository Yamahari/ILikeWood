package yamahari.ilikewood.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.entity.WoodenChairEntity;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.entity.WoodenPaintingEntity;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class ILikeWoodEntityTypeRegistry
    extends AbstractILikeWoodObjectRegistry<EntityType<?>, WoodenEntityType>
{
    public ILikeWoodEntityTypeRegistry()
    {
        super(ForgeRegistries.ENTITY_TYPES);
    }

    private void registerChairEntityTypes()
    {
        this.registryObjects.put(WoodenEntityType.CHAIR, Collections.singletonMap(Util.DUMMY_WOOD_TYPE, registerChairEntityType()));
    }

    private void registerItemFrameEntityTypes()
    {
        final Map<IWoodType, RegistryObject<EntityType<?>>> entityTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(WoodenItemType.ITEM_FRAME))
            .forEach(woodType -> entityTypes.put(woodType, registerItemFrameEntityType(woodType)));
        this.registryObjects.put(WoodenEntityType.ITEM_FRAME, Collections.unmodifiableMap(entityTypes));
    }

    private void registerPaintingEntityTypes()
    {
        this.registryObjects.put(WoodenEntityType.PAINTING, Collections.singletonMap(Util.DUMMY_WOOD_TYPE, registerPaintingEntityType()));
    }

    private RegistryObject<EntityType<?>> registerItemFrameEntityType(final IWoodType woodType)
    {
        final String name;
        if (woodType.getModId().equals(Constants.MOD_ID))
        {
            name = Util.toRegistryName(woodType.getName(), WoodenEntityType.ITEM_FRAME.getName());
        }
        else
        {
            name = Util.toRegistryName(woodType.getModId(), woodType.getName(), WoodenEntityType.ITEM_FRAME.getName());
        }

        return this.registry.register(name, () -> EntityType.Builder
            .<WoodenItemFrameEntity>of((entityType, world) -> new WoodenItemFrameEntity(woodType, entityType, world), MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build(name));
    }

    private RegistryObject<EntityType<?>> registerChairEntityType()
    {
        final String name = WoodenEntityType.CHAIR.getName();
        return this.registry.register(name, () -> EntityType.Builder.of(WoodenChairEntity::new, MobCategory.MISC).sized(0.0F, 0.0F).build(name));
    }

    private RegistryObject<EntityType<?>> registerPaintingEntityType()
    {
        final String name = WoodenEntityType.PAINTING.getName();
        return this.registry.register(name, () -> EntityType.Builder
            .of(WoodenPaintingEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(10)
            .updateInterval(Integer.MAX_VALUE)
            .build(name));
    }

    @Override
    protected void register()
    {
        if (ILikeWoodConfig.ITEM_FRAMES_CONFIG.isEnabled())
        {
            registerItemFrameEntityTypes();
        }

        if (ILikeWoodConfig.CHAIRS_CONFIG.isEnabled() || ILikeWoodConfig.STOOLS_CONFIG.isEnabled())
        {
            registerChairEntityTypes();
        }

        if (ILikeWoodConfig.PAINTING_CONFIG.isEnabled())
        {
            registerPaintingEntityTypes();
        }
    }

    @Override
    public Stream<RegistryObject<EntityType<?>>> getRegistryObjects(final WoodenEntityType objectType)
    {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getEntityTypes().contains(objectType))
            .map(woodType -> this.getRegistryObject(woodType, objectType))
            .distinct();
    }
}