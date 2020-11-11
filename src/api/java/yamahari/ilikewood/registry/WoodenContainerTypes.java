package yamahari.ilikewood.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenContainerTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<ContainerType<?>>>> REGISTRY_OBJECTS;

    public static RegistryObject<ContainerType<?>> getRegistryObject(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).get(woodType);
    }

    public static Stream<RegistryObject<ContainerType<?>>> getRegistryObjects(final WoodenObjectType woodenObjectType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).values().stream();
    }

    public static Stream<RegistryObject<ContainerType<?>>> getRegistryObjects(final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenContainerTypes::getRegistryObjects);
    }


    public static ContainerType<?> getContainerType(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return getRegistryObject(woodenObjectType, woodType).get();
    }

    public static Stream<ContainerType<?>> getContainerTypes(final WoodenObjectType woodenObjectType) {
        return getRegistryObjects(woodenObjectType).map(RegistryObject::get);
    }

    public static Stream<ContainerType<?>> getContainerTypes(final WoodenObjectType... woodenObjectTypes) {
        return getRegistryObjects(woodenObjectTypes).map(RegistryObject::get);
    }

    private WoodenContainerTypes() {
    }
}
