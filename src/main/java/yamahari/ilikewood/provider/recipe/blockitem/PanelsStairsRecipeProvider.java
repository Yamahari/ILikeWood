package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class PanelsStairsRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsStairsRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.STAIRS);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectTypes.PANELS, ((IWooden) block).getWoodType());
        final IItemProvider planks =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

        ShapedRecipeBuilder
            .shapedRecipe(block, 4)
            .key('#', panels)
            .patternLine("#  ")
            .patternLine("## ")
            .patternLine("###")
            .addCriterion("has_panels", hasItem(panels))
            .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
            .build(consumer);

        sawmillingRecipe(Ingredient.fromItems(planks), block)
            .addCriterion("has_planks", hasItem(planks))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        planks.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        sawmillingRecipe(Ingredient.fromItems(panels), block)
            .addCriterion("has_panels", hasItem(panels))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        panels.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
    }
}
