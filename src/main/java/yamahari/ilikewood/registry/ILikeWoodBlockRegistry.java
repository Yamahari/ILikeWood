package yamahari.ilikewood.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.*;
import yamahari.ilikewood.block.post.WoodenPostBlock;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.block.torch.WoodenTorchBlock;
import yamahari.ilikewood.block.torch.WoodenWallTorchBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ILikeWoodBlockRegistry {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Block>>> registryObjects = new EnumMap<>(WoodenObjectType.class);
        final Map<IWoodType, Map<DyeColor, RegistryObject<Block>>> bedRegistryObjects = new HashMap<>();

        final Map<IWoodType, RegistryObject<Block>> panels = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsStairs = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsSlab = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(Util.HAS_PLANKS)
                .forEach(woodType -> {
                    final AbstractBlock.Properties properties = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getProperties();
                    final RegistryObject<Block> panel = panels.computeIfAbsent(woodType, w -> registerPanelsBlock(w, properties));
                    panelsStairs.put(woodType, registerPanelsStairsBlock(woodType, panel, properties));
                    panelsSlab.put(woodType, registerPanelsSlabBlock(woodType, properties));
                    final Map<DyeColor, RegistryObject<Block>> beds = new EnumMap<>(DyeColor.class);
                    for (final DyeColor color : DyeColor.values())
                        beds.put(color, registerBedBlock(woodType, color));
                    bedRegistryObjects.put(woodType, beds);
                });

        registryObjects.put(WoodenObjectType.PANELS, Collections.unmodifiableMap(panels));
        registryObjects.put(WoodenObjectType.STAIRS, Collections.unmodifiableMap(panelsStairs));
        registryObjects.put(WoodenObjectType.SLAB, Collections.unmodifiableMap(panelsSlab));
        registryObjects.put(WoodenObjectType.BARREL, registerBlocksWith(ILikeWoodBlockRegistry::registerBarrelBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerBlocksWith(ILikeWoodBlockRegistry::registerBookshelfBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.CHEST, registerBlocksWith(ILikeWoodBlockRegistry::registerChestBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerBlocksWith(ILikeWoodBlockRegistry::registerComposterBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.WALL, registerBlocksWith(ILikeWoodBlockRegistry::registerWallBlock, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.LADDER, registerBlocksWith(ILikeWoodBlockRegistry::registerLadderBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.TORCH, registerBlocksWith(ILikeWoodBlockRegistry::registerTorchBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.WALL_TORCH, registerBlocksWith(ILikeWoodBlockRegistry::registerWallTorchBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerBlocksWith(ILikeWoodBlockRegistry::registerCraftingTableBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerBlocksWith(ILikeWoodBlockRegistry::registerScaffoldingBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.LECTERN, registerBlocksWith(ILikeWoodBlockRegistry::registerLecternBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB)));
        registryObjects.put(WoodenObjectType.POST, registerBlocksWith(ILikeWoodBlockRegistry::registerPostBlock, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.STRIPPED_POST, registerBlocksWith(ILikeWoodBlockRegistry::registerStrippedPostBlock, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.SAWMILL, registerBlocksWith(ILikeWoodBlockRegistry::registerSawmillBlock, Util.HAS_PLANKS.and(Util.HAS_SLAB).and(Util.HAS_LOG).and(Util.HAS_STRIPPED_LOG)));
        WoodenBlocks.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
        WoodenBlocks.BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private static Map<IWoodType, RegistryObject<Block>> registerBlocksWith(final Function<IWoodType, RegistryObject<Block>> function,
                                                                            final Predicate<IWoodType> predicate) {
        final Map<IWoodType, RegistryObject<Block>> blocks = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(predicate)
                .forEach(woodType -> blocks.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(blocks);
    }

    private static RegistryObject<Block> registerBarrelBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.BARREL.toString()), () -> new WoodenBarrelBlock(woodType));
    }

    private static RegistryObject<Block> registerBookshelfBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.BOOKSHELF.toString()), () -> new WoodenBookshelfBlock(woodType));
    }

    private static RegistryObject<Block> registerChestBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.CHEST.toString()), () -> new WoodenChestBlock(woodType));
    }

    private static RegistryObject<Block> registerComposterBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.COMPOSTER.toString()), () -> new WoodenComposterBlock(woodType));
    }

    private static RegistryObject<Block> registerPanelsBlock(final IWoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.PANELS.toString()), () -> new WoodenBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerPanelsStairsBlock(final IWoodType woodType, final RegistryObject<Block> modelBlock, Block.Properties properties) {
        return REGISTRY.register(
                Util.toRegistryName(woodType.getName(), WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString()),
                () -> new WoodenStairsBlock(woodType, modelBlock.get().getDefaultState(), properties));
    }

    private static RegistryObject<Block> registerPanelsSlabBlock(final IWoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString()), () -> new WoodenSlabBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerWallBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.WALL.toString()), () -> new WoodenWallBlock(woodType));
    }

    private static RegistryObject<Block> registerLadderBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.LADDER.toString()), () -> new WoodenLadderBlock(woodType));
    }

    private static RegistryObject<Block> registerTorchBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.TORCH.toString()), () -> new WoodenTorchBlock(woodType));
    }

    private static RegistryObject<Block> registerWallTorchBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.WALL_TORCH.toString()), () -> {
            final Block torch = WoodenBlocks.REGISTRY_OBJECTS.get(WoodenObjectType.TORCH).get(woodType).get();
            return new WoodenWallTorchBlock(woodType, Block.Properties.from(torch).lootFrom(torch));
        });
    }

    private static RegistryObject<Block> registerCraftingTableBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.CRAFTING_TABLE.toString()), () -> new WoodenCraftingTableBlock(woodType));
    }

    private static RegistryObject<Block> registerScaffoldingBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.SCAFFOLDING.toString()), () -> new WoodenScaffoldingBlock(woodType));
    }

    private static RegistryObject<Block> registerLecternBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.LECTERN.toString()), () -> new WoodenLecternBlock(woodType));
    }

    private static RegistryObject<Block> registerPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.POST.toString()), () -> new WoodenPostBlock(woodType));
    }

    private static RegistryObject<Block> registerStrippedPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName("stripped", woodType.getName(), WoodenObjectType.POST.toString()), () -> new WoodenStrippedPostBlock(woodType));
    }

    private static RegistryObject<Block> registerBedBlock(final IWoodType woodType, final DyeColor color) {
        return REGISTRY.register(Util.toRegistryName(color.toString(), woodType.getName(), WoodenObjectType.BED.toString()), () -> new WoodenBedBlock(woodType, color));
    }

    private static RegistryObject<Block> registerSawmillBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.SAWMILL.toString()), () -> new WoodenSawmillBlock(woodType));
    }
}
