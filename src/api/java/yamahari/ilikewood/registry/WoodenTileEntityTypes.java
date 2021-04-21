package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

// TODO same refactor as for other registries
public final class WoodenTileEntityTypes {
    public static RegistryObject<TileEntityType<?>> WOODEN_BARREL;
    public static RegistryObject<TileEntityType<?>> WOODEN_CHEST;
    public static RegistryObject<TileEntityType<?>> WOODEN_LECTERN;

    private WoodenTileEntityTypes() {
    }
}
