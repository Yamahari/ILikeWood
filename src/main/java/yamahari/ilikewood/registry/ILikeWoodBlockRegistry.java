package yamahari.ilikewood.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.*;
import yamahari.ilikewood.block.post.WoodenPostBlock;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.block.torch.WoodenTorchBlock;
import yamahari.ilikewood.block.torch.WoodenWallTorchBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ILikeWoodBlockRegistry extends AbstractILikeWoodObjectRegistry<Block, WoodenBlockType> {
    public ILikeWoodBlockRegistry() {
        super(ForgeRegistries.BLOCKS);
    }

    @Override
    protected void register() {
        registerBlocks(WoodenBlockType.PANELS, this::registerPanelsBlock);
        registerBlocks(WoodenBlockType.PANELS_SLAB, this::registerPanelsSlabBlock);
        registerBlocks(WoodenBlockType.PANELS_STAIRS, this::registerPanelsStairsBlock);
        registerBlocks(WoodenBlockType.BARREL, this::registerBarrelBlock);
        registerBlocks(WoodenBlockType.BOOKSHELF, this::registerBookshelfBlock);
        registerBlocks(WoodenBlockType.CHEST, this::registerChestBlock);
        registerBlocks(WoodenBlockType.COMPOSTER, this::registerComposterBlock);
        registerBlocks(WoodenBlockType.WALL, this::registerWallBlock);
        registerBlocks(WoodenBlockType.LADDER, this::registerLadderBlock);
        registerBlocks(WoodenBlockType.TORCH, this::registerTorchBlock);
        registerBlocks(WoodenBlockType.WALL_TORCH, this::registerWallTorchBlock);
        registerBlocks(WoodenBlockType.SOUL_TORCH, this::registerSoulTorchBlock);
        registerBlocks(WoodenBlockType.WALL_SOUL_TORCH, this::registerWallSoulTorchBlock);
        registerBlocks(WoodenBlockType.CRAFTING_TABLE, this::registerCraftingTableBlock);
        registerBlocks(WoodenBlockType.SCAFFOLDING, this::registerScaffoldingBlock);
        registerBlocks(WoodenBlockType.LECTERN, this::registerLecternBlock);
        registerBlocks(WoodenBlockType.POST, this::registerPostBlock);
        registerBlocks(WoodenBlockType.STRIPPED_POST, this::registerStrippedPostBlock);
        registerBlocks(WoodenBlockType.SAWMILL, this::registerSawmillBlock);

        final Map<WoodenBlockType, DyeColor> colors = new HashMap<>();
        colors.put(WoodenBlockType.WHITE_BED, DyeColor.WHITE);
        colors.put(WoodenBlockType.ORANGE_BED, DyeColor.ORANGE);
        colors.put(WoodenBlockType.MAGENTA_BED, DyeColor.MAGENTA);
        colors.put(WoodenBlockType.LIGHT_BLUE_BED, DyeColor.LIGHT_BLUE);
        colors.put(WoodenBlockType.YELLOW_BED, DyeColor.YELLOW);
        colors.put(WoodenBlockType.LIME_BED, DyeColor.LIME);
        colors.put(WoodenBlockType.PINK_BED, DyeColor.PINK);
        colors.put(WoodenBlockType.GRAY_BED, DyeColor.GRAY);
        colors.put(WoodenBlockType.LIGHT_GRAY_BED, DyeColor.LIGHT_GRAY);
        colors.put(WoodenBlockType.CYAN_BED, DyeColor.CYAN);
        colors.put(WoodenBlockType.PURPLE_BED, DyeColor.PURPLE);
        colors.put(WoodenBlockType.BLUE_BED, DyeColor.BLUE);
        colors.put(WoodenBlockType.BROWN_BED, DyeColor.BROWN);
        colors.put(WoodenBlockType.GREEN_BED, DyeColor.GREEN);
        colors.put(WoodenBlockType.RED_BED, DyeColor.RED);
        colors.put(WoodenBlockType.BLACK_BED, DyeColor.BLACK);

        WoodenBlockType
            .getBeds()
            .forEach(blockType -> registerBlocks(blockType,
                woodType -> this.registerBedBlock(woodType, colors.get(blockType))));
    }

    @Override
    public Stream<RegistryObject<Block>> getRegistryObjects(final WoodenBlockType blockType) {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(blockType))
            .map(woodType -> this.getRegistryObject(woodType, blockType));
    }

    private void registerBlocks(final WoodenBlockType blockType,
                                final Function<IWoodType, RegistryObject<Block>> function) {
        final Map<IWoodType, RegistryObject<Block>> blocks = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(blockType))
            .forEach(woodType -> blocks.put(woodType, function.apply(woodType)));
        this.registryObjects.put(blockType, Collections.unmodifiableMap(blocks));
    }

    private RegistryObject<Block> register(final IWoodType woodType, final WoodenBlockType blockType,
                                           final Supplier<? extends Block> supplier) {
        return this.registry.register(Util.toRegistryName(woodType.getName(), blockType.getName()), supplier);
    }

    private RegistryObject<Block> registerBarrelBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.BARREL, () -> new WoodenBarrelBlock(woodType));
    }

    private RegistryObject<Block> registerBookshelfBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.BOOKSHELF, () -> new WoodenBookshelfBlock(woodType));
    }

    private RegistryObject<Block> registerChestBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.CHEST, () -> new WoodenChestBlock(woodType));
    }

    private RegistryObject<Block> registerComposterBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.COMPOSTER, () -> new WoodenComposterBlock(woodType));
    }

    private RegistryObject<Block> registerPanelsBlock(final IWoodType woodType) {
        final AbstractBlock.Properties properties =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getProperties();
        return this.register(woodType, WoodenBlockType.PANELS, () -> new WoodenBlock(woodType, properties));
    }

    private RegistryObject<Block> registerPanelsStairsBlock(final IWoodType woodType) {
        return this.register(woodType,
            WoodenBlockType.PANELS_STAIRS,
            () -> new WoodenStairsBlock(woodType,
                ILikeWood.getBlock(woodType, WoodenBlockType.PANELS).defaultBlockState(),
                ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getProperties()));
    }

    private RegistryObject<Block> registerPanelsSlabBlock(final IWoodType woodType) {
        final AbstractBlock.Properties properties =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getProperties();
        return this.register(woodType, WoodenBlockType.PANELS_SLAB, () -> new WoodenSlabBlock(woodType, properties));
    }

    private RegistryObject<Block> registerWallBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.WALL, () -> new WoodenWallBlock(woodType));
    }

    private RegistryObject<Block> registerLadderBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.LADDER, () -> new WoodenLadderBlock(woodType));
    }

    private RegistryObject<Block> registerTorchBlock(final WoodenBlockType torchBlockType, final IWoodType woodType) {
        return this.register(woodType, torchBlockType, () -> new WoodenTorchBlock(woodType));
    }

    private RegistryObject<Block> registerWallTorchBlock(final WoodenBlockType torchBlockType,
                                                         final WoodenBlockType wallTorchBlockType,
                                                         final IWoodType woodType) {
        return this.register(woodType, wallTorchBlockType, () -> {
            final Block torch = this.registryObjects.get(torchBlockType).get(woodType).get();
            return new WoodenWallTorchBlock(woodType, Block.Properties.copy(torch).lootFrom(() -> torch));
        });
    }

    private RegistryObject<Block> registerTorchBlock(final IWoodType woodType) {
        return registerTorchBlock(WoodenBlockType.TORCH, woodType);
    }

    private RegistryObject<Block> registerWallTorchBlock(final IWoodType woodType) {
        return registerWallTorchBlock(WoodenBlockType.TORCH, WoodenBlockType.WALL_TORCH, woodType);
    }

    private RegistryObject<Block> registerCraftingTableBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.CRAFTING_TABLE, () -> new WoodenCraftingTableBlock(woodType));
    }

    private RegistryObject<Block> registerScaffoldingBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.SCAFFOLDING, () -> new WoodenScaffoldingBlock(woodType));
    }

    private RegistryObject<Block> registerLecternBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.LECTERN, () -> new WoodenLecternBlock(woodType));
    }

    private RegistryObject<Block> registerPostBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.POST, () -> new WoodenPostBlock(woodType));
    }

    private RegistryObject<Block> registerStrippedPostBlock(final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName("stripped",
            woodType.getName(),
            WoodenBlockType.POST.getName()), () -> new WoodenStrippedPostBlock(woodType));
    }

    private RegistryObject<Block> registerBedBlock(final IWoodType woodType, final DyeColor color) {
        return this.registry.register(Util.toRegistryName(color.toString(), woodType.getName(), "bed"),
            () -> new WoodenBedBlock(woodType, color));
    }

    private RegistryObject<Block> registerSawmillBlock(final IWoodType woodType) {
        return this.register(woodType, WoodenBlockType.SAWMILL, () -> new WoodenSawmillBlock(woodType));
    }

    private RegistryObject<Block> registerSoulTorchBlock(final IWoodType woodType) {
        return registerTorchBlock(WoodenBlockType.SOUL_TORCH, woodType);
    }

    private RegistryObject<Block> registerWallSoulTorchBlock(final IWoodType woodType) {
        return registerWallTorchBlock(WoodenBlockType.SOUL_TORCH, WoodenBlockType.WALL_SOUL_TORCH, woodType);
    }
}
