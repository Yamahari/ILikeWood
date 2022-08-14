package yamahari.ilikewood.event;

import net.minecraft.DetectedVersion;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import yamahari.ilikewood.data.loot.BedLoot;
import yamahari.ilikewood.data.loot.BookshelfLoot;
import yamahari.ilikewood.data.loot.CampfireLoot;
import yamahari.ilikewood.data.loot.ComposterLoot;
import yamahari.ilikewood.data.loot.DropSelfLoot;
import yamahari.ilikewood.data.loot.NameableBlockEntityLoot;
import yamahari.ilikewood.data.loot.PanelsSlabLoot;
import yamahari.ilikewood.data.loot.PostLoot;
import yamahari.ilikewood.data.loot.SawmillLoot;
import yamahari.ilikewood.data.loot.TorchLoot;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.PackMCMetaProvider;
import yamahari.ilikewood.provider.blockstate.BarrelBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.BedBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.BookshelfBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.CampfireBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.ChairBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.ChestBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.ComposterBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.CraftingTableBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.ItemFrameBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.LadderBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.LecternBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.LogPileBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.PanelsBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.PanelsSlabBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.PanelsStairsBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.PostBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.SawmillBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.ScaffoldingBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.SingleDresserBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.SoulCampfireBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.SoulTorchBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.StoolBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.StrippedPostBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.TableBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.TorchBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.WallBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.WallSoulTorchBlockStateProvider;
import yamahari.ilikewood.provider.blockstate.WallTorchBlockStateProvider;
import yamahari.ilikewood.provider.itemmodel.BowItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.CrossbowItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.FishingRodItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.ItemFrameItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.StickItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.BarrelBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.BedBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.BookshelfBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.CampfireBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.ChairBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.ChestBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.ComposterBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.CraftingTableBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.LadderBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.LecternBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.LogPileBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.PanelsBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.PanelsSlabBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.PanelsStairsBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.PostBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.SawmillBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.ScaffoldingBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.SingleDresserBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.SoulCampfireBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.SoulTorchBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.StoolBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.StrippedPostBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.TableBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.TorchBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.blockitem.WallBlockItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.tiered.AxeTieredItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.tiered.HoeTieredItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.tiered.PickaxeTieredItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.tiered.ShovelTieredItemModelProvider;
import yamahari.ilikewood.provider.itemmodel.tiered.SwordTieredItemModelProvider;
import yamahari.ilikewood.provider.lang.BedLanguageProvider;
import yamahari.ilikewood.provider.lang.CampfireLanguageProvider;
import yamahari.ilikewood.provider.lang.ContainerBlockLanguageProvider;
import yamahari.ilikewood.provider.lang.DefaultLanguageProvider;
import yamahari.ilikewood.provider.lang.PostLanguageProvider;
import yamahari.ilikewood.provider.lang.TorchLanguageProvider;
import yamahari.ilikewood.provider.loot.DefaultBlockLootTableProvider;
import yamahari.ilikewood.provider.recipe.blockitem.BarrelRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.BedRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.BookshelfRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.CampfireRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.ChairRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.ChestRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.ComposterRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.CraftingTableRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.LadderRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.LecternRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.LogPileRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.PanelsRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.PanelsSlabRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.PanelsStairsRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.PostRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.SawmillRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.ScaffoldingRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.SingleDresserRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.SoulCampfireRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.SoulTorchRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.StoolRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.TableRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.TorchRecipeProvider;
import yamahari.ilikewood.provider.recipe.blockitem.WallRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.BowRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.CrossbowRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.FishingRodRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.ItemFrameRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.StickRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.AxeRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.HoeRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.NetheriteTieredItemRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.PickaxeRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.ShovelRecipeProvider;
import yamahari.ilikewood.provider.recipe.item.tiered.SwordRecipeProvider;
import yamahari.ilikewood.provider.recipe.sawmilling.SawmillingRecipeProvider;
import yamahari.ilikewood.provider.tag.block.BarrelBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.CampfireBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.ChestBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.ClimbableBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.DefaultBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.DummyBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.PostBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.TorchBlockTagsProvider;
import yamahari.ilikewood.provider.tag.block.WallBlockTagsProvider;
import yamahari.ilikewood.provider.tag.item.BarrelItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.CampfireItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.ChestItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.DefaultBlockItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.DefaultItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.PostItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.StickItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.TieredItemItemTagsProvider;
import yamahari.ilikewood.provider.tag.item.TorchItemTagsProvider;
import yamahari.ilikewood.provider.texture.block.BarrelTextureProvider;
import yamahari.ilikewood.provider.texture.block.BedTextureProvider;
import yamahari.ilikewood.provider.texture.block.BookshelfTextureProvider;
import yamahari.ilikewood.provider.texture.block.ChestTextureProvider;
import yamahari.ilikewood.provider.texture.block.ComposterTextureProvider;
import yamahari.ilikewood.provider.texture.block.LadderTextureProvider;
import yamahari.ilikewood.provider.texture.block.LecternTextureProvider;
import yamahari.ilikewood.provider.texture.block.LogPileTextureProvider;
import yamahari.ilikewood.provider.texture.block.PostTextureProvider;
import yamahari.ilikewood.provider.texture.block.ScaffoldingTextureProvider;
import yamahari.ilikewood.provider.texture.block.TorchTextureProvider;
import yamahari.ilikewood.provider.texture.item.BowTextureProvider;
import yamahari.ilikewood.provider.texture.item.CrossbowTextureProvider;
import yamahari.ilikewood.provider.texture.item.FishingRodTextureProvider;
import yamahari.ilikewood.provider.texture.item.ItemFrameTextureProvider;
import yamahari.ilikewood.provider.texture.item.StickTextureProvider;
import yamahari.ilikewood.provider.texture.item.tiered.ToolTextureProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import java.io.IOException;
import java.util.Collections;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT,
    Dist.DEDICATED_SERVER
}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GatherDataEventHandler
{
    private GatherDataEventHandler()
    {
    }

    private static GatherDataEvent.DataGeneratorConfig getConfig(final GatherDataEvent event)
    {
        return ObfuscationReflectionHelper.getPrivateValue(GatherDataEvent.class, event, "config");
    }

    private static DataGenerator makeGenerator(
        final GatherDataEvent.DataGeneratorConfig config,
        final String root,
        boolean shouldExecute
    )
    {
        return config.makeGenerator(
            outputPath -> outputPath.getParent().resolve("data").resolve(String.format("%s_resources", Constants.MOD_ID)).resolve(root), shouldExecute);
    }

    private static void makePanelsData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.PANELS_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));

        generator.addProvider(event.includeServer(), new PanelsRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.PANELS_PLURAL, WoodenBlockType.PANELS, ILikeWoodBlockTags.PANELS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.PANELS_PLURAL, WoodenBlockType.PANELS,
                ILikeWoodItemTags.PANELS
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.PANELS), Constants.PANELS_PLURAL)
        );

        generator.addProvider(event.includeClient(), new PanelsBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new PanelsBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.PANELS));

    }

    private static void makePanelsStairsData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.PANELS_STAIRS_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new PanelsStairsRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.PANELS_STAIRS_PLURAL, WoodenBlockType.PANELS_STAIRS, ILikeWoodBlockTags.PANELS_STAIRS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.PANELS_STAIRS_PLURAL,
                WoodenBlockType.PANELS_STAIRS, ILikeWoodItemTags.PANELS_STAIRS
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.PANELS_STAIRS), Constants.PANELS_STAIRS_PLURAL)
        );


        generator.addProvider(event.includeClient(), new PanelsStairsBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new PanelsStairsBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.PANELS_STAIRS));

    }

    private static void makePanelsSlabData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.PANELS_SLAB_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new PanelsSlabRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.PANELS_SLAB_PLURAL, WoodenBlockType.PANELS_SLAB, ILikeWoodBlockTags.PANELS_SLABS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.PANELS_SLAB_PLURAL, WoodenBlockType.PANELS_SLAB,
                ILikeWoodItemTags.PANELS_SLABS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, PanelsSlabLoot::new, Constants.PANELS_SLAB_PLURAL));


        generator.addProvider(event.includeClient(), new PanelsSlabBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new PanelsSlabBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.PANELS_SLAB));

    }

    private static void makeBarrelData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BARREL_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new BarrelTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new BarrelRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new BarrelBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(), new BarrelItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new NameableBlockEntityLoot(WoodenBlockType.BARREL), Constants.BARREL_PLURAL)
        );


        generator.addProvider(event.includeClient(), new BarrelBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new BarrelBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ContainerBlockLanguageProvider(generator, WoodenBlockType.BARREL));

    }

    private static void makeBookshelfData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BOOKSHELF_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new BookshelfTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new BookshelfRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.BOOKSHELF_PLURAL, WoodenBlockType.BOOKSHELF, ILikeWoodBlockTags.BOOKSHELVES)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.BOOKSHELF_PLURAL, WoodenBlockType.BOOKSHELF,
                ILikeWoodItemTags.BOOKSHELVES
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, BookshelfLoot::new, Constants.BOOKSHELF_PLURAL));


        generator.addProvider(event.includeClient(), new BookshelfBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new BookshelfBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.BOOKSHELF));

    }

    private static void makeChestData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.CHEST_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ChestTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ChestRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new ChestBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(), new ChestItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new NameableBlockEntityLoot(WoodenBlockType.CHEST), Constants.CHEST_PLURAL)
        );


        generator.addProvider(event.includeClient(), new ChestBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ChestBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ContainerBlockLanguageProvider(generator, WoodenBlockType.CHEST));

    }

    private static void makeComposterData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.COMPOSTER_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ComposterTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ComposterRecipeProvider(generator));
        generator.addProvider(
            event.includeServer(), new DefaultBlockTagsProvider(generator, helper, Constants.COMPOSTER_PLURAL, WoodenBlockType.COMPOSTER, ILikeWoodBlockTags.COMPOSTER));
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.COMPOSTER_PLURAL, WoodenBlockType.COMPOSTER,
                ILikeWoodItemTags.COMPOSTER
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, ComposterLoot::new, Constants.COMPOSTER_PLURAL));


        generator.addProvider(event.includeClient(), new ComposterBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ComposterBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.COMPOSTER));

    }

    private static void makeWallData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.WALL_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new WallRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new WallBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.WALL_PLURAL, WoodenBlockType.WALL,
                ILikeWoodItemTags.WALLS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.WALL), Constants.WALL_PLURAL));


        generator.addProvider(event.includeClient(), new WallBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new WallBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.WALL));

    }

    private static void makeLadderData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LADDER_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new LadderTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new LadderRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new ClimbableBlockTagsProvider(generator, helper, Constants.LADDER_PLURAL, WoodenBlockType.LADDER, ILikeWoodBlockTags.LADDERS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.LADDER_PLURAL, WoodenBlockType.LADDER,
                ILikeWoodItemTags.LADDERS
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.LADDER), Constants.LADDER_PLURAL)
        );


        generator.addProvider(event.includeClient(), new LadderBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new LadderBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.LADDER));

    }

    private static void makeTorchData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.TORCH_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        // TODO maybe merge all torch & soul torch providers

        textureGenerator.addProvider(true, new TorchTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new TorchRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SoulTorchRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new TorchBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(), new TorchItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, TorchLoot::new, Constants.TORCH_PLURAL));


        generator.addProvider(event.includeClient(), new TorchBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new WallTorchBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SoulTorchBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new WallSoulTorchBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new TorchBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SoulTorchBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new TorchLanguageProvider(generator));

    }

    private static void makeCraftingTableData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.CRAFTING_TABLE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new CraftingTableRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.CRAFTING_TABLE_PLURAL, WoodenBlockType.CRAFTING_TABLE, ILikeWoodBlockTags.CRAFTING_TABLES)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.CRAFTING_TABLE_PLURAL,
                WoodenBlockType.CRAFTING_TABLE, ILikeWoodItemTags.CRAFTING_TABLES
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.CRAFTING_TABLE), Constants.CRAFTING_TABLE_PLURAL)
        );


        generator.addProvider(event.includeClient(), new CraftingTableBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new CraftingTableBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ContainerBlockLanguageProvider(generator, WoodenBlockType.CRAFTING_TABLE));

    }

    private static void makeScaffoldingData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SCAFFOLDING_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ScaffoldingTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ScaffoldingRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new ClimbableBlockTagsProvider(generator, helper, Constants.SCAFFOLDING_PLURAL, WoodenBlockType.SCAFFOLDING, ILikeWoodBlockTags.SCAFFOLDINGS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.SCAFFOLDING_PLURAL, WoodenBlockType.SCAFFOLDING,
                ILikeWoodItemTags.SCAFFOLDINGS
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.SCAFFOLDING), Constants.SCAFFOLDING_PLURAL)
        );


        generator.addProvider(event.includeClient(), new ScaffoldingBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ScaffoldingBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.SCAFFOLDING));

    }

    private static void makeLecternData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LECTERN_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new LecternTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new LecternRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.LECTERN_PLURAL, WoodenBlockType.LECTERN, ILikeWoodBlockTags.LECTERNS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.LECTERN_PLURAL, WoodenBlockType.LECTERN,
                ILikeWoodItemTags.LECTERNS
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new NameableBlockEntityLoot(WoodenBlockType.LECTERN), Constants.LECTERN_PLURAL)
        );


        generator.addProvider(event.includeClient(), new LecternBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new LecternBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ContainerBlockLanguageProvider(generator, WoodenBlockType.LECTERN));

    }

    private static void makePostData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.POST_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new PostTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new PostRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new PostBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(), new PostItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, PostLoot::new, Constants.POST_PLURAL));


        generator.addProvider(event.includeClient(), new PostBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new StrippedPostBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new PostBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new StrippedPostBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new PostLanguageProvider(generator));

    }

    private static void makeSawmillData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.SAWMILL_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new SawmillingRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SawmillRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.SAWMILL_PLURAL, WoodenBlockType.SAWMILL, ILikeWoodBlockTags.SAWMILLS)
        );

        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.SAWMILL_PLURAL, WoodenBlockType.SAWMILL,
                ILikeWoodItemTags.SAWMILLS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, SawmillLoot::new, Constants.SAWMILL_PLURAL));


        generator.addProvider(event.includeClient(), new SawmillBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SawmillBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ContainerBlockLanguageProvider(generator, WoodenBlockType.SAWMILL));

    }

    private static void makeBedData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BEDS, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new BedTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new BedRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new DefaultBlockTagsProvider(generator, helper, Constants.BEDS, WoodenBlockType.WHITE_BED, ILikeWoodBlockTags.BEDS));
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.BEDS, WoodenBlockType.WHITE_BED,
                ILikeWoodItemTags.BEDS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, BedLoot::new, Constants.BEDS));


        generator.addProvider(event.includeClient(), new BedBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new BedBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new BedLanguageProvider(generator));

    }

    private static void makeChairData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.CHAIR_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ChairRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.CHAIR_PLURAL, WoodenBlockType.CHAIR, ILikeWoodBlockTags.CHAIRS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.CHAIR_PLURAL, WoodenBlockType.CHAIR,
                ILikeWoodItemTags.CHAIRS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.CHAIR), Constants.CHAIR_PLURAL));


        generator.addProvider(event.includeClient(), new ChairBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ChairBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.CHAIR));

    }

    private static void makeStoolData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.STOOL_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new StoolRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.STOOL_PLURAL, WoodenBlockType.STOOL, ILikeWoodBlockTags.STOOLS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.STOOL_PLURAL, WoodenBlockType.STOOL,
                ILikeWoodItemTags.STOOLS
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.STOOL), Constants.STOOL_PLURAL));


        generator.addProvider(event.includeClient(), new StoolBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new StoolBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.STOOL));

    }

    private static void makeTableData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.TABLE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new TableRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.TABLE_PLURAL, WoodenBlockType.TABLE, ILikeWoodBlockTags.TABLES)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.TABLE_PLURAL, WoodenBlockType.TABLE,
                ILikeWoodItemTags.TABLES
            )
        );
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.TABLE), Constants.TABLE_PLURAL));


        generator.addProvider(event.includeClient(), new TableBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new TableBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.TABLE));

    }

    private static void makeSingleDresserData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute
    )
    {
        final var generator = makeGenerator(config, Constants.SINGLE_DRESSER_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new SingleDresserRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.SINGLE_DRESSER_PLURAL, WoodenBlockType.SINGLE_DRESSER, ILikeWoodBlockTags.SINGLE_DRESSERS)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.SINGLE_DRESSER_PLURAL,
                WoodenBlockType.SINGLE_DRESSER, ILikeWoodItemTags.SINGLE_DRESSER
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.SINGLE_DRESSER), Constants.SINGLE_DRESSER_PLURAL)
        );


        generator.addProvider(event.includeClient(), new SingleDresserBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SingleDresserBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.SINGLE_DRESSER));

    }

    private static void makeLogPileData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LOG_PILE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new LogPileTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new LogPileRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultBlockTagsProvider(generator, helper, Constants.LOG_PILE_PLURAL, WoodenBlockType.LOG_PILE, ILikeWoodBlockTags.LOG_PILES)
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.LOG_PILE_PLURAL, WoodenBlockType.LOG_PILE,
                ILikeWoodItemTags.LOG_PILES
            )
        );
        generator.addProvider(event.includeServer(),
            new DefaultBlockLootTableProvider(generator, () -> new DropSelfLoot(WoodenBlockType.LOG_PILE), Constants.LOG_PILE_PLURAL)
        );


        generator.addProvider(event.includeClient(), new LogPileBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new LogPileBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenBlockType.LOG_PILE));

    }

    private static void makeStickData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.STICK_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new StickTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new StickRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new StickItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));


        generator.addProvider(event.includeClient(), new StickItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenItemType.STICK));

    }

    private static void makeAxeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.AXE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.AXE));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new AxeRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.AXE));
        generator.addProvider(event.includeServer(),
            new TieredItemItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.AXE_PLURAL, WoodenTieredItemType.AXE,
                ILikeWoodItemTags.AXES
            )
        );


        generator.addProvider(event.includeClient(), new AxeTieredItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenTieredItemType.AXE));

    }

    private static void makeHoeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.HOE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.HOE));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new HoeRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.HOE));
        generator.addProvider(event.includeServer(),
            new TieredItemItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.HOE_PLURAL, WoodenTieredItemType.HOE,
                ILikeWoodItemTags.HOES
            )
        );


        generator.addProvider(event.includeClient(), new HoeTieredItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenTieredItemType.HOE));

    }

    private static void makePickaxeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.PICKAXE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.PICKAXE));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new PickaxeRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.PICKAXE));
        generator.addProvider(event.includeServer(),
            new TieredItemItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.PICKAXE_PLURAL, WoodenTieredItemType.PICKAXE,
                ILikeWoodItemTags.PICKAXES
            )
        );


        generator.addProvider(event.includeClient(), new PickaxeTieredItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenTieredItemType.PICKAXE));

    }

    private static void makeShovelData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SHOVEL_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.SHOVEL));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ShovelRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.SHOVEL));
        generator.addProvider(event.includeServer(),
            new TieredItemItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.SHOVEL_PLURAL, WoodenTieredItemType.SHOVEL,
                ILikeWoodItemTags.SHOVELS
            )
        );


        generator.addProvider(event.includeClient(), new ShovelTieredItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenTieredItemType.SHOVEL));

    }

    private static void makeSwordData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SWORD_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.SWORD));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new SwordRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.SWORD));
        generator.addProvider(event.includeServer(),
            new TieredItemItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.SWORD_PLURAL, WoodenTieredItemType.SWORD,
                ILikeWoodItemTags.SWORDS
            )
        );


        generator.addProvider(event.includeClient(), new SwordTieredItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenTieredItemType.SWORD));

    }

    private static void makeBowData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BOW_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new BowTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new BowRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.BOW_PLURAL, WoodenItemType.BOW,
                ILikeWoodItemTags.BOWS
            )
        );


        generator.addProvider(event.includeClient(), new BowItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenItemType.BOW));

    }

    private static void makeCrossbowData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.CROSSBOW_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new CrossbowTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new CrossbowRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.CROSSBOW_PLURAL, WoodenItemType.CROSSBOW,
                ILikeWoodItemTags.CROSSBOWS
            )
        );


        generator.addProvider(event.includeClient(), new CrossbowItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenItemType.CROSSBOW));

    }

    private static void makeItemFrameData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.ITEM_FRAME_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new ItemFrameTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new ItemFrameRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.ITEM_FRAME_PLURAL, WoodenItemType.ITEM_FRAME,
                ILikeWoodItemTags.ITEM_FRAMES
            )
        );


        generator.addProvider(event.includeClient(), new ItemFrameItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new ItemFrameBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenItemType.ITEM_FRAME));

    }

    private static void makeFishingRodData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.FISHING_ROD_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(true, new FishingRodTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new FishingRodRecipeProvider(generator));
        generator.addProvider(event.includeServer(),
            new DefaultItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper, Constants.FISHING_ROD_PLURAL, WoodenItemType.FISHING_ROD,
                ILikeWoodItemTags.FISHING_RODS
            )
        );


        generator.addProvider(event.includeClient(), new FishingRodItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new DefaultLanguageProvider(generator, WoodenItemType.FISHING_ROD));
    }

    private static void makeCampfireData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final boolean shouldExecute,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.CAMPFIRE_PLURAL, shouldExecute);
        final var helper = event.getExistingFileHelper();


        // TODO add these back when you found a way to make it work with plugin datagen
        // textureGenerator.addProvider(event.includeClient(), new CampfireFireTextureProvider(textureGenerator, helper));
        // textureGenerator.addProvider(event.includeClient(), new ColoredCampfireSmokeParticleTextureProvider(textureGenerator, helper));

        generator.addProvider(true, new PackMCMetaProvider(generator));


        generator.addProvider(event.includeServer(), new CampfireRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SoulCampfireRecipeProvider(generator));
        generator.addProvider(event.includeServer(), new CampfireBlockTagsProvider(generator, helper));
        generator.addProvider(event.includeServer(), new CampfireItemTagsProvider(generator, new DummyBlockTagsProvider(generator, helper), helper));
        generator.addProvider(event.includeServer(), new DefaultBlockLootTableProvider(generator, CampfireLoot::new, Constants.CAMPFIRE_PLURAL));


        generator.addProvider(event.includeClient(), new CampfireBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SoulCampfireBlockStateProvider(generator, helper));
        generator.addProvider(event.includeClient(), new CampfireBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new SoulCampfireBlockItemModelProvider(generator, helper));
        generator.addProvider(event.includeClient(), new CampfireLanguageProvider(generator));


        // generator.addProvider(event.includeClient(), new ColoredCampfireSmokeParticleProvider(generator));
        // generator.addProvider(event.includeClient(), new ColoredLavaParticleProvider(generator));
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event)
    {
        final var config = getConfig(event);
        final var shouldExecute = config.getMods().contains(Constants.MOD_ID);

        final var textureGenerator =
            new DataGenerator(event.getGenerator().getOutputFolder().getParent().resolve("textures").resolve(String.format("%s_resources", Constants.MOD_ID)),
                Collections.emptyList(), DetectedVersion.tryDetectVersion(), shouldExecute
            );

        makePanelsData(event, config, shouldExecute);
        makePanelsStairsData(event, config, shouldExecute);
        makePanelsSlabData(event, config, shouldExecute);
        makeBarrelData(event, config, shouldExecute, textureGenerator);
        makeBookshelfData(event, config, shouldExecute, textureGenerator);
        makeChestData(event, config, shouldExecute, textureGenerator);
        makeComposterData(event, config, shouldExecute, textureGenerator);
        makeWallData(event, config, shouldExecute);
        makeLadderData(event, config, shouldExecute, textureGenerator);
        makeTorchData(event, config, shouldExecute, textureGenerator);
        makeCraftingTableData(event, config, shouldExecute);
        makeScaffoldingData(event, config, shouldExecute, textureGenerator);
        makeLecternData(event, config, shouldExecute, textureGenerator);
        makePostData(event, config, shouldExecute, textureGenerator);
        makeSawmillData(event, config, shouldExecute);
        makeBedData(event, config, shouldExecute, textureGenerator);
        makeChairData(event, config, shouldExecute);
        makeStoolData(event, config, shouldExecute);
        makeTableData(event, config, shouldExecute);
        makeSingleDresserData(event, config, shouldExecute);
        makeLogPileData(event, config, shouldExecute, textureGenerator);
        makeStickData(event, config, shouldExecute, textureGenerator);
        makeAxeData(event, config, shouldExecute, textureGenerator);
        makeHoeData(event, config, shouldExecute, textureGenerator);
        makePickaxeData(event, config, shouldExecute, textureGenerator);
        makeShovelData(event, config, shouldExecute, textureGenerator);
        makeSwordData(event, config, shouldExecute, textureGenerator);
        makeBowData(event, config, shouldExecute, textureGenerator);
        makeCrossbowData(event, config, shouldExecute, textureGenerator);
        makeItemFrameData(event, config, shouldExecute, textureGenerator);
        makeFishingRodData(event, config, shouldExecute, textureGenerator);
        makeCampfireData(event, config, shouldExecute, textureGenerator);

        if (shouldExecute)
        {
            try
            {
                textureGenerator.run();
            }
            catch (IOException ignored)
            {
                throw new RuntimeException("");
            }
        }
    }
}