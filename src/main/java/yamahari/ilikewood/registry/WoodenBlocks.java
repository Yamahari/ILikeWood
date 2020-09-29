package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.block.*;
import yamahari.ilikewood.block.post.WoodenPostBlock;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.block.torch.WoodenTorchBlock;
import yamahari.ilikewood.block.torch.WoodenWallTorchBlock;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WoodenBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<Block>>> REGISTRY_OBJECTS;
    private static final Map<WoodType, Map<DyeColor, RegistryObject<Block>>> BED_REGISTRY_OBJECTS;

    static {
        final Map<WoodenObjectType, Map<WoodType, RegistryObject<Block>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.BARREL, registerSimpleBlocks(WoodenBlocks::registerBarrelBlock));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerSimpleBlocks(WoodenBlocks::registerBookshelfBlock));
        registryObjects.put(WoodenObjectType.CHEST, registerSimpleBlocks(WoodenBlocks::registerChestBlock));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerSimpleBlocks(WoodenBlocks::registerComposterBlock));
        registryObjects.put(WoodenObjectType.WALL, registerSimpleBlocks(WoodenBlocks::registerWallBlock));
        registryObjects.put(WoodenObjectType.LADDER, registerSimpleBlocks(WoodenBlocks::registerLadderBlock));
        registryObjects.put(WoodenObjectType.TORCH, registerSimpleBlocks(WoodenBlocks::registerTorchBlock));
        registryObjects.put(WoodenObjectType.WALL_TORCH, registerSimpleBlocks(WoodenBlocks::registerWallTorchBlock));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerSimpleBlocks(WoodenBlocks::registerCraftingTableBlock));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerSimpleBlocks(WoodenBlocks::registerScaffoldingBlock));
        registryObjects.put(WoodenObjectType.LECTERN, registerSimpleBlocks(WoodenBlocks::registerLecternBlock));
        registryObjects.put(WoodenObjectType.POST, registerSimpleBlocks(WoodenBlocks::registerPostBlock));
        registryObjects.put(WoodenObjectType.STRIPPED_POST, registerSimpleBlocks(WoodenBlocks::registerStrippedPostBlock));

        final Map<WoodType, Block.Properties> woodProperties = new EnumMap<>(WoodType.class);
        woodProperties.put(WoodType.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS));
        woodProperties.put(WoodType.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS));
        woodProperties.put(WoodType.CRIMSON, Block.Properties.from(Blocks.CRIMSON_PLANKS));
        woodProperties.put(WoodType.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS));
        woodProperties.put(WoodType.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS));
        woodProperties.put(WoodType.OAK, Block.Properties.from(Blocks.OAK_PLANKS));
        woodProperties.put(WoodType.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS));
        woodProperties.put(WoodType.WARPED, Block.Properties.from(Blocks.WARPED_PLANKS));
        woodProperties.put(WoodType.CHERRY, Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.DEAD, Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.FIR, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.HELLBARK, Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.JACARANDA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.MAGIC, Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.MAHOGANY, Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.PALM, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.REDWOOD, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.UMBRAN, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(WoodType.WILLOW, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));

        final Map<WoodType, RegistryObject<Block>> panels = new EnumMap<>(WoodType.class);
        final Map<WoodType, RegistryObject<Block>> panelsStairs = new EnumMap<>(WoodType.class);
        final Map<WoodType, RegistryObject<Block>> panelsSlab = new EnumMap<>(WoodType.class);

        WoodType.getLoadedValues().forEach(woodType -> {
            final Block.Properties properties = woodProperties.get(woodType);
            final RegistryObject<Block> panel = panels.computeIfAbsent(woodType, w -> registerPanelsBlock(w, properties));
            panelsStairs.put(woodType, registerPanelsStairsBlock(woodType, panel, properties));
            panelsSlab.put(woodType, registerPanelsSlabBlock(woodType, properties));
        });

        registryObjects.put(WoodenObjectType.PANELS, Collections.unmodifiableMap(panels));
        registryObjects.put(WoodenObjectType.STAIRS, Collections.unmodifiableMap(panelsStairs));
        registryObjects.put(WoodenObjectType.SLAB, Collections.unmodifiableMap(panelsSlab));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);

        final Map<WoodType, Map<DyeColor, RegistryObject<Block>>> bedRegistryObjects = new EnumMap<>(WoodType.class);
        WoodType.getLoadedValues().forEach(woodType -> {
            final Map<DyeColor, RegistryObject<Block>> beds = new EnumMap<>(DyeColor.class);
            for (final DyeColor color : DyeColor.values())
                beds.put(color, registerBedBlock(woodType, color));
            bedRegistryObjects.put(woodType, beds);
        });

        BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private WoodenBlocks() {
    }

    public static Stream<Block> getBlocks(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static Block getBlock(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType).get();
    }

    public static Stream<Block> getBlocks(final WoodenObjectType... objectTypes) {
        return Arrays.stream(objectTypes).flatMap(WoodenBlocks::getBlocks);
    }

    public static RegistryObject<Block> getRegistryObject(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static Stream<RegistryObject<Block>> getRegistryObjects(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream();
    }

    public static Stream<Block> getBedBlocks() {
        return getBedRegistryObjects().map(RegistryObject::get);
    }

    public static Stream<Block> getBedBlocks(final WoodType woodType) {
        return getBedRegistryObjects(woodType).map(RegistryObject::get);
    }

    public static Block getBedBlock(final WoodType woodType, final DyeColor color) {
        return getBedRegistryObject(woodType, color).get();
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects() {
        return WoodType.getLoadedValues().flatMap(WoodenBlocks::getBedRegistryObjects);
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects(final WoodType woodType) {
        return Arrays.stream(DyeColor.values()).map(color -> getBedRegistryObject(woodType, color));
    }

    public static RegistryObject<Block> getBedRegistryObject(final WoodType woodType, final DyeColor color) {
        return BED_REGISTRY_OBJECTS.get(woodType).get(color);
    }

    private static Map<WoodType, RegistryObject<Block>> registerSimpleBlocks(final Function<WoodType, RegistryObject<Block>> function) {
        final Map<WoodType, RegistryObject<Block>> blocks = new EnumMap<>(WoodType.class);
        WoodType.getLoadedValues().forEach(woodType -> blocks.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(blocks);
    }

    private static RegistryObject<Block> registerBarrelBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.BARREL.toString()), () -> new WoodenBarrelBlock(woodType));
    }

    private static RegistryObject<Block> registerBookshelfBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.BOOKSHELF.toString()), () -> new WoodenBookshelfBlock(woodType));
    }

    private static RegistryObject<Block> registerChestBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CHEST.toString()), () -> new WoodenChestBlock(woodType));
    }

    private static RegistryObject<Block> registerComposterBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.COMPOSTER.toString()), () -> new WoodenComposterBlock(woodType));
    }

    private static RegistryObject<Block> registerPanelsBlock(final WoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString()), () -> new WoodenBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerPanelsStairsBlock(final WoodType woodType, final RegistryObject<Block> modelBlock, Block.Properties properties) {
        return REGISTRY.register(
                Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString()),
                () -> new WoodenStairsBlock(woodType, modelBlock.get().getDefaultState(), properties));
    }

    private static RegistryObject<Block> registerPanelsSlabBlock(final WoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString()), () -> new WoodenSlabBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerWallBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.WALL.toString()), () -> new WoodenWallBlock(woodType));
    }

    private static RegistryObject<Block> registerLadderBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.LADDER.toString()), () -> new WoodenLadderBlock(woodType));
    }

    private static RegistryObject<Block> registerTorchBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.TORCH.toString()), () -> new WoodenTorchBlock(woodType));
    }

    private static RegistryObject<Block> registerWallTorchBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.WALL_TORCH.toString()), () -> {
            final Block torch = REGISTRY_OBJECTS.get(WoodenObjectType.TORCH).get(woodType).get();
            return new WoodenWallTorchBlock(woodType, Block.Properties.from(torch).lootFrom(torch));
        });
    }

    private static RegistryObject<Block> registerCraftingTableBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CRAFTING_TABLE.toString()), () -> new WoodenCraftingTableBlock(woodType));
    }

    private static RegistryObject<Block> registerScaffoldingBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.SCAFFOLDING.toString()), () -> new WoodenScaffoldingBlock(woodType));
    }

    private static RegistryObject<Block> registerLecternBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.LECTERN.toString()), () -> new WoodenLecternBlock(woodType));
    }

    private static RegistryObject<Block> registerPostBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.POST.toString()), () -> new WoodenPostBlock(woodType));
    }

    private static RegistryObject<Block> registerStrippedPostBlock(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName("stripped", woodType.toString(), WoodenObjectType.POST.toString()), () -> new WoodenStrippedPostBlock(woodType));
    }

    private static RegistryObject<Block> registerBedBlock(final WoodType woodType, final DyeColor color) {
        return REGISTRY.register(Util.toRegistryName(color.toString(), woodType.toString(), WoodenObjectType.BED.toString()), () -> new WoodenBedBlock(woodType, color));
    }
}
