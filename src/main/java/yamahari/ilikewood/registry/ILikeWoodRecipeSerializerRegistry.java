package yamahari.ilikewood.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.data.recipe.WoodenSawmillRecipe;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodRecipeSerializerRegistry
{
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    static
    {
        WoodenRecipeSerializers.SAWMILLING = REGISTRY.register("sawmilling", WoodenSawmillRecipeSerializer::new);
    }

    private ILikeWoodRecipeSerializerRegistry()
    {
    }

    private static class WoodenSawmillRecipeSerializer
        extends SingleItemRecipe.Serializer<WoodenSawmillRecipe>
    {
        public WoodenSawmillRecipeSerializer()
        {
            super(WoodenSawmillRecipe::new);
        }
    }
}