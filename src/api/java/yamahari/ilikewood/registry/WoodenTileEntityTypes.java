package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Map;
import java.util.stream.Stream;

public final class WoodenTileEntityTypes {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<TileEntityType<?>>>> REGISTRY_OBJECTS;

    public static TileEntityType<?> getTileEntityType(final WoodenObjectType objectType, final IWoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<TileEntityType<?>> getTileEntityTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static RegistryObject<TileEntityType<?>> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    private WoodenTileEntityTypes() {
    }
}
