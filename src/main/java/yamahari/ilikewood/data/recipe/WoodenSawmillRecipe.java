package yamahari.ilikewood.data.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenSawmillRecipe extends AbstractWoodenSawmillRecipe {
    public WoodenSawmillRecipe(final ResourceLocation id, final String group, final Ingredient ingredient,
                               final ItemStack result) {
        super(id, group, ingredient, result);
    }

    @Nonnull
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ILikeWood.BLOCK_REGISTRY.getObject(VanillaWoodTypes.OAK, WoodenBlockType.SAWMILL));
    }
}
