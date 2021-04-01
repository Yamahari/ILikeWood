package yamahari.ilikewood.data.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;

public final class WoodenSawmillRecipe extends AbstractWoodenSawmillRecipe {
    public WoodenSawmillRecipe(final ResourceLocation id, final String group, final Ingredient ingredient,
                               final ItemStack result) {
        super(id, group, ingredient, result);
    }

    @Nonnull
    @Override
    public ItemStack getIcon() {
        return new ItemStack(WoodenBlocks.getBlock(WoodenObjectTypes.SAWMILL, VanillaWoodTypes.OAK));
    }
}
