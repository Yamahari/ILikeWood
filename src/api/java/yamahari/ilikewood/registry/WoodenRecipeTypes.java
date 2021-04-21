package yamahari.ilikewood.registry;

import net.minecraft.item.crafting.IRecipeType;
import yamahari.ilikewood.data.recipe.AbstractWoodenSawmillRecipe;

// TODO same refactor as for other registries
public final class WoodenRecipeTypes {
    public static IRecipeType<AbstractWoodenSawmillRecipe> SAWMILLING;
    // TODO RegistryObject when Forge support recipe types

    private WoodenRecipeTypes() {
    }
}
