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
import yamahari.ilikewood.provider.recipe.AbstractBlockItemRecipeProvider;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsSlabRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsSlabRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.SLAB);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
        final IItemProvider planks =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

        ShapedRecipeBuilder
            .shapedRecipe(block, 6)
            .key('#', panels)
            .patternLine("###")
            .addCriterion("has_panels", hasItem(panels))
            .setGroup(ILikeWoodBlockTags.PANELS_SLABS.getName().getPath())
            .build(consumer);

        ShapedRecipeBuilder
            .shapedRecipe(Objects.requireNonNull(planks))
            .key('S', block)
            .patternLine("S")
            .patternLine("S")
            .addCriterion("has_panels_slab", hasItem(block))
            .setGroup("ilikewood:planks")
            .build(consumer,
                Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" +
                block.getRegistryName().getPath());

        sawmillingRecipe(Ingredient.fromItems(planks), block, 2)
            .addCriterion("has_planks", hasItem(planks))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        planks.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        sawmillingRecipe(Ingredient.fromItems(panels), block, 2)
            .addCriterion("has_panels", hasItem(panels))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        panels.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
    }
}
