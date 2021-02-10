package yamahari.ilikewood.data.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.WoodenRecipeTypes;

import javax.annotation.Nonnull;

public abstract class AbstractWoodenSawmillRecipe extends SingleItemRecipe {
    public AbstractWoodenSawmillRecipe(final ResourceLocation id, final String group, final Ingredient ingredient,
                                       final ItemStack result) {
        super(WoodenRecipeTypes.SAWMILLING, WoodenRecipeSerializers.SAWMILLING.get(), id, group, ingredient, result);
    }

    public final boolean matches(final IInventory inventory, @Nonnull final World world) {
        return this.ingredient.test(inventory.getStackInSlot(0));
    }

    @Nonnull
    @Override
    abstract public ItemStack getIcon();
}
