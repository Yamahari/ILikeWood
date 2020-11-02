package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Map;
import java.util.stream.Stream;

public final class WoodenEntityTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<EntityType<?>>>> REGISTRY_OBJECTS;

    public static EntityType<?> getEntityType(final WoodenObjectType objectType, final IWoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<EntityType<?>> getEntityTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static RegistryObject<EntityType<?>> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    private WoodenEntityTypes() {
    }
}
