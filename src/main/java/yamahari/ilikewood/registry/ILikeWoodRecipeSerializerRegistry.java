package yamahari.ilikewood.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.data.recipe.WoodenSawmillRecipe;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodRecipeSerializerRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    static {
        WoodenRecipeSerializers.SAWMILLING = REGISTRY.register("sawmilling", WoodenSawmillRecipeSerializer::new);
        WoodenRecipeTypes.SAWMILLING = register("sawmilling");
    }

    private ILikeWoodRecipeSerializerRegistry() {
    }

    private static <T extends Recipe<?>> RecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE,
            new ResourceLocation(Constants.MOD_ID, key),
            new RecipeType<T>() {
                public String toString() {
                    return String.format("%s:%s", Constants.MOD_ID, key);
                }
            });
    }

    private static class WoodenSawmillRecipeSerializer extends SingleItemRecipe.Serializer<WoodenSawmillRecipe> {
        public WoodenSawmillRecipeSerializer() {
            super(WoodenSawmillRecipe::new);
        }
    }
}
