package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class ChestRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public ChestRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.CHEST);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike panels = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS);

        ShapedRecipeBuilder.shaped(block).define('#', panels).pattern("###").pattern("# #").pattern("###").unlockedBy("has_panels",
            has(panels)
        ).group(String.format("%s:%s", Constants.MOD_ID, Constants.CHEST_PLURAL)).save(consumer,
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block))
        );
    }
}