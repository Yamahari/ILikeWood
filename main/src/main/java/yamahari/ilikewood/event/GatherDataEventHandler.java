package yamahari.ilikewood.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import yamahari.ilikewood.data.loot.*;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.PackMCMetaProvider;
import yamahari.ilikewood.provider.blockstate.*;
import yamahari.ilikewood.provider.itemmodel.*;
import yamahari.ilikewood.provider.itemmodel.blockitem.*;
import yamahari.ilikewood.provider.itemmodel.tiered.*;
import yamahari.ilikewood.provider.lang.*;
import yamahari.ilikewood.provider.loot.DefaultBlockLootTableProvider;
import yamahari.ilikewood.provider.recipe.blockitem.*;
import yamahari.ilikewood.provider.recipe.item.*;
import yamahari.ilikewood.provider.recipe.item.tiered.*;
import yamahari.ilikewood.provider.recipe.sawmilling.SawmillingRecipeProvider;
import yamahari.ilikewood.provider.tag.block.*;
import yamahari.ilikewood.provider.tag.item.*;
import yamahari.ilikewood.provider.texture.block.*;
import yamahari.ilikewood.provider.texture.item.*;
import yamahari.ilikewood.provider.texture.item.tiered.ToolTextureProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import java.io.IOException;
import java.util.Collections;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT, Dist.DEDICATED_SERVER
}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GatherDataEventHandler
{
    private GatherDataEventHandler() {
    }

    private static GatherDataEvent.DataGeneratorConfig getConfig(final GatherDataEvent event) {
        return ObfuscationReflectionHelper.getPrivateValue(GatherDataEvent.class, event, "config");
    }

    private static DataGenerator makeGenerator(final GatherDataEvent.DataGeneratorConfig config, final String root) {
        return config.makeGenerator(outputPath -> outputPath.getParent()
            .resolve("data")
            .resolve(String.format("%s_resources", Constants.MOD_ID))
            .resolve(root), true);
    }

    private static void makePanelsData(final GatherDataEvent event, final GatherDataEvent.DataGeneratorConfig config) {
        final var generator = makeGenerator(config, Constants.PANELS_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new PanelsRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.PANELS_PLURAL,
                WoodenBlockType.PANELS,
                ILikeWoodBlockTags.PANELS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.PANELS_PLURAL,
                WoodenBlockType.PANELS,
                ILikeWoodItemTags.PANELS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.PANELS),
                Constants.PANELS_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new PanelsBlockStateProvider(generator, helper));
            generator.addProvider(new PanelsBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.PANELS));
        }
    }

    private static void makePanelsStairsData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.PANELS_STAIRS_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new PanelsStairsRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.PANELS_STAIRS_PLURAL,
                WoodenBlockType.PANELS_STAIRS,
                ILikeWoodBlockTags.PANELS_STAIRS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.PANELS_STAIRS_PLURAL,
                WoodenBlockType.PANELS_STAIRS,
                ILikeWoodItemTags.PANELS_STAIRS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.PANELS_STAIRS),
                Constants.PANELS_STAIRS_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new PanelsStairsBlockStateProvider(generator, helper));
            generator.addProvider(new PanelsStairsBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.PANELS_STAIRS));
        }
    }

    private static void makePanelsSlabData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.PANELS_SLAB_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new PanelsSlabRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.PANELS_SLAB_PLURAL,
                WoodenBlockType.PANELS_SLAB,
                ILikeWoodBlockTags.PANELS_SLABS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.PANELS_SLAB_PLURAL,
                WoodenBlockType.PANELS_SLAB,
                ILikeWoodItemTags.PANELS_SLABS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                PanelsSlabLoot::new,
                Constants.PANELS_SLAB_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new PanelsSlabBlockStateProvider(generator, helper));
            generator.addProvider(new PanelsSlabBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.PANELS_SLAB));
        }
    }

    private static void makeBarrelData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BARREL_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new BarrelTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new BarrelRecipeProvider(generator));
            generator.addProvider(new BarrelBlockTagsProvider(generator, helper));
            generator.addProvider(new BarrelItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new NameableBlockEntityLoot(WoodenBlockType.BARREL),
                Constants.BARREL_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new BarrelBlockStateProvider(generator, helper));
            generator.addProvider(new BarrelBlockItemModelProvider(generator, helper));
            generator.addProvider(new ContainerBlockLanguageProvider(generator, WoodenBlockType.BARREL));
        }
    }

    private static void makeBookshelfData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BOOKSHELF_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new BookshelfTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new BookshelfRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.BOOKSHELF_PLURAL,
                WoodenBlockType.BOOKSHELF,
                ILikeWoodBlockTags.BOOKSHELVES
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.BOOKSHELF_PLURAL,
                WoodenBlockType.BOOKSHELF,
                ILikeWoodItemTags.BOOKSHELVES
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                BookshelfLoot::new,
                Constants.BOOKSHELF_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new BookshelfBlockStateProvider(generator, helper));
            generator.addProvider(new BookshelfBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.BOOKSHELF));
        }
    }

    private static void makeChestData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.CHEST_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ChestTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ChestRecipeProvider(generator));
            generator.addProvider(new ChestBlockTagsProvider(generator, helper));
            generator.addProvider(new ChestItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new NameableBlockEntityLoot(WoodenBlockType.CHEST),
                Constants.CHEST_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ChestBlockStateProvider(generator, helper));
            generator.addProvider(new ChestBlockItemModelProvider(generator, helper));
            generator.addProvider(new ContainerBlockLanguageProvider(generator, WoodenBlockType.CHEST));
        }
    }

    private static void makeComposterData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.COMPOSTER_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ComposterTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ComposterRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.COMPOSTER_PLURAL,
                WoodenBlockType.COMPOSTER,
                ILikeWoodBlockTags.COMPOSTER
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.COMPOSTER_PLURAL,
                WoodenBlockType.COMPOSTER,
                ILikeWoodItemTags.COMPOSTER
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                ComposterLoot::new,
                Constants.COMPOSTER_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ComposterBlockStateProvider(generator, helper));
            generator.addProvider(new ComposterBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.COMPOSTER));
        }
    }

    private static void makeWallData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.WALL_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new WallRecipeProvider(generator));
            generator.addProvider(new WallBlockTagsProvider(generator, helper));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.WALL_PLURAL,
                WoodenBlockType.WALL,
                ILikeWoodItemTags.WALLS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.WALL),
                Constants.WALL_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new WallBlockStateProvider(generator, helper));
            generator.addProvider(new WallBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.WALL));
        }
    }

    private static void makeLadderData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LADDER_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new LadderTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new LadderRecipeProvider(generator));
            generator.addProvider(new ClimbableBlockTagsProvider(
                generator,
                helper,
                Constants.LADDER_PLURAL,
                WoodenBlockType.LADDER,
                ILikeWoodBlockTags.LADDERS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.LADDER_PLURAL,
                WoodenBlockType.LADDER,
                ILikeWoodItemTags.LADDERS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.LADDER),
                Constants.LADDER_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new LadderBlockStateProvider(generator, helper));
            generator.addProvider(new LadderBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.LADDER));
        }
    }

    private static void makeTorchData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.TORCH_PLURAL);
        final var helper = event.getExistingFileHelper();

        // TODO maybe merge all torch & soul torch providers

        textureGenerator.addProvider(new TorchTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new TorchRecipeProvider(generator));
            generator.addProvider(new SoulTorchRecipeProvider(generator));
            generator.addProvider(new TorchBlockTagsProvider(generator, helper));
            generator.addProvider(new TorchItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                TorchLoot::new,
                Constants.TORCH_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new TorchBlockStateProvider(generator, helper));
            generator.addProvider(new WallTorchBlockStateProvider(generator, helper));
            generator.addProvider(new SoulTorchBlockStateProvider(generator, helper));
            generator.addProvider(new WallSoulTorchBlockStateProvider(generator, helper));
            generator.addProvider(new TorchBlockItemModelProvider(generator, helper));
            generator.addProvider(new SoulTorchBlockItemModelProvider(generator, helper));
            generator.addProvider(new TorchLanguageProvider(generator));
        }
    }

    private static void makeCraftingTableData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.CRAFTING_TABLE_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new CraftingTableRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.CRAFTING_TABLE_PLURAL,
                WoodenBlockType.CRAFTING_TABLE,
                ILikeWoodBlockTags.CRAFTING_TABLES
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.CRAFTING_TABLE_PLURAL,
                WoodenBlockType.CRAFTING_TABLE,
                ILikeWoodItemTags.CRAFTING_TABLES
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.CRAFTING_TABLE),
                Constants.CRAFTING_TABLE_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new CraftingTableBlockStateProvider(generator, helper));
            generator.addProvider(new CraftingTableBlockItemModelProvider(generator, helper));
            generator.addProvider(new ContainerBlockLanguageProvider(generator, WoodenBlockType.CRAFTING_TABLE));
        }
    }

    private static void makeScaffoldingData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SCAFFOLDING_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ScaffoldingTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ScaffoldingRecipeProvider(generator));
            generator.addProvider(new ClimbableBlockTagsProvider(
                generator,
                helper,
                Constants.SCAFFOLDING_PLURAL,
                WoodenBlockType.SCAFFOLDING,
                ILikeWoodBlockTags.SCAFFOLDINGS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.SCAFFOLDING_PLURAL,
                WoodenBlockType.SCAFFOLDING,
                ILikeWoodItemTags.SCAFFOLDINGS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.SCAFFOLDING),
                Constants.SCAFFOLDING_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ScaffoldingBlockStateProvider(generator, helper));
            generator.addProvider(new ScaffoldingBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.SCAFFOLDING));
        }
    }

    private static void makeLecternData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LECTERN_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new LecternTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new LecternRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.LECTERN_PLURAL,
                WoodenBlockType.LECTERN,
                ILikeWoodBlockTags.LECTERNS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.LECTERN_PLURAL,
                WoodenBlockType.LECTERN,
                ILikeWoodItemTags.LECTERNS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new NameableBlockEntityLoot(WoodenBlockType.LECTERN),
                Constants.LECTERN_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new LecternBlockStateProvider(generator, helper));
            generator.addProvider(new LecternBlockItemModelProvider(generator, helper));
            generator.addProvider(new ContainerBlockLanguageProvider(generator, WoodenBlockType.LECTERN));
        }
    }

    private static void makePostData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.POST_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new PostTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new PostRecipeProvider(generator));
            generator.addProvider(new PostBlockTagsProvider(generator, helper));
            generator.addProvider(new PostItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                PostLoot::new,
                Constants.POST_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new PostBlockStateProvider(generator, helper));
            generator.addProvider(new StrippedPostBlockStateProvider(generator, helper));
            generator.addProvider(new PostBlockItemModelProvider(generator, helper));
            generator.addProvider(new StrippedPostBlockItemModelProvider(generator, helper));
            generator.addProvider(new PostLanguageProvider(generator));
        }
    }

    private static void makeSawmillData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.SAWMILL_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new SawmillingRecipeProvider(generator));
            generator.addProvider(new SawmillRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.SAWMILL_PLURAL,
                WoodenBlockType.SAWMILL,
                ILikeWoodBlockTags.SAWMILLS
            ));

            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.SAWMILL_PLURAL,
                WoodenBlockType.SAWMILL,
                ILikeWoodItemTags.SAWMILLS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                SawmillLoot::new,
                Constants.SAWMILL_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new SawmillBlockStateProvider(generator, helper));
            generator.addProvider(new SawmillBlockItemModelProvider(generator, helper));
            generator.addProvider(new ContainerBlockLanguageProvider(generator, WoodenBlockType.SAWMILL));
        }
    }

    private static void makeBedData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BEDS);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new BedTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new BedRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.BEDS,
                WoodenBlockType.WHITE_BED,
                ILikeWoodBlockTags.BEDS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.BEDS,
                WoodenBlockType.WHITE_BED,
                ILikeWoodItemTags.BEDS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                BedLoot::new,
                Constants.BEDS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new BedBlockStateProvider(generator, helper));
            generator.addProvider(new BedBlockItemModelProvider(generator, helper));
            generator.addProvider(new BedLanguageProvider(generator));
        }
    }

    private static void makeChairData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.CHAIR_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ChairRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.CHAIR_PLURAL,
                WoodenBlockType.CHAIR,
                ILikeWoodBlockTags.CHAIRS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.CHAIR_PLURAL,
                WoodenBlockType.CHAIR,
                ILikeWoodItemTags.CHAIRS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.CHAIR),
                Constants.CHAIR_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ChairBlockStateProvider(generator, helper));
            generator.addProvider(new ChairBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.CHAIR));
        }
    }

    private static void makeStoolData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.STOOL_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new StoolRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.STOOL_PLURAL,
                WoodenBlockType.STOOL,
                ILikeWoodBlockTags.STOOLS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.STOOL_PLURAL,
                WoodenBlockType.STOOL,
                ILikeWoodItemTags.STOOLS
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.STOOL),
                Constants.STOOL_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new StoolBlockStateProvider(generator, helper));
            generator.addProvider(new StoolBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.STOOL));
        }
    }

    private static void makeTableData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.TABLE_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new TableRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.TABLE_PLURAL,
                WoodenBlockType.TABLE,
                ILikeWoodBlockTags.TABLES
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.TABLE_PLURAL,
                WoodenBlockType.TABLE,
                ILikeWoodItemTags.TABLES
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.TABLE),
                Constants.TABLE_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new TableBlockStateProvider(generator, helper));
            generator.addProvider(new TableBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.TABLE));
        }
    }

    private static void makeSingleDresserData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config
    )
    {
        final var generator = makeGenerator(config, Constants.SINGLE_DRESSER_PLURAL);
        final var helper = event.getExistingFileHelper();

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new SingleDresserRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.SINGLE_DRESSER_PLURAL,
                WoodenBlockType.SINGLE_DRESSER,
                ILikeWoodBlockTags.SINGLE_DRESSERS
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.SINGLE_DRESSER_PLURAL,
                WoodenBlockType.SINGLE_DRESSER,
                ILikeWoodItemTags.SINGLE_DRESSER
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.SINGLE_DRESSER),
                Constants.SINGLE_DRESSER_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new SingleDresserBlockStateProvider(generator, helper));
            generator.addProvider(new SingleDresserBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.SINGLE_DRESSER));
        }
    }

    private static void makeLogPileData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.LOG_PILE_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new LogPileTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new LogPileRecipeProvider(generator));
            generator.addProvider(new DefaultBlockTagsProvider(
                generator,
                helper,
                Constants.LOG_PILE_PLURAL,
                WoodenBlockType.LOG_PILE,
                ILikeWoodBlockTags.LOG_PILES
            ));
            generator.addProvider(new DefaultBlockItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.LOG_PILE_PLURAL,
                WoodenBlockType.LOG_PILE,
                ILikeWoodItemTags.LOG_PILES
            ));
            generator.addProvider(new DefaultBlockLootTableProvider(
                generator,
                () -> new DropSelfLoot(WoodenBlockType.LOG_PILE),
                Constants.LOG_PILE_PLURAL
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new LogPileBlockStateProvider(generator, helper));
            generator.addProvider(new LogPileBlockItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenBlockType.LOG_PILE));
        }
    }

    private static void makeStickData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.STICK_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new StickTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new StickRecipeProvider(generator));
            generator.addProvider(new StickItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new StickItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenItemType.STICK));
        }
    }

    private static void makeAxeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.AXE_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.AXE));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new AxeRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.AXE));
            generator.addProvider(new TieredItemItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.AXE_PLURAL,
                WoodenTieredItemType.AXE,
                ILikeWoodItemTags.AXES
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new AxeTieredItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenTieredItemType.AXE));
        }
    }

    private static void makeHoeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.HOE_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.HOE));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new HoeRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.HOE));
            generator.addProvider(new TieredItemItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.HOE_PLURAL,
                WoodenTieredItemType.HOE,
                ILikeWoodItemTags.HOES
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new HoeTieredItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenTieredItemType.HOE));
        }
    }

    private static void makePickaxeData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.PICKAXE_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.PICKAXE));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new PickaxeRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.PICKAXE));
            generator.addProvider(new TieredItemItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.PICKAXE_PLURAL,
                WoodenTieredItemType.PICKAXE,
                ILikeWoodItemTags.PICKAXES
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new PickaxeTieredItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenTieredItemType.PICKAXE));
        }
    }

    private static void makeShovelData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SHOVEL_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.SHOVEL));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ShovelRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.SHOVEL));
            generator.addProvider(new TieredItemItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.SHOVEL_PLURAL,
                WoodenTieredItemType.SHOVEL,
                ILikeWoodItemTags.SHOVELS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ShovelTieredItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenTieredItemType.SHOVEL));
        }
    }

    private static void makeSwordData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.SWORD_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ToolTextureProvider(textureGenerator, helper, WoodenTieredItemType.SWORD));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new SwordRecipeProvider(generator));
            generator.addProvider(new NetheriteTieredItemRecipeProvider(generator, WoodenTieredItemType.SWORD));
            generator.addProvider(new TieredItemItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.SWORD_PLURAL,
                WoodenTieredItemType.SWORD,
                ILikeWoodItemTags.SWORDS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new SwordTieredItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenTieredItemType.SWORD));
        }
    }

    private static void makeBowData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.BOW_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new BowTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new BowRecipeProvider(generator));
            generator.addProvider(new DefaultItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.BOW_PLURAL,
                WoodenItemType.BOW,
                ILikeWoodItemTags.BOWS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new BowItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenItemType.BOW));
        }
    }

    private static void makeCrossbowData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.CROSSBOW_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new CrossbowTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new CrossbowRecipeProvider(generator));
            generator.addProvider(new DefaultItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.CROSSBOW_PLURAL,
                WoodenItemType.CROSSBOW,
                ILikeWoodItemTags.CROSSBOWS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new CrossbowItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenItemType.CROSSBOW));
        }
    }

    private static void makeItemFrameData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.ITEM_FRAME_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new ItemFrameTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new ItemFrameRecipeProvider(generator));
            generator.addProvider(new DefaultItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.ITEM_FRAME_PLURAL,
                WoodenItemType.ITEM_FRAME,
                ILikeWoodItemTags.ITEM_FRAMES
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new ItemFrameItemModelProvider(generator, helper));
            generator.addProvider(new ItemFrameBlockStateProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenItemType.ITEM_FRAME));
        }
    }

    private static void makeFishingRodData(
        final GatherDataEvent event,
        final GatherDataEvent.DataGeneratorConfig config,
        final DataGenerator textureGenerator
    )
    {
        final var generator = makeGenerator(config, Constants.FISHING_ROD_PLURAL);
        final var helper = event.getExistingFileHelper();

        textureGenerator.addProvider(new FishingRodTextureProvider(textureGenerator, helper));

        generator.addProvider(new PackMCMetaProvider(generator));

        if (event.includeServer())
        {
            generator.addProvider(new FishingRodRecipeProvider(generator));
            generator.addProvider(new DefaultItemTagsProvider(
                generator,
                new DummyBlockTagsProvider(generator, helper),
                helper,
                Constants.FISHING_ROD_PLURAL,
                WoodenItemType.FISHING_ROD,
                ILikeWoodItemTags.FISHING_RODS
            ));
        }

        if (event.includeClient())
        {
            generator.addProvider(new FishingRodItemModelProvider(generator, helper));
            generator.addProvider(new DefaultLanguageProvider(generator, WoodenItemType.FISHING_ROD));
        }
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final var config = getConfig(event);

        final var textureGenerator = new DataGenerator(event.getGenerator()
            .getOutputFolder()
            .getParent()
            .resolve("textures")
            .resolve(String.format(
                "%s_resources", Constants.MOD_ID
            )), Collections.emptyList());

        makePanelsData(event, config);
        makePanelsStairsData(event, config);
        makePanelsSlabData(event, config);
        makeBarrelData(event, config, textureGenerator);
        makeBookshelfData(event, config, textureGenerator);
        makeChestData(event, config, textureGenerator);
        makeComposterData(event, config, textureGenerator);
        makeWallData(event, config);
        makeLadderData(event, config, textureGenerator);
        makeTorchData(event, config, textureGenerator);
        makeCraftingTableData(event, config);
        makeScaffoldingData(event, config, textureGenerator);
        makeLecternData(event, config, textureGenerator);
        makePostData(event, config, textureGenerator);
        makeSawmillData(event, config);
        makeBedData(event, config, textureGenerator);
        makeChairData(event, config);
        makeStoolData(event, config);
        makeTableData(event, config);
        makeSingleDresserData(event, config);
        makeLogPileData(event, config, textureGenerator);
        makeStickData(event, config, textureGenerator);
        makeAxeData(event, config, textureGenerator);
        makeHoeData(event, config, textureGenerator);
        makePickaxeData(event, config, textureGenerator);
        makeShovelData(event, config, textureGenerator);
        makeSwordData(event, config, textureGenerator);
        makeBowData(event, config, textureGenerator);
        makeCrossbowData(event, config, textureGenerator);
        makeItemFrameData(event, config, textureGenerator);
        makeFishingRodData(event, config, textureGenerator);

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