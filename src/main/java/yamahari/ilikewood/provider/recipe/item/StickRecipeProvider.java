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
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

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
            .shaped(item, 4)
            .define('#', panels)
            .pattern("#")
            .pattern("#")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodItemTags.STICKS.getName().getPath())
            .save(consumer);

        sawmillingRecipe(Ingredient.of(panels), item, 2)
            .unlocks("has_panels", has(panels))
            .save(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(item.getRegistryName().getPath(),
                        "from",
                        panels.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final IItemProvider planks =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            sawmillingRecipe(Ingredient.of(planks), item, 2)
                .unlocks("has_planks", has(planks))
                .save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(item.getRegistryName().getPath(),
                            "from",
                            planks.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }
    }
}
