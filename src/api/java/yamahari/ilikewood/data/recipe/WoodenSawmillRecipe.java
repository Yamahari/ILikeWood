package yamahari.ilikewood.data.recipe;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.WoodenRecipeTypes;

public class WoodenSawmillRecipe extends SingleItemRecipe {
    public WoodenSawmillRecipe(final ResourceLocation id, final String group, final Ingredient ingredient,
                               final ItemStack result) {
        super(WoodenRecipeTypes.SAWMILLING, WoodenRecipeSerializers.SAWMILLING.get(), id, group, ingredient, result);
    }

    public boolean matches(final IInventory inventory, @SuppressWarnings("NullableProblems") final World world) {
        return this.ingredient.test(inventory.getStackInSlot(0));
    }

    @SuppressWarnings("NullableProblems")
    public ItemStack getIcon() {
        return new ItemStack(Blocks.STONECUTTER); // TODO return actual icon
    }
}
