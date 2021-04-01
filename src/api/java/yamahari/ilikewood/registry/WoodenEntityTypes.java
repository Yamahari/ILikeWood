package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenEntityTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<EntityType<?>>>> REGISTRY_OBJECTS;

    public static RegistryObject<EntityType<?>> getRegistryObject(final WoodenObjectType objectType,
                                                                  final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static Stream<RegistryObject<EntityType<?>>> getRegistryObjects(final WoodenObjectType woodenObjectType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).values().stream();
    }

    public static Stream<RegistryObject<EntityType<?>>> getRegistryObjects(
        final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenEntityTypes::getRegistryObjects);
    }

    public static EntityType<?> getEntityType(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return getRegistryObject(woodenObjectType, woodType).get();
    }

    public static Stream<EntityType<?>> getEntityTypes(final WoodenObjectType woodenObjectType) {
        return getRegistryObjects(woodenObjectType).map(RegistryObject::get);
    }

    public static Stream<EntityType<?>> getEntityTypes(final WoodenObjectType... woodenObjectTypes) {
        return getRegistryObjects(woodenObjectTypes).map(RegistryObject::get);
    }

    private WoodenEntityTypes() {
    }
}
