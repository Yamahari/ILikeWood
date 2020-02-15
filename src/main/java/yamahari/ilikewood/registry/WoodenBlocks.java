package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.block.*;
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
    public static final DeferredRegister<Block> REGISTRY = new DeferredRegister<>(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<Block>>> REGISTRY_OBJECTS;

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

        final Map<WoodType, RegistryObject<Block>> panels = new EnumMap<>(WoodType.class);
        panels.put(WoodType.ACACIA, registerPanelsBlock(WoodType.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS)));
        panels.put(WoodType.BIRCH, registerPanelsBlock(WoodType.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS)));
        panels.put(WoodType.DARK_OAK, registerPanelsBlock(WoodType.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS)));
        panels.put(WoodType.JUNGLE, registerPanelsBlock(WoodType.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS)));
        panels.put(WoodType.OAK, registerPanelsBlock(WoodType.OAK, Block.Properties.from(Blocks.OAK_PLANKS)));
        panels.put(WoodType.SPRUCE, registerPanelsBlock(WoodType.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS)));
        registryObjects.put(WoodenObjectType.PANELS, Collections.unmodifiableMap(panels));

        final Map<WoodType, RegistryObject<Block>> panelsStairs = new EnumMap<>(WoodType.class);
        panelsStairs.put(WoodType.ACACIA, registerPanelsStairsBlock(WoodType.ACACIA, panels.get(WoodType.ACACIA), Block.Properties.from(Blocks.ACACIA_STAIRS)));
        panelsStairs.put(WoodType.BIRCH, registerPanelsStairsBlock(WoodType.BIRCH, panels.get(WoodType.BIRCH), Block.Properties.from(Blocks.BIRCH_STAIRS)));
        panelsStairs.put(WoodType.DARK_OAK, registerPanelsStairsBlock(WoodType.DARK_OAK, panels.get(WoodType.DARK_OAK), Block.Properties.from(Blocks.DARK_OAK_STAIRS)));
        panelsStairs.put(WoodType.JUNGLE, registerPanelsStairsBlock(WoodType.JUNGLE, panels.get(WoodType.JUNGLE), Block.Properties.from(Blocks.JUNGLE_STAIRS)));
        panelsStairs.put(WoodType.OAK, registerPanelsStairsBlock(WoodType.OAK, panels.get(WoodType.OAK), Block.Properties.from(Blocks.OAK_STAIRS)));
        panelsStairs.put(WoodType.SPRUCE, registerPanelsStairsBlock(WoodType.SPRUCE, panels.get(WoodType.OAK), Block.Properties.from(Blocks.SPRUCE_STAIRS)));
        registryObjects.put(WoodenObjectType.STAIRS, Collections.unmodifiableMap(panelsStairs));

        final Map<WoodType, RegistryObject<Block>> panelsSlab = new EnumMap<>(WoodType.class);
        panelsSlab.put(WoodType.ACACIA, registerPanelsSlabBlock(WoodType.ACACIA, Block.Properties.from(Blocks.ACACIA_SLAB)));
        panelsSlab.put(WoodType.BIRCH, registerPanelsSlabBlock(WoodType.BIRCH, Block.Properties.from(Blocks.BIRCH_SLAB)));
        panelsSlab.put(WoodType.DARK_OAK, registerPanelsSlabBlock(WoodType.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_SLAB)));
        panelsSlab.put(WoodType.JUNGLE, registerPanelsSlabBlock(WoodType.JUNGLE, Block.Properties.from(Blocks.JUNGLE_SLAB)));
        panelsSlab.put(WoodType.OAK, registerPanelsSlabBlock(WoodType.OAK, Block.Properties.from(Blocks.OAK_SLAB)));
        panelsSlab.put(WoodType.SPRUCE, registerPanelsSlabBlock(WoodType.SPRUCE, Block.Properties.from(Blocks.SPRUCE_SLAB)));
        registryObjects.put(WoodenObjectType.SLAB, Collections.unmodifiableMap(panelsSlab));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
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

    private static Map<WoodType, RegistryObject<Block>> registerSimpleBlocks(final Function<WoodType, RegistryObject<Block>> function) {
        final Map<WoodType, RegistryObject<Block>> blocks = new EnumMap<>(WoodType.class);
        for (final WoodType woodType : WoodType.values()) {
            blocks.put(woodType, function.apply(woodType));
        }
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

    private static RegistryObject<Block> registerPanelsBlock(final WoodType woodType, final Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString()), () -> new WoodenBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerPanelsStairsBlock(final WoodType woodType, final RegistryObject<Block> modelBlock, final Block.Properties properties) {
        return REGISTRY.register(
                Util.toRegistryName(woodType.toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString()),
                () -> new WoodenStairsBlock(woodType, modelBlock.get().getDefaultState(), properties));
    }

    private static RegistryObject<Block> registerPanelsSlabBlock(final WoodType woodType, final Block.Properties properties) {
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
}
