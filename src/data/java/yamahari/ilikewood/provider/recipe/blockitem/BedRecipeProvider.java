package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class BedRecipeProvider extends AbstractBlockItemRecipeProvider {
    public BedRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.WHITE_BED);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
        final IItemProvider wool =
            Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "WOOL"), Blocks.class);
        final IItemProvider panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
        final IItemProvider dye =
            Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "DYE"), Items.class);

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('#', wool)
            .key('X', panels)
            .patternLine("###")
            .patternLine("XXX")
            .addCriterion("has_wool", hasItem(wool))
            .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
            .build(consumer);

        if (!color.equals(DyeColor.WHITE)) {
            try {
                final IItemProvider whiteBed = ILikeWood.getBlock(woodType, WoodenBlockType.WHITE_BED);
                ShapelessRecipeBuilder
                    .shapelessRecipe(block)
                    .addIngredient(whiteBed)
                    .addIngredient(dye)
                    .addCriterion("has_dye", hasItem(dye))
                    .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
                    .build(consumer,
                        new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(),
                                "from",
                                whiteBed.asItem().getRegistryName().getPath())));
            } catch (final IllegalArgumentException ignored) {
                ILikeWood.LOGGER.info("White bed was not present for white<->color recipe!");
            }
        } else {
            WoodenBlockType
                .getBeds()
                .filter(bedBlockType -> !bedBlockType.equals(WoodenBlockType.WHITE_BED))
                .forEach(bedBlockType -> {
                    try {
                        final IItemProvider coloredBed = ILikeWood.getBlock(woodType, bedBlockType);
                        ShapelessRecipeBuilder
                            .shapelessRecipe(block)
                            .addIngredient(coloredBed)
                            .addIngredient(dye)
                            .addCriterion("has_dye", hasItem(dye))
                            .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
                            .build(consumer,
                                new ResourceLocation(Constants.MOD_ID,
                                    Util.toRegistryName(block.getRegistryName().getPath(),
                                        "from",
                                        coloredBed.asItem().getRegistryName().getPath())));
                    } catch (final IllegalArgumentException ignored) {
                        ILikeWood.LOGGER.info("Colored bed was not present for white<->color recipe!");
                    }
                });
        }
    }
}
