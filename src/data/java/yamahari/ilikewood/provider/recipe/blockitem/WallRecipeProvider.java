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

public final class WallRecipeProvider extends AbstractBlockItemRecipeProvider {
    public WallRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.WALL);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
            final IItemProvider log =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());

            ShapedRecipeBuilder
                .shapedRecipe(block, 6)
                .key('#', Objects.requireNonNull(log))
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_log", hasItem(log))
                .setGroup(ILikeWoodBlockTags.WALLS.getName().getPath())
                .build(consumer);

            sawmillingRecipe(Ingredient.fromItems(log), block)
                .addCriterion("has_log", hasItem(log))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            log.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }
    }
}
