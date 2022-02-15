package yamahari.ilikewood.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import yamahari.ilikewood.provider.*;
import yamahari.ilikewood.provider.blockstate.*;
import yamahari.ilikewood.provider.itemmodel.*;
import yamahari.ilikewood.provider.itemmodel.blockitem.*;
import yamahari.ilikewood.provider.itemmodel.tiered.*;
import yamahari.ilikewood.provider.recipe.blockitem.*;
import yamahari.ilikewood.provider.recipe.item.*;
import yamahari.ilikewood.provider.recipe.item.tiered.*;
import yamahari.ilikewood.provider.texture.block.*;
import yamahari.ilikewood.provider.texture.item.*;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT, Dist.DEDICATED_SERVER}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GatherDataEventHandler {
    private GatherDataEventHandler() {
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

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
            generator.addProvider(new SoulTorchRecipeProvider(generator));
            generator.addProvider(new ChairRecipeProvider(generator));
            generator.addProvider(new TableRecipeProvider(generator));
            generator.addProvider(new StoolRecipeProvider(generator));
            generator.addProvider(new SingleDresserRecipeProvider(generator));

            generator.addProvider(new ILikeWoodLootTableProvider(generator));
            final ILikeWoodBlockTagsProvider blockTagsProvider = new ILikeWoodBlockTagsProvider(generator, helper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ILikeWoodItemTagsProvider(generator, blockTagsProvider, helper));
        }

        if (event.includeClient()) {
            generator.addProvider(new ChestTextureProvider(generator, helper));
            generator.addProvider(new BarrelTextureProvider(generator, helper));
            generator.addProvider(new BedTextureProvider(generator, helper));
            generator.addProvider(new BookshelfTextureProvider(generator, helper));
            generator.addProvider(new ComposterTextureProvider(generator, helper));
            generator.addProvider(new LadderTextureProvider(generator, helper));
            generator.addProvider(new LecternTextureProvider(generator, helper));
            generator.addProvider(new LogPileTextureProvider(generator, helper));
            generator.addProvider(new ScaffoldingTextureProvider(generator, helper));
            generator.addProvider(new TorchTextureProvider(generator, helper));
            generator.addProvider(new PostTextureProvider(generator, helper));
            generator.addProvider(new StrippedPostTextureProvider(generator, helper));
            generator.addProvider(new BowTextureProvider(generator, helper));
            generator.addProvider(new CrossbowTextureProvider(generator, helper));
            generator.addProvider(new FishingRodTextureProvider(generator, helper));
            generator.addProvider(new ItemFrameTextureProvider(generator, helper));
            generator.addProvider(new StickAndToolTextureProvider(generator, helper));

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
            generator.addProvider(new SoulTorchBlockStateProvider(generator, helper));
            generator.addProvider(new WallSoulTorchBlockStateProvider(generator, helper));
            generator.addProvider(new ChairBlockStateProvider(generator, helper));
            generator.addProvider(new TableBlockStateProvider(generator, helper));
            generator.addProvider(new StoolBlockStateProvider(generator, helper));
            generator.addProvider(new SingleDresserBlockStateProvider(generator, helper));

            generator.addProvider(new BarrelBlockItemModelProvider(generator, helper));
            generator.addProvider(new BedBlockItemModelProvider(generator, helper));
            generator.addProvider(new BookshelfBlockItemModelProvider(generator, helper));
            generator.addProvider(new ChestBlockItemModelProvider(generator, helper));
            generator.addProvider(new ComposterBlockItemModelProvider(generator, helper));
            generator.addProvider(new CraftingTableBlockItemModelProvider(generator, helper));
            generator.addProvider(new LadderBlockItemModelProvider(generator, helper));
            generator.addProvider(new LecternBlockItemModelProvider(generator, helper));
            generator.addProvider(new PanelsBlockItemModelProvider(generator, helper));
            generator.addProvider(new PanelsSlabBlockItemModelProvider(generator, helper));
            generator.addProvider(new PanelsStairsBlockItemModelProvider(generator, helper));
            generator.addProvider(new PostBlockItemModelProvider(generator, helper));
            generator.addProvider(new SawmillBlockItemModelProvider(generator, helper));
            generator.addProvider(new ScaffoldingBlockItemModelProvider(generator, helper));
            generator.addProvider(new SoulTorchBlockItemModelProvider(generator, helper));
            generator.addProvider(new StrippedPostBlockItemModelProvider(generator, helper));
            generator.addProvider(new TorchBlockItemModelProvider(generator, helper));
            generator.addProvider(new WallBlockItemModelProvider(generator, helper));
            generator.addProvider(new AxeTieredItemModelProvider(generator, helper));
            generator.addProvider(new HoeTieredItemModelProvider(generator, helper));
            generator.addProvider(new PickaxeTieredItemModelProvider(generator, helper));
            generator.addProvider(new ShovelTieredItemModelProvider(generator, helper));
            generator.addProvider(new SwordTieredItemModelProvider(generator, helper));
            generator.addProvider(new BowItemModelProvider(generator, helper));
            generator.addProvider(new CrossbowItemModelProvider(generator, helper));
            generator.addProvider(new FishingRodItemModelProvider(generator, helper));
            generator.addProvider(new ItemFrameItemModelProvider(generator, helper));
            generator.addProvider(new StickItemModelProvider(generator, helper));
            generator.addProvider(new ChairBlockItemModelProvider(generator, helper));
            generator.addProvider(new TableBlockItemModelProvider(generator, helper));
            generator.addProvider(new StoolBlockItemModelProvider(generator, helper));
            generator.addProvider(new SingleDresserBlockItemModelProvider(generator, helper));

            generator.addProvider(new ILikeWoodLanguageProvider(generator, "en_us"));
        }
    }
}
