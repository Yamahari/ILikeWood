package yamahari.ilikewood.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.ILikeWoodLanguageProvider;
import yamahari.ilikewood.provider.ILikeWoodLootTableProvider;
import yamahari.ilikewood.provider.PackMCMetaProvider;
import yamahari.ilikewood.provider.blockstate.*;
import yamahari.ilikewood.provider.itemmodel.*;
import yamahari.ilikewood.provider.itemmodel.blockitem.*;
import yamahari.ilikewood.provider.itemmodel.tiered.*;
import yamahari.ilikewood.provider.recipe.blockitem.*;
import yamahari.ilikewood.provider.recipe.item.*;
import yamahari.ilikewood.provider.recipe.item.tiered.*;
import yamahari.ilikewood.provider.tag.block.*;
import yamahari.ilikewood.provider.tag.item.*;
import yamahari.ilikewood.provider.texture.block.*;
import yamahari.ilikewood.provider.texture.item.*;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
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

            generator.addProvider(new BarrelBlockTagsProvider(generator, helper));
            generator.addProvider(new ChestBlockTagsProvider(generator, helper));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.COMPOSTER_PLURAL,
                WoodenBlockType.BARREL,
                ILikeWoodBlockTags.COMPOSTER));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.BOOKSHELF_PLURAL,
                WoodenBlockType.BOOKSHELF,
                ILikeWoodBlockTags.BOOKSHELVES));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.PANELS_PLURAL,
                WoodenBlockType.PANELS,
                ILikeWoodBlockTags.PANELS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.PANELS_STAIRS_PLURAL,
                WoodenBlockType.PANELS_STAIRS,
                ILikeWoodBlockTags.PANELS_STAIRS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.PANELS_SLAB_PLURAL,
                WoodenBlockType.PANELS_SLAB,
                ILikeWoodBlockTags.PANELS_SLABS));
            generator.addProvider(new PostBlockTagsProvider(generator, helper));
            generator.addProvider(new WallBlockTagsProvider(generator, helper));
            generator.addProvider(new ClimbableBlockTagsProvider(generator,
                helper,
                Constants.LADDER_PLURAL,
                WoodenBlockType.LADDER,
                ILikeWoodBlockTags.LADDERS));
            generator.addProvider(new TorchBlockTagsProvider(generator, helper));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.CRAFTING_TABLE_PLURAL,
                WoodenBlockType.CRAFTING_TABLE,
                ILikeWoodBlockTags.CRAFTING_TABLES));
            generator.addProvider(new ClimbableBlockTagsProvider(generator,
                helper,
                Constants.SCAFFOLDING_PLURAL,
                WoodenBlockType.SCAFFOLDING,
                ILikeWoodBlockTags.SCAFFOLDINGS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.LECTERN_PLURAL,
                WoodenBlockType.LECTERN,
                ILikeWoodBlockTags.LECTERNS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.BEDS,
                WoodenBlockType.WHITE_BED,
                ILikeWoodBlockTags.BEDS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.SAWMILL_PLURAL,
                WoodenBlockType.SAWMILL,
                ILikeWoodBlockTags.SAWMILLS));
            generator.addProvider(new SoulTorchBlockTagsProvider(generator, helper));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.CHAIR_PLURAL,
                WoodenBlockType.CHAIR,
                ILikeWoodBlockTags.CHAIRS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.TABLE_PLURAL,
                WoodenBlockType.TABLE,
                ILikeWoodBlockTags.TABLES));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.STOOL_PLURAL,
                WoodenBlockType.STOOL,
                ILikeWoodBlockTags.STOOLS));
            generator.addProvider(new DefaultBlockTagsProvider(generator,
                helper,
                Constants.SINGLE_DRESSER_PLURAL,
                WoodenBlockType.SINGLE_DRESSER,
                ILikeWoodBlockTags.SINGLE_DRESSERS));

            final AbstractBlockTagsProvider dummy = new DummyBlockTagsProvider(generator, helper);

            generator.addProvider(new BarrelItemTagsProvider(generator, dummy, helper));
            generator.addProvider(new ChestItemTagsProvider(generator, dummy, helper));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.COMPOSTER_PLURAL,
                WoodenBlockType.COMPOSTER,
                ILikeWoodItemTags.COMPOSTER));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.BOOKSHELF_PLURAL,
                WoodenBlockType.BOOKSHELF,
                ILikeWoodItemTags.BOOKSHELVES));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.PANELS_PLURAL,
                WoodenBlockType.PANELS,
                ILikeWoodItemTags.PANELS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.PANELS_STAIRS_PLURAL,
                WoodenBlockType.PANELS_STAIRS,
                ILikeWoodItemTags.PANELS_STAIRS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.PANELS_SLAB_PLURAL,
                WoodenBlockType.PANELS_SLAB,
                ILikeWoodItemTags.PANELS_SLABS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.WALL_PLURAL,
                WoodenBlockType.WALL,
                ILikeWoodItemTags.WALLS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.LADDER_PLURAL,
                WoodenBlockType.LADDER,
                ILikeWoodItemTags.LADDERS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.TORCH_PLURAL,
                WoodenBlockType.TORCH,
                ILikeWoodItemTags.TORCHES));
            generator.addProvider(new StickItemTagsProvider(generator, dummy, helper));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.CRAFTING_TABLE_PLURAL,
                WoodenBlockType.CRAFTING_TABLE,
                ILikeWoodItemTags.CRAFTING_TABLES));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.SCAFFOLDING_PLURAL,
                WoodenBlockType.SCAFFOLDING,
                ILikeWoodItemTags.SCAFFOLDINGS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.LECTERN_PLURAL,
                WoodenBlockType.LECTERN,
                ILikeWoodItemTags.LECTERNS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.POST_PLURAL,
                WoodenBlockType.POST,
                ILikeWoodItemTags.POSTS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.POST_PLURAL,
                WoodenBlockType.STRIPPED_POST,
                ILikeWoodItemTags.STRIPPED_POSTS));
            generator.addProvider(new ItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.BOW_PLURAL,
                WoodenItemType.BOW,
                ILikeWoodItemTags.BOWS));
            generator.addProvider(new ItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.CROSSBOW_PLURAL,
                WoodenItemType.CROSSBOW,
                ILikeWoodItemTags.CROSSBOWS));
            generator.addProvider(new ItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.ITEM_FRAME_PLURAL,
                WoodenItemType.ITEM_FRAME,
                ILikeWoodItemTags.ITEM_FRAMES));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.BEDS,
                WoodenBlockType.WHITE_BED,
                ILikeWoodItemTags.BEDS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.SAWMILL_PLURAL,
                WoodenBlockType.SAWMILL,
                ILikeWoodItemTags.SAWMILLS));
            generator.addProvider(new ItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.FISHING_ROD_PLURAL,
                WoodenItemType.FISHING_ROD,
                ILikeWoodItemTags.FISHING_RODS));
            generator.addProvider(new SoulTorchItemTagsProvider(generator, dummy, helper));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.CHAIR_PLURAL,
                WoodenBlockType.CHAIR,
                ILikeWoodItemTags.CHAIRS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.TABLE_PLURAL,
                WoodenBlockType.TABLE,
                ILikeWoodItemTags.TABLES));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.STOOL_PLURAL,
                WoodenBlockType.STOOL,
                ILikeWoodItemTags.STOOLS));
            generator.addProvider(new BlockItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.SINGLE_DRESSER_PLURAL,
                WoodenBlockType.SINGLE_DRESSER,
                ILikeWoodItemTags.SINGLE_DRESSER));
            generator.addProvider(new TieredItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.AXE_PLURAL,
                WoodenTieredItemType.AXE,
                ILikeWoodItemTags.AXES));
            generator.addProvider(new TieredItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.HOE_PLURAL,
                WoodenTieredItemType.HOE,
                ILikeWoodItemTags.HOES));
            generator.addProvider(new TieredItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.PICKAXE_PLURAL,
                WoodenTieredItemType.PICKAXE,
                ILikeWoodItemTags.PICKAXES));
            generator.addProvider(new TieredItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.SHOVEL_PLURAL,
                WoodenTieredItemType.SHOVEL,
                ILikeWoodItemTags.SHOVELS));
            generator.addProvider(new TieredItemItemTagsProvider(generator,
                dummy,
                helper,
                Constants.SWORD_PLURAL,
                WoodenTieredItemType.SWORD,
                ILikeWoodItemTags.SWORDS));
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
