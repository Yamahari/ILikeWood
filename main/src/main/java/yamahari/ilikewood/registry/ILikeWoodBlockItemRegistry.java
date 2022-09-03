package yamahari.ilikewood.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.WoodenBedItem;
import yamahari.ilikewood.item.WoodenBlockItem;
import yamahari.ilikewood.item.WoodenChestBlockItem;
import yamahari.ilikewood.item.WoodenScaffoldingItem;
import yamahari.ilikewood.item.WoodenWallOrFloorItem;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ILikeWoodBlockItemRegistry
    extends AbstractILikeWoodObjectRegistry<Item, WoodenBlockType>
{
    public ILikeWoodBlockItemRegistry()
    {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void register()
    {
        this.registerBlockItems(WoodenBlockType.PANELS, this::registerPanelsBlockItem);
        this.registerBlockItems(WoodenBlockType.PANELS_STAIRS, this::registerPanelsStairsBlockItem);
        this.registerBlockItems(WoodenBlockType.PANELS_SLAB, this::registerPanelsSlabBlockItem);
        this.registerBlockItems(WoodenBlockType.BOOKSHELF, this::registerBookshelfBlockItem);
        this.registerBlockItems(WoodenBlockType.BARREL, this::registerBarrelBlockItem);
        this.registerBlockItems(WoodenBlockType.WALL, this::registerWallBlockItem);
        this.registerBlockItems(WoodenBlockType.LADDER, this::registerLadderBlockItem);
        this.registerBlockItems(WoodenBlockType.CRAFTING_TABLE, this::registerCraftingTableBlockItem);
        this.registerBlockItems(WoodenBlockType.POST, this::registerPostBlockItem);
        this.registerBlockItems(WoodenBlockType.STRIPPED_POST, this::registerStrippedPostBlockItem);
        this.registerBlockItems(WoodenBlockType.SAWMILL, this::registerSawmillBlockItem);
        this.registerBlockItems(WoodenBlockType.COMPOSTER, this::registerComposterBlockItem);
        this.registerBlockItems(WoodenBlockType.LECTERN, this::registerLecternBlockItem);
        this.registerBlockItems(WoodenBlockType.CHEST, this::registerChestBlockItem);
        this.registerBlockItems(WoodenBlockType.TORCH, this::registerTorchBlockItem);
        this.registerBlockItems(WoodenBlockType.SOUL_TORCH, this::registerSoulTorchBlockItem);
        this.registerBlockItems(WoodenBlockType.SCAFFOLDING, this::registerScaffoldingBlockItem);
        this.registerBlockItems(WoodenBlockType.CHAIR, this::registerChairBlockItem);
        this.registerBlockItems(WoodenBlockType.TABLE, this::registerTableBlockItem);
        this.registerBlockItems(WoodenBlockType.STOOL, this::registerStoolBlockItem);
        this.registerBlockItems(WoodenBlockType.SINGLE_DRESSER, this::registerSingleDresserBlockItem);
        this.registerBlockItems(WoodenBlockType.LOG_PILE, this::registerLogPileBlockItem);
        this.registerBlockItems(WoodenBlockType.CAMPFIRE, this::registerCampfireBlockItem);
        this.registerBlockItems(WoodenBlockType.SOUL_CAMPFIRE, this::registerSoulCampfireBlockItem);
        WoodenBlockType.getBeds().forEach(bedBlockType -> this.registerBlockItems(bedBlockType, woodType -> this.registerBedBlockItem(woodType, bedBlockType)));
        this.registerBlockItems(WoodenBlockType.CRATE, this::registerCrateBlockItem);
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(final WoodenBlockType blockType)
    {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(blockType))
            .map(woodType -> this.getRegistryObject(woodType, blockType));
    }

    private void registerBlockItems(
        final WoodenBlockType blockType,
        final Function<IWoodType, RegistryObject<Item>> function
    )
    {
        if (blockType.isEnabled())
        {
            final Map<IWoodType, RegistryObject<Item>> blockItems = new HashMap<>();
            ILikeWood.WOOD_TYPE_REGISTRY
                .getWoodTypes()
                .filter(woodType -> woodType.getBlockTypes().contains(blockType))
                .forEach(woodType -> blockItems.put(woodType, function.apply(woodType)));
            this.registryObjects.put(blockType, Collections.unmodifiableMap(blockItems));
        }
    }

    private RegistryObject<Item> registerBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType,
        final Item.Properties properties
    )
    {
        final RegistryObject<Block> block = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, blockType);
        return this.registry.register(block.getId().getPath(), () -> new WoodenBlockItem(blockType, block.get(), properties));
    }

    private RegistryObject<Item> registerBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType,
        final CreativeModeTab itemGroup
    )
    {
        return this.registerBlockItem(woodType, blockType, new Item.Properties().tab(itemGroup));
    }

    private RegistryObject<Item> registerBuildingBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType
    )
    {
        return this.registerBlockItem(woodType, blockType, CreativeModeTab.TAB_BUILDING_BLOCKS);
    }

    private RegistryObject<Item> registerDecorationBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType
    )
    {
        return this.registerBlockItem(woodType, blockType, CreativeModeTab.TAB_DECORATIONS);
    }

    private RegistryObject<Item> registerMiscBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType
    )
    {
        return this.registerBlockItem(woodType, blockType, CreativeModeTab.TAB_MISC);
    }

    private RegistryObject<Item> registerRedstoneBlockItem(
        final IWoodType woodType,
        final WoodenBlockType blockType
    )
    {
        return this.registerBlockItem(woodType, blockType, CreativeModeTab.TAB_REDSTONE);
    }

    private RegistryObject<Item> registerPanelsBlockItem(final IWoodType woodType)
    {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS);
    }

    private RegistryObject<Item> registerPanelsStairsBlockItem(final IWoodType woodType)
    {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS_STAIRS);
    }

    private RegistryObject<Item> registerPanelsSlabBlockItem(final IWoodType woodType)
    {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS_SLAB);
    }

    private RegistryObject<Item> registerBookshelfBlockItem(final IWoodType woodType)
    {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.BOOKSHELF);
    }

    private RegistryObject<Item> registerBarrelBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.BARREL);
    }

    private RegistryObject<Item> registerWallBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.WALL);
    }

    private RegistryObject<Item> registerLadderBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.LADDER);
    }

    private RegistryObject<Item> registerScaffoldingBlockItem(final IWoodType woodType)
    {
        final RegistryObject<Block> scaffoldingBlock = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, WoodenBlockType.SCAFFOLDING);
        return this.registry.register(scaffoldingBlock.getId().getPath(), () -> new WoodenScaffoldingItem(scaffoldingBlock.get()));
    }

    private RegistryObject<Item> registerCraftingTableBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.CRAFTING_TABLE);
    }

    private RegistryObject<Item> registerPostBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.POST);
    }

    private RegistryObject<Item> registerStrippedPostBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.STRIPPED_POST);
    }

    private RegistryObject<Item> registerSawmillBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.SAWMILL);
    }

    private RegistryObject<Item> registerComposterBlockItem(final IWoodType woodType)
    {
        return this.registerMiscBlockItem(woodType, WoodenBlockType.COMPOSTER);
    }

    private RegistryObject<Item> registerLecternBlockItem(final IWoodType woodType)
    {
        return this.registerRedstoneBlockItem(woodType, WoodenBlockType.LECTERN);
    }

    private RegistryObject<Item> registerChestBlockItem(final IWoodType woodType)
    {
        final RegistryObject<Block> chest = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, WoodenBlockType.CHEST);
        return this.registry.register(chest.getId().getPath(), () -> new WoodenChestBlockItem(chest.get()));
    }

    private RegistryObject<Item> registerBedBlockItem(
        final IWoodType woodType,
        final WoodenBlockType bedBlockType
    )
    {
        final RegistryObject<Block> bed = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, bedBlockType);
        return this.registry.register(bed.getId().getPath(), () -> new WoodenBedItem(bedBlockType, bed.get()));
    }

    private RegistryObject<Item> registerTorchBlockItem(
        final IWoodType woodType,
        final WoodenBlockType torchBlockType,
        final WoodenBlockType wallTorchBlockType
    )
    {
        final RegistryObject<Block> torch = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, torchBlockType);
        final RegistryObject<Block> wallTorch = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, wallTorchBlockType);
        return this.registry.register(
            torch.getId().getPath(),
            () -> new WoodenWallOrFloorItem(torchBlockType, torch.get(), wallTorch.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS))
        );
    }

    private RegistryObject<Item> registerTorchBlockItem(final IWoodType woodType)
    {
        return this.registerTorchBlockItem(woodType, WoodenBlockType.TORCH, WoodenBlockType.WALL_TORCH);
    }

    private RegistryObject<Item> registerSoulTorchBlockItem(final IWoodType woodType)
    {
        return this.registerTorchBlockItem(woodType, WoodenBlockType.SOUL_TORCH, WoodenBlockType.WALL_SOUL_TORCH);
    }

    private RegistryObject<Item> registerChairBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.CHAIR);
    }

    private RegistryObject<Item> registerTableBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.TABLE);
    }

    private RegistryObject<Item> registerStoolBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.STOOL);
    }

    private RegistryObject<Item> registerSingleDresserBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.SINGLE_DRESSER);
    }

    private RegistryObject<Item> registerLogPileBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.LOG_PILE);
    }

    private RegistryObject<Item> registerCampfireBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.CAMPFIRE);
    }

    private RegistryObject<Item> registerSoulCampfireBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.SOUL_CAMPFIRE);
    }

    private RegistryObject<Item> registerCrateBlockItem(final IWoodType woodType)
    {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.CRATE);
    }
}