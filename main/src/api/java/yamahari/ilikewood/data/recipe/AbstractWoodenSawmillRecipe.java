package yamahari.ilikewood.data.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.WoodenRecipeTypes;

import javax.annotation.Nonnull;

public abstract class AbstractWoodenSawmillRecipe extends SingleItemRecipe {
    public AbstractWoodenSawmillRecipe(final ResourceLocation id, final String group, final Ingredient ingredient,
                                       final ItemStack result) {
        super(
            WoodenRecipeTypes.SAWMILLING.get(),
            WoodenRecipeSerializers.SAWMILLING.get(),
            id,
            group,
            ingredient,
            result
        );
    }

    public final boolean matches(final Container inventory, @Nonnull final Level world) {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Nonnull
    @Override
    abstract public ItemStack getToastSymbol();
}
