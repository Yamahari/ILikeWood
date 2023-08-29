package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class BedRecipeProvider extends AbstractBlockItemRecipeProvider {
    public BedRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.WHITE_BED);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block) {
        final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
        final ItemLike wool = Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "WOOL"), Blocks.class);
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
        final ItemLike dye = Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "DYE"), Items.class);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
            .define('#', Objects.requireNonNull(wool))
            .define('X', panels)
            .pattern("###")
            .pattern("XXX")
            .unlockedBy("has_wool", has(wool))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.BEDS))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));

        if (!color.equals(DyeColor.WHITE)) {
            try {
                final ItemLike whiteBed = ILikeWood.getBlock(woodType, WoodenBlockType.WHITE_BED);
                ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, block)
                    .requires(whiteBed)
                    .requires(Objects.requireNonNull(dye))
                    .unlockedBy("has_dye", has(dye))
                    .group(String.format("%s:%s", Constants.MOD_ID, Constants.BEDS))
                    .save(consumer,
                        new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(ForgeRegistries.BLOCKS.getKey(block).getPath(),
                                "from",
                                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(whiteBed.asItem())).getPath())));
            }
            catch (final IllegalArgumentException ignored) {
                ILikeWood.LOGGER.info("White bed was not present for white<->color recipe!");
            }
        }
        else {
            WoodenBlockType.getBeds().filter(bedBlockType -> !bedBlockType.equals(WoodenBlockType.WHITE_BED)).forEach(bedBlockType -> {
                try {
                    final ItemLike coloredBed = ILikeWood.getBlock(woodType, bedBlockType);
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, block)
                        .requires(coloredBed)
                        .requires(Objects.requireNonNull(dye))
                        .unlockedBy("has_dye", has(dye))
                        .group(String.format("%s:%s", Constants.MOD_ID, Constants.BEDS))
                        .save(consumer,
                            new ResourceLocation(Constants.MOD_ID,
                                Util.toRegistryName(ForgeRegistries.BLOCKS.getKey(block).getPath(),
                                    "from",
                                    Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(coloredBed.asItem())).getPath())));
                }
                catch (final IllegalArgumentException ignored) {
                    ILikeWood.LOGGER.info("Colored bed was not present for white<->color recipe!");
                }
            });
        }
    }
}