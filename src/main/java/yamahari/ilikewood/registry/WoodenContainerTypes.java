package yamahari.ilikewood.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WoodenContainerTypes {
    public static final DeferredRegister<ContainerType<?>> REGISTRY = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<ContainerType<?>>>> REGISTRY_OBJECTS;

    static {
        final Map<WoodenObjectType, Map<WoodType, RegistryObject<ContainerType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerContainerTypes(WoodenContainerTypes::registerWorkBenchContainer));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private WoodenContainerTypes() {
    }

    public static RegistryObject<ContainerType<?>> getRegistryObject(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static ContainerType<?> getContainerType(final WoodenObjectType objectType, final WoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<ContainerType<?>> getContainerTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    private static Map<WoodType, RegistryObject<ContainerType<?>>> registerContainerTypes(final Function<WoodType, RegistryObject<ContainerType<?>>> function) {
        final Map<WoodType, RegistryObject<ContainerType<?>>> containerTypes = new EnumMap<>(WoodType.class);
        for (final WoodType woodType : WoodType.values()) {
            containerTypes.put(woodType, function.apply(woodType));
        }
        return Collections.unmodifiableMap(containerTypes);
    }

    private static RegistryObject<ContainerType<?>> registerWorkBenchContainer(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CRAFTING_TABLE.toString()),
                () -> new ContainerType<>((windowId, inventory) -> new WoodenWorkBenchContainer(woodType, windowId, inventory)));
    }
}
