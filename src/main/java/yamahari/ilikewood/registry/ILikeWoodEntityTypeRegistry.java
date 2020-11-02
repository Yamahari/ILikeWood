package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ILikeWoodEntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, Constants.MOD_ID);

    static {
        final EnumMap<WoodenObjectType, Map<IWoodType, RegistryObject<EntityType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.ITEM_FRAME, registerSimpleEntityTypes(ILikeWoodEntityTypeRegistry::registerItemFrameEntityTypes));

        WoodenEntityTypes.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private ILikeWoodEntityTypeRegistry() {
    }

    private static Map<IWoodType, RegistryObject<EntityType<?>>> registerSimpleEntityTypes(final Function<IWoodType, RegistryObject<EntityType<?>>> function) {
        final Map<IWoodType, RegistryObject<EntityType<?>>> entityTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> entityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(entityTypes);
    }

    private static RegistryObject<EntityType<?>> registerItemFrameEntityTypes(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.ITEM_FRAME.toString());
        return REGISTRY.register(name,
                () -> EntityType.Builder.<WoodenItemFrameEntity>create(
                        (entityType, world) -> new WoodenItemFrameEntity(woodType, entityType, world), EntityClassification.MISC)
                        .size(0.5F, 0.5F)
                        .trackingRange(10)
                        .func_233608_b_(Integer.MAX_VALUE)
                        // .setCustomClientFactory(((spawnEntity, world) -> new WoodenItemFrameEntity(woodType, (EntityType<? extends ItemFrameEntity>)spawnEntity.getEntity().getType(), world)))
                        .build(name));
    }
}
