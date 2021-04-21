package yamahari.ilikewood.registry;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;

// TODO same refactor as for other registries
public final class WoodenRecipeSerializers {
    public static RegistryObject<IRecipeSerializer<?>> SAWMILLING;

    private WoodenRecipeSerializers() {
    }
}
