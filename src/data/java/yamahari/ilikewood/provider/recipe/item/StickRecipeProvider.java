package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class StickRecipeProvider extends AbstractItemRecipeProvider {
    public StickRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.STICK);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shapedRecipe(item, 4)
            .key('#', panels)
            .patternLine("#")
            .patternLine("#")
            .addCriterion("has_panels", hasItem(panels))
            .setGroup(ILikeWoodItemTags.STICKS.getName().getPath())
            .build(consumer);

        sawmillingRecipe(Ingredient.fromItems(panels), item, 2)
            .addCriterion("has_panels", hasItem(panels))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(item.getRegistryName().getPath(),
                        "from",
                        panels.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final IItemProvider planks =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            sawmillingRecipe(Ingredient.fromItems(planks), item, 2)
                .addCriterion("has_planks", hasItem(planks))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(item.getRegistryName().getPath(),
                            "from",
                            planks.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }
    }
}
