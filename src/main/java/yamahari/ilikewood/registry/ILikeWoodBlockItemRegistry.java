package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.item.WoodenBedItem;
import yamahari.ilikewood.item.WoodenBlockItem;
import yamahari.ilikewood.item.WoodenScaffoldingItem;
import yamahari.ilikewood.item.WoodenWallOrFloorItem;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ILikeWoodBlockItemRegistry extends AbstractILikeWoodObjectRegistry<Item, WoodenBlockType> {
    public ILikeWoodBlockItemRegistry() {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void register() {
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
        WoodenBlockType
            .getBeds()
            .forEach(bedBlockType -> this.registerBlockItems(bedBlockType,
                woodType -> this.registerBedBlockItem(woodType, bedBlockType)));
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(final WoodenBlockType blockType) {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(blockType))
            .map(woodType -> this.getRegistryObject(woodType, blockType));
    }

    private void registerBlockItems(final WoodenBlockType blockType,
                                    final Function<IWoodType, RegistryObject<Item>> function) {
        final Map<IWoodType, RegistryObject<Item>> blockItems = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(blockType))
            .forEach(woodType -> blockItems.put(woodType, function.apply(woodType)));
        this.registryObjects.put(blockType, Collections.unmodifiableMap(blockItems));
    }

    private RegistryObject<Item> registerBlockItem(final IWoodType woodType, final WoodenBlockType blockType,
                                                   final Item.Properties properties) {
        final RegistryObject<Block> block = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, blockType);
        return this.registry.register(block.getId().getPath(),
            () -> new WoodenBlockItem(blockType, block.get(), properties));
    }

    private RegistryObject<Item> registerBlockItem(final IWoodType woodType, final WoodenBlockType blockType,
                                                   final ItemGroup itemGroup) {
        return this.registerBlockItem(woodType, blockType, new Item.Properties().group(itemGroup));
    }

    private RegistryObject<Item> registerBuildingBlockItem(final IWoodType woodType, final WoodenBlockType blockType) {
        return this.registerBlockItem(woodType, blockType, ItemGroup.BUILDING_BLOCKS);
    }

    private RegistryObject<Item> registerDecorationBlockItem(final IWoodType woodType,
                                                             final WoodenBlockType blockType) {
        return this.registerBlockItem(woodType, blockType, ItemGroup.DECORATIONS);
    }

    private RegistryObject<Item> registerMiscBlockItem(final IWoodType woodType, final WoodenBlockType blockType) {
        return this.registerBlockItem(woodType, blockType, ItemGroup.MISC);
    }

    private RegistryObject<Item> registerRedstoneBlockItem(final IWoodType woodType, final WoodenBlockType blockType) {
        return this.registerBlockItem(woodType, blockType, ItemGroup.REDSTONE);
    }

    private RegistryObject<Item> registerPanelsBlockItem(final IWoodType woodType) {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS);
    }

    private RegistryObject<Item> registerPanelsStairsBlockItem(final IWoodType woodType) {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS_STAIRS);
    }

    private RegistryObject<Item> registerPanelsSlabBlockItem(final IWoodType woodType) {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.PANELS_SLAB);
    }

    private RegistryObject<Item> registerBookshelfBlockItem(final IWoodType woodType) {
        return this.registerBuildingBlockItem(woodType, WoodenBlockType.BOOKSHELF);
    }

    private RegistryObject<Item> registerBarrelBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.BARREL);
    }

    private RegistryObject<Item> registerWallBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.WALL);
    }

    private RegistryObject<Item> registerLadderBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.LADDER);
    }

    private RegistryObject<Item> registerScaffoldingBlockItem(final IWoodType woodType) {
        final RegistryObject<Block> scaffoldingBlock =
            ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, WoodenBlockType.SCAFFOLDING);
        return this.registry.register(scaffoldingBlock.getId().getPath(),
            () -> new WoodenScaffoldingItem(scaffoldingBlock.get()));
    }

    private RegistryObject<Item> registerCraftingTableBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.CRAFTING_TABLE);
    }

    private RegistryObject<Item> registerPostBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.POST);
    }

    private RegistryObject<Item> registerStrippedPostBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.STRIPPED_POST);
    }

    private RegistryObject<Item> registerSawmillBlockItem(final IWoodType woodType) {
        return this.registerDecorationBlockItem(woodType, WoodenBlockType.SAWMILL);
    }

    private RegistryObject<Item> registerComposterBlockItem(final IWoodType woodType) {
        return this.registerMiscBlockItem(woodType, WoodenBlockType.COMPOSTER);
    }

    private RegistryObject<Item> registerLecternBlockItem(final IWoodType woodType) {
        return this.registerRedstoneBlockItem(woodType, WoodenBlockType.LECTERN);
    }

    private RegistryObject<Item> registerChestBlockItem(final IWoodType woodType) {
        return this.registerBlockItem(woodType,
            WoodenBlockType.CHEST,
            new Item.Properties()
                .setISTER(() -> WoodenChestItemStackTileEntityRenderer::new)
                .group(ItemGroup.DECORATIONS));
    }

    private RegistryObject<Item> registerBedBlockItem(final IWoodType woodType, final WoodenBlockType bedBlockType) {
        final RegistryObject<Block> bedBlock = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, bedBlockType);
        return this.registry.register(bedBlock.getId().getPath(),
            () -> new WoodenBedItem(bedBlockType, bedBlock.get()));
    }

    private RegistryObject<Item> registerTorchBlockItem(final IWoodType woodType, final WoodenBlockType torchBlockType,
                                                        final WoodenBlockType wallTorchBlockType) {
        final RegistryObject<Block> torch = ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, torchBlockType);
        final RegistryObject<Block> wallTorch =
            ILikeWood.BLOCK_REGISTRY.getRegistryObject(woodType, wallTorchBlockType);
        return this.registry.register(torch.getId().getPath(),
            () -> new WoodenWallOrFloorItem(torchBlockType,
                torch.get(),
                wallTorch.get(),
                new Item.Properties().group(ItemGroup.DECORATIONS)));
    }

    private RegistryObject<Item> registerTorchBlockItem(final IWoodType woodType) {
        return this.registerTorchBlockItem(woodType, WoodenBlockType.TORCH, WoodenBlockType.WALL_TORCH);
    }

    private RegistryObject<Item> registerSoulTorchBlockItem(final IWoodType woodType) {
        return this.registerTorchBlockItem(woodType, WoodenBlockType.SOUL_TORCH, WoodenBlockType.WALL_SOUL_TORCH);
    }
}
