package yamahari.ilikewood.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ILikeWoodContainerRegistry {
    public static final DeferredRegister<ContainerType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<ContainerType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerContainerTypes(ILikeWoodContainerRegistry::registerWorkBenchContainer));

        WoodenContainerTypes.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private ILikeWoodContainerRegistry() {
    }

    private static Map<IWoodType, RegistryObject<ContainerType<?>>> registerContainerTypes(final Function<IWoodType, RegistryObject<ContainerType<?>>> function) {
        final Map<IWoodType, RegistryObject<ContainerType<?>>> containerTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .forEach(woodType -> containerTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(containerTypes);
    }

    private static RegistryObject<ContainerType<?>> registerWorkBenchContainer(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.CRAFTING_TABLE.toString()),
                () -> new ContainerType<>((windowId, inventory) -> new WoodenWorkBenchContainer(woodType, windowId, inventory)));
    }
}
