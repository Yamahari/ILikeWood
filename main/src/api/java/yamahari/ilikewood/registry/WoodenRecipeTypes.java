package yamahari.ilikewood.registry;

import net.minecraft.world.item.crafting.RecipeType;
import yamahari.ilikewood.data.recipe.AbstractWoodenSawmillRecipe;

// TODO same refactor as for other registries
public final class WoodenRecipeTypes {
    public static RecipeType<AbstractWoodenSawmillRecipe> SAWMILLING;
    // TODO RegistryObject when Forge support recipe types

    private WoodenRecipeTypes() {
    }
}
