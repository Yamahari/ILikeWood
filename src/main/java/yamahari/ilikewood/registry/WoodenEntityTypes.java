package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WoodenEntityTypes {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<EntityType<?>>>> REGISTRY_OBJECTS;

    static {
        final EnumMap<WoodenObjectType, Map<WoodType, RegistryObject<EntityType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.ITEM_FRAME, registerSimpleEntityTypes(WoodenEntityTypes::registerItemFrameEntityTypes));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private WoodenEntityTypes() {
    }

    public static EntityType<?> getEntityType(final WoodenObjectType objectType, final WoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<EntityType<?>> getEntityTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static RegistryObject<EntityType<?>> getRegistryObject(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    private static Map<WoodType, RegistryObject<EntityType<?>>> registerSimpleEntityTypes(final Function<WoodType, RegistryObject<EntityType<?>>> function) {
        final Map<WoodType, RegistryObject<EntityType<?>>> entityTypes = new EnumMap<>(WoodType.class);
        WoodType.getLoadedValues().forEach(woodType -> entityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(entityTypes);
    }

    private static RegistryObject<EntityType<?>> registerItemFrameEntityTypes(final WoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.ITEM_FRAME.toString());
        return REGISTRY.register(name,
                () -> EntityType.Builder.<WoodenItemFrameEntity>create(
                        (entityType, world) -> new WoodenItemFrameEntity(woodType, entityType, world), EntityClassification.MISC)
                        .size(0.5F, 0.5F)
                        .func_233606_a_(10)
                        .func_233608_b_(Integer.MAX_VALUE)
                        // .setCustomClientFactory(((spawnEntity, world) -> new WoodenItemFrameEntity(woodType, (EntityType<? extends ItemFrameEntity>)spawnEntity.getEntity().getType(), world)))
                        .build(name));
    }
}
