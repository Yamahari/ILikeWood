package yamahari.ilikewood.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.block.*;
import yamahari.ilikewood.block.post.WoodenPostBlock;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.block.torch.WoodenTorchBlock;
import yamahari.ilikewood.block.torch.WoodenWallTorchBlock;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ILikeWoodBlockRegistry {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Block>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.BARREL, registerSimpleBlocks(ILikeWoodBlockRegistry::registerBarrelBlock));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerSimpleBlocks(ILikeWoodBlockRegistry::registerBookshelfBlock));
        registryObjects.put(WoodenObjectType.CHEST, registerSimpleBlocks(ILikeWoodBlockRegistry::registerChestBlock));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerSimpleBlocks(ILikeWoodBlockRegistry::registerComposterBlock));
        registryObjects.put(WoodenObjectType.WALL, registerSimpleBlocks(ILikeWoodBlockRegistry::registerWallBlock));
        registryObjects.put(WoodenObjectType.LADDER, registerSimpleBlocks(ILikeWoodBlockRegistry::registerLadderBlock));
        registryObjects.put(WoodenObjectType.TORCH, registerSimpleBlocks(ILikeWoodBlockRegistry::registerTorchBlock));
        registryObjects.put(WoodenObjectType.WALL_TORCH, registerSimpleBlocks(ILikeWoodBlockRegistry::registerWallTorchBlock));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerSimpleBlocks(ILikeWoodBlockRegistry::registerCraftingTableBlock));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerSimpleBlocks(ILikeWoodBlockRegistry::registerScaffoldingBlock));
        registryObjects.put(WoodenObjectType.LECTERN, registerSimpleBlocks(ILikeWoodBlockRegistry::registerLecternBlock));
        registryObjects.put(WoodenObjectType.POST, registerSimpleBlocks(ILikeWoodBlockRegistry::registerPostBlock));
        registryObjects.put(WoodenObjectType.STRIPPED_POST, registerSimpleBlocks(ILikeWoodBlockRegistry::registerStrippedPostBlock));

        /*final Map<IWoodType, Block.Properties> woodProperties = new EnumMap<>(IWoodType.class);
        woodProperties.put(IWoodType.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS));
        woodProperties.put(IWoodType.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS));
        woodProperties.put(IWoodType.CRIMSON, Block.Properties.from(Blocks.CRIMSON_PLANKS));
        woodProperties.put(IWoodType.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS));
        woodProperties.put(IWoodType.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS));
        woodProperties.put(IWoodType.OAK, Block.Properties.from(Blocks.OAK_PLANKS));
        woodProperties.put(IWoodType.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS));
        woodProperties.put(IWoodType.WARPED, Block.Properties.from(Blocks.WARPED_PLANKS));
        woodProperties.put(IWoodType.CHERRY, Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.DEAD, Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.FIR, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.HELLBARK, Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.JACARANDA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.MAGIC, Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.MAHOGANY, Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.PALM, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.REDWOOD, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.UMBRAN, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        woodProperties.put(IWoodType.WILLOW, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));*/

        final Map<IWoodType, RegistryObject<Block>> panels = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsStairs = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsSlab = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final AbstractBlock.Properties properties = woodType.getPanelProperties();
            final RegistryObject<Block> panel = panels.computeIfAbsent(woodType, w -> registerPanelsBlock(w, properties));
            panelsStairs.put(woodType, registerPanelsStairsBlock(woodType, panel, properties));
            panelsSlab.put(woodType, registerPanelsSlabBlock(woodType, properties));
        });

        registryObjects.put(WoodenObjectType.PANELS, Collections.unmodifiableMap(panels));
        registryObjects.put(WoodenObjectType.STAIRS, Collections.unmodifiableMap(panelsStairs));
        registryObjects.put(WoodenObjectType.SLAB, Collections.unmodifiableMap(panelsSlab));

        WoodenBlocks.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);

        final Map<IWoodType, Map<DyeColor, RegistryObject<Block>>> bedRegistryObjects = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final Map<DyeColor, RegistryObject<Block>> beds = new EnumMap<>(DyeColor.class);
            for (final DyeColor color : DyeColor.values())
                beds.put(color, registerBedBlock(woodType, color));
            bedRegistryObjects.put(woodType, beds);
        });

        WoodenBlocks.BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private static Map<IWoodType, RegistryObject<Block>> registerSimpleBlocks(final Function<IWoodType, RegistryObject<Block>> function) {
        final Map<IWoodType, RegistryObject<Block>> blocks = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> blocks.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(blocks);
    }

    private static RegistryObject<Block> registerBarrelBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.BARREL.toString()), () -> new WoodenBarrelBlock(woodType));
    }

    private static RegistryObject<Block> registerBookshelfBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.BOOKSHELF.toString()), () -> new WoodenBookshelfBlock(woodType));
    }

    private static RegistryObject<Block> registerChestBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CHEST.toString()), () -> new WoodenChestBlock(woodType));
    }

    private static RegistryObject<Block> registerComposterBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.COMPOSTER.toString()), () -> new WoodenComposterBlock(woodType));
    }

    private static RegistryObject<Block> registerPanelsBlock(final IWoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString()), () -> new WoodenBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerPanelsStairsBlock(final IWoodType woodType, final RegistryObject<Block> modelBlock, Block.Properties properties) {
        return REGISTRY.register(
                Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString()),
                () -> new WoodenStairsBlock(woodType, modelBlock.get().getDefaultState(), properties));
    }

    private static RegistryObject<Block> registerPanelsSlabBlock(final IWoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString()), () -> new WoodenSlabBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerWallBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.WALL.toString()), () -> new WoodenWallBlock(woodType));
    }

    private static RegistryObject<Block> registerLadderBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.LADDER.toString()), () -> new WoodenLadderBlock(woodType));
    }

    private static RegistryObject<Block> registerTorchBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.TORCH.toString()), () -> new WoodenTorchBlock(woodType));
    }

    private static RegistryObject<Block> registerWallTorchBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.WALL_TORCH.toString()), () -> {
            final Block torch = WoodenBlocks.REGISTRY_OBJECTS.get(WoodenObjectType.TORCH).get(woodType).get();
            return new WoodenWallTorchBlock(woodType, Block.Properties.from(torch).lootFrom(torch));
        });
    }

    private static RegistryObject<Block> registerCraftingTableBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CRAFTING_TABLE.toString()), () -> new WoodenCraftingTableBlock(woodType));
    }

    private static RegistryObject<Block> registerScaffoldingBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.SCAFFOLDING.toString()), () -> new WoodenScaffoldingBlock(woodType));
    }

    private static RegistryObject<Block> registerLecternBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.LECTERN.toString()), () -> new WoodenLecternBlock(woodType));
    }

    private static RegistryObject<Block> registerPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.POST.toString()), () -> new WoodenPostBlock(woodType));
    }

    private static RegistryObject<Block> registerStrippedPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName("stripped", woodType.toString(), WoodenObjectType.POST.toString()), () -> new WoodenStrippedPostBlock(woodType));
    }

    private static RegistryObject<Block> registerBedBlock(final IWoodType woodType, final DyeColor color) {
        return REGISTRY.register(Util.toRegistryName(color.toString(), woodType.toString(), WoodenObjectType.BED.toString()), () -> new WoodenBedBlock(woodType, color));
    }
}
