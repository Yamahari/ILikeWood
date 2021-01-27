package yamahari.ilikewood.registry;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.data.recipe.WoodenSawmillRecipe;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodRecipeSerializerRegistry {
    public static final DeferredRegister<IRecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    static {
        WoodenRecipeSerializers.SAWMILLING = REGISTRY.register("sawmilling", () -> new SingleItemRecipe.Serializer<>(WoodenSawmillRecipe::new));
        WoodenRecipeTypes.SAWMILLING = register("sawmilling");
    }

    private ILikeWoodRecipeSerializerRegistry() {
    }

    private static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Constants.MOD_ID, key), new IRecipeType<T>() {
            public String toString() {
                return String.format("%s:%s", Constants.MOD_ID, key);
            }
        });
    }
}
