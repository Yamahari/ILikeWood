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
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsSlabRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsSlabRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS_SLAB);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shaped(block, 6)
            .define('#', panels)
            .pattern("###")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodBlockTags.PANELS_SLABS.getName().getPath())
            .save(consumer);

        sawmillingRecipe(Ingredient.of(panels), block, 2)
            .unlocks("has_panels", has(panels))
            .save(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        panels.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final IItemProvider planks =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            ShapedRecipeBuilder
                .shaped(Objects.requireNonNull(planks))
                .define('S', block)
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_panels_slab", has(block))
                .group("ilikewood:planks")
                .save(consumer,
                    Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" +
                    block.getRegistryName().getPath());

            sawmillingRecipe(Ingredient.of(planks), block, 2)
                .unlocks("has_planks", has(planks))
                .save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            planks.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }
    }
}
