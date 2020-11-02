package yamahari.ilikewood.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Map;
import java.util.stream.Stream;

public final class WoodenContainerTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<ContainerType<?>>>> REGISTRY_OBJECTS;

    public static RegistryObject<ContainerType<?>> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static ContainerType<?> getContainerType(final WoodenObjectType objectType, final IWoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<ContainerType<?>> getContainerTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    private WoodenContainerTypes() {
    }
}
