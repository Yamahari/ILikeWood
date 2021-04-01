package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class TorchRecipeProvider extends AbstractBlockItemRecipeProvider {
    public TorchRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.TORCH);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider stick = WoodenItems.getItem(WoodenObjectTypes.STICK, woodType);
        final Ingredient coals = Ingredient.fromTag(ItemTags.COALS);

        ShapedRecipeBuilder
            .shapedRecipe(block, 4)
            .key('I', stick)
            .key('#', coals)
            .patternLine("#")
            .patternLine("I")
            .addCriterion("has_coal", hasItem(ItemTags.COALS))
            .setGroup(ILikeWoodBlockTags.TORCHES.getName().getPath())
            .build(consumer);
    }
}
