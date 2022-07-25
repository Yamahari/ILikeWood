package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class LadderRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public LadderRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.LADDER);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder.shaped(block, 3).define('I', stick).pattern("I I").pattern("III").pattern("I I").unlockedBy("has_stick",
            has(stick)
        ).group(String.format("%s:%s", Constants.MOD_ID, Constants.LADDER_PLURAL)).save(consumer,
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block))
        );
    }
}