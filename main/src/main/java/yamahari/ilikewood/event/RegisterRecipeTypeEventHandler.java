package yamahari.ilikewood.event;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.data.recipe.AbstractWoodenSawmillRecipe;
import yamahari.ilikewood.registry.WoodenRecipeTypes;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT, Dist.DEDICATED_SERVER
}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterRecipeTypeEventHandler
{
    // TODO change this to correct registry event, once forge adds event for registering directly to vanilla
    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent<Block> event) {
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
