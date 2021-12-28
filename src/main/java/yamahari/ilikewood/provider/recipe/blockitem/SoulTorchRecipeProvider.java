package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class SoulTorchRecipeProvider extends AbstractBlockItemRecipeProvider {
    public SoulTorchRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SOUL_TORCH);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);
        final Ingredient coals = Ingredient.of(ItemTags.COALS);
        final Ingredient soul = Ingredient.of(ItemTags.SOUL_FIRE_BASE_BLOCKS);

        ShapedRecipeBuilder.shaped(block, 4).define('I', stick).define('#', coals).define('S', soul)
            .pattern("#")
            .pattern("I")
            .pattern("S")
            .unlockedBy("has_soul_fire_base", has(ItemTags.SOUL_FIRE_BASE_BLOCKS))
            .group(ILikeWoodBlockTags.SOUL_TORCHES.getName().getPath())
            .save(consumer);
    }
}
