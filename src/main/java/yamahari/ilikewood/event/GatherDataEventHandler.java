package yamahari.ilikewood.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import yamahari.ilikewood.provider.*;
import yamahari.ilikewood.provider.blockstate.*;
import yamahari.ilikewood.provider.recipe.blockitem.*;
import yamahari.ilikewood.provider.recipe.item.*;
import yamahari.ilikewood.provider.recipe.item.tiered.*;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT,
                                 Dist.DEDICATED_SERVER}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GatherDataEventHandler {
    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            generator.addProvider(new PanelsRecipeProvider(generator));
            generator.addProvider(new PanelsStairsRecipeProvider(generator));
            generator.addProvider(new PanelsSlabRecipeProvider(generator));
            generator.addProvider(new BarrelRecipeProvider(generator));
            generator.addProvider(new BookshelfRecipeProvider(generator));
            generator.addProvider(new ChestRecipeProvider(generator));
            generator.addProvider(new ComposterRecipeProvider(generator));
            generator.addProvider(new WallRecipeProvider(generator));
            generator.addProvider(new LadderRecipeProvider(generator));
            generator.addProvider(new TorchRecipeProvider(generator));
            generator.addProvider(new CraftingTableRecipeProvider(generator));
            generator.addProvider(new ScaffoldingRecipeProvider(generator));
            generator.addProvider(new LecternRecipeProvider(generator));
            generator.addProvider(new PostRecipeProvider(generator));
            generator.addProvider(new StickRecipeProvider(generator));
            generator.addProvider(new AxeRecipeProvider(generator));
            generator.addProvider(new HoeRecipeProvider(generator));
            generator.addProvider(new PickaxeRecipeProvider(generator));
            generator.addProvider(new ShovelRecipeProvider(generator));
            generator.addProvider(new SwordRecipeProvider(generator));
            generator.addProvider(new BowRecipeProvider(generator));
            generator.addProvider(new CrossbowRecipeProvider(generator));
            generator.addProvider(new ItemFrameRecipeProvider(generator));
            generator.addProvider(new SawmillRecipeProvider(generator));
            generator.addProvider(new BedRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator));
            generator.addProvider(new FishingRodRecipeProvider(generator));

            generator.addProvider(new ILikeWoodLootTableProvider(generator));
            final ILikeWoodBlockTagsProvider blockTagsProvider = new ILikeWoodBlockTagsProvider(generator, helper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ILikeWoodItemTagsProvider(generator, blockTagsProvider, helper));
        }

        if (event.includeClient()) {
            generator.addProvider(new PanelsBlockStateProvider(generator, helper));
            generator.addProvider(new PanelsStairsBlockStateProvider(generator, helper));
            generator.addProvider(new PanelsSlabBlockStateProvider(generator, helper));
            generator.addProvider(new BarrelBlockStateProvider(generator, helper));
            generator.addProvider(new BookshelfBlockStateProvider(generator, helper));
            generator.addProvider(new ChestBlockStateProvider(generator, helper));
            generator.addProvider(new ComposterBlockStateProvider(generator, helper));
            generator.addProvider(new WallBlockStateProvider(generator, helper));
            generator.addProvider(new LadderBlockStateProvider(generator, helper));
            generator.addProvider(new TorchBlockStateProvider(generator, helper));
            generator.addProvider(new WallTorchBlockStateProvider(generator, helper));
            generator.addProvider(new CraftingTableBlockStateProvider(generator, helper));
            generator.addProvider(new ScaffoldingBlockStateProvider(generator, helper));
            generator.addProvider(new LecternBlockStateProvider(generator, helper));
            generator.addProvider(new PostBlockStateProvider(generator, helper));
            generator.addProvider(new StrippedPostBlockStateProvider(generator, helper));
            generator.addProvider(new ItemFrameBlockStateProvider(generator, helper));
            generator.addProvider(new BedBlockStateProvider(generator, helper));
            generator.addProvider(new SawmillBlockStateProvider(generator, helper));

            generator.addProvider(new ILikeWoodItemModelProvider(generator, helper));
            generator.addProvider(new ILikeWoodLanguageProvider(generator, "en_us"));
        }
    }

    private GatherDataEventHandler() {
    }
}
