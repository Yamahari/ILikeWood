package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenTileEntityTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<TileEntityType<?>>>> REGISTRY_OBJECTS;

    public static RegistryObject<TileEntityType<?>> getRegistryObject(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).get(woodType);
    }

    public static Stream<RegistryObject<TileEntityType<?>>> getRegistryObjects(final WoodenObjectType woodenObjectType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).values().stream();
    }

    public static Stream<RegistryObject<TileEntityType<?>>> getRegistryObjects(final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenTileEntityTypes::getRegistryObjects);
    }


    public static TileEntityType<?> getTileEntityType(final WoodenObjectType objectType, final IWoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<TileEntityType<?>> getTileEntityTypes(final WoodenObjectType woodenObjectType) {
        return getRegistryObjects(woodenObjectType).map(RegistryObject::get);
    }

    public static Stream<TileEntityType<?>> getTileEntityTypes(final WoodenObjectType... woodenObjectTypes) {
        return getRegistryObjects(woodenObjectTypes).map(RegistryObject::get);
    }

    private WoodenTileEntityTypes() {
    }
}
