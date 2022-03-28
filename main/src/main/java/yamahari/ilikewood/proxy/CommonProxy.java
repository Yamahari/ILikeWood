package yamahari.ilikewood.proxy;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.recipe.AbstractWoodenSawmillRecipe;
import yamahari.ilikewood.registry.WoodenRecipeTypes;
import yamahari.ilikewood.util.Constants;

public class CommonProxy implements IProxy
{
    @Override
    public void onFMLClientSetup(FMLClientSetupEvent event) {
        ILikeWood.LOGGER.info("CommonProxy: FMLClientSetupEvent");
    }

    @Override
    public void onFMLCommonSetup(FMLCommonSetupEvent event) {
        ILikeWood.LOGGER.info("CommonProxy: FMLCommonSetupEvent");

        WoodenRecipeTypes.SAWMILLING = Registry.register(
            Registry.RECIPE_TYPE,
            new ResourceLocation(Constants.MOD_ID, "sawmilling"),
            new RecipeType<AbstractWoodenSawmillRecipe>()
            {
                public String toString() {
                    return String.format("%s:%s", Constants.MOD_ID, "sawmilling");
                }
            }
        );
    }
}
