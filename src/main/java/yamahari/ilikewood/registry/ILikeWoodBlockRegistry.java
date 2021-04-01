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
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import java.util.*;
import java.util.function.Function;

public final class ILikeWoodBlockRegistry {
    public static final DeferredRegister<Block> REGISTRY =
        DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Block>>> registryObjects = new HashMap<>();
        final Map<IWoodType, Map<DyeColor, RegistryObject<Block>>> bedRegistryObjects = new HashMap<>();

        final Map<IWoodType, RegistryObject<Block>> panels = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsStairs = new HashMap<>();
        final Map<IWoodType, RegistryObject<Block>> panelsSlab = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final AbstractBlock.Properties properties =
                ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getProperties();
            final Set<WoodenObjectType> objectTypes = woodType.getObjectTypes();
            if (objectTypes.contains(WoodenObjectTypes.PANELS)) {
                final RegistryObject<Block> panel =
                    panels.computeIfAbsent(woodType, w -> registerPanelsBlock(w, properties));
                if (objectTypes.contains(WoodenObjectTypes.STAIRS)) {
                    panelsStairs.put(woodType, registerPanelsStairsBlock(woodType, panel, properties));
                }
                if (objectTypes.contains(WoodenObjectTypes.SLAB)) {
                    panelsSlab.put(woodType, registerPanelsSlabBlock(woodType, properties));
                }
            }
            if (objectTypes.contains(WoodenObjectTypes.BED)) {
                final Map<DyeColor, RegistryObject<Block>> beds = new EnumMap<>(DyeColor.class);
                for (final DyeColor color : DyeColor.values()) {
                    beds.put(color, registerBedBlock(woodType, color));
                }
                bedRegistryObjects.put(woodType, beds);
            }
        });

        registryObjects.put(WoodenObjectTypes.PANELS, Collections.unmodifiableMap(panels));
        registryObjects.put(WoodenObjectTypes.STAIRS, Collections.unmodifiableMap(panelsStairs));
        registryObjects.put(WoodenObjectTypes.SLAB, Collections.unmodifiableMap(panelsSlab));
        registerBlocks(registryObjects, WoodenObjectTypes.BARREL, ILikeWoodBlockRegistry::registerBarrelBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.BOOKSHELF, ILikeWoodBlockRegistry::registerBookshelfBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.CHEST, ILikeWoodBlockRegistry::registerChestBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.COMPOSTER, ILikeWoodBlockRegistry::registerComposterBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.WALL, ILikeWoodBlockRegistry::registerWallBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.LADDER, ILikeWoodBlockRegistry::registerLadderBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.TORCH, ILikeWoodBlockRegistry::registerTorchBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.WALL_TORCH, ILikeWoodBlockRegistry::registerWallTorchBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.SOUL_TORCH, ILikeWoodBlockRegistry::registerSoulTorchBlock);
        registerBlocks(registryObjects,
            WoodenObjectTypes.WALL_SOUL_TORCH,
            ILikeWoodBlockRegistry::registerWallSoulTorchBlock);
        registerBlocks(registryObjects,
            WoodenObjectTypes.CRAFTING_TABLE,
            ILikeWoodBlockRegistry::registerCraftingTableBlock);
        registerBlocks(registryObjects,
            WoodenObjectTypes.SCAFFOLDING,
            ILikeWoodBlockRegistry::registerScaffoldingBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.LECTERN, ILikeWoodBlockRegistry::registerLecternBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.POST, ILikeWoodBlockRegistry::registerPostBlock);
        registerBlocks(registryObjects,
            WoodenObjectTypes.STRIPPED_POST,
            ILikeWoodBlockRegistry::registerStrippedPostBlock);
        registerBlocks(registryObjects, WoodenObjectTypes.SAWMILL, ILikeWoodBlockRegistry::registerSawmillBlock);

        WoodenBlocks.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
        WoodenBlocks.BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private static Map<IWoodType, RegistryObject<Block>> registerBlocks(final WoodenObjectType objectType,
                                                                        final Function<IWoodType, RegistryObject<Block>> function) {
        final Map<IWoodType, RegistryObject<Block>> blocks = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getObjectTypes().contains(objectType))
            .forEach(woodType -> blocks.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(blocks);
    }

    private static void registerBlocks(
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Block>>> registryObjects,
        final WoodenObjectType objectType, final Function<IWoodType, RegistryObject<Block>> function) {
        registryObjects.put(objectType, registerBlocks(objectType, function));
    }

    private static RegistryObject<Block> registerBarrelBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.BARREL.getName()),
            () -> new WoodenBarrelBlock(woodType));
    }

    private static RegistryObject<Block> registerBookshelfBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.BOOKSHELF.getName()),
            () -> new WoodenBookshelfBlock(woodType));
    }

    private static RegistryObject<Block> registerChestBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.CHEST.getName()),
            () -> new WoodenChestBlock(woodType));
    }

    private static RegistryObject<Block> registerComposterBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.COMPOSTER.getName()),
            () -> new WoodenComposterBlock(woodType));
    }

    private static RegistryObject<Block> registerPanelsBlock(final IWoodType woodType, Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.PANELS.getName()),
            () -> new WoodenBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerPanelsStairsBlock(final IWoodType woodType,
                                                                   final RegistryObject<Block> modelBlock,
                                                                   Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(),
            WoodenObjectTypes.PANELS.getName(),
            WoodenObjectTypes.STAIRS.getName()),
            () -> new WoodenStairsBlock(woodType, modelBlock.get().getDefaultState(), properties));
    }

    private static RegistryObject<Block> registerPanelsSlabBlock(final IWoodType woodType,
                                                                 Block.Properties properties) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(),
            WoodenObjectTypes.PANELS.getName(),
            WoodenObjectTypes.SLAB.getName()), () -> new WoodenSlabBlock(woodType, properties));
    }

    private static RegistryObject<Block> registerWallBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.WALL.getName()),
            () -> new WoodenWallBlock(woodType));
    }

    private static RegistryObject<Block> registerLadderBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.LADDER.getName()),
            () -> new WoodenLadderBlock(woodType));
    }

    private static RegistryObject<Block> registerTorchBlock(final WoodenObjectType torchObjectType,
                                                            final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), torchObjectType.getName()),
            () -> new WoodenTorchBlock(woodType));
    }

    private static RegistryObject<Block> registerWallTorchBlock(final WoodenObjectType torchObjectType,
                                                                final WoodenObjectType wallTorchObjectType,
                                                                final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), wallTorchObjectType.getName()), () -> {
            final Block torch = WoodenBlocks.REGISTRY_OBJECTS.get(torchObjectType).get(woodType).get();
            return new WoodenWallTorchBlock(woodType, Block.Properties.from(torch).lootFrom(torch));
        });
    }

    private static RegistryObject<Block> registerTorchBlock(final IWoodType woodType) {
        return registerTorchBlock(WoodenObjectTypes.TORCH, woodType);
    }

    private static RegistryObject<Block> registerWallTorchBlock(final IWoodType woodType) {
        return registerWallTorchBlock(WoodenObjectTypes.TORCH, WoodenObjectTypes.WALL_TORCH, woodType);
    }

    private static RegistryObject<Block> registerCraftingTableBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.CRAFTING_TABLE.getName()),
            () -> new WoodenCraftingTableBlock(woodType));
    }

    private static RegistryObject<Block> registerScaffoldingBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.SCAFFOLDING.getName()),
            () -> new WoodenScaffoldingBlock(woodType));
    }

    private static RegistryObject<Block> registerLecternBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.LECTERN.getName()),
            () -> new WoodenLecternBlock(woodType));
    }

    private static RegistryObject<Block> registerPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.POST.getName()),
            () -> new WoodenPostBlock(woodType));
    }

    private static RegistryObject<Block> registerStrippedPostBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName("stripped", woodType.getName(), WoodenObjectTypes.POST.getName()),
            () -> new WoodenStrippedPostBlock(woodType));
    }

    private static RegistryObject<Block> registerBedBlock(final IWoodType woodType, final DyeColor color) {
        return REGISTRY.register(Util.toRegistryName(color.toString(),
            woodType.getName(),
            WoodenObjectTypes.BED.getName()), () -> new WoodenBedBlock(woodType, color));
    }

    private static RegistryObject<Block> registerSawmillBlock(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.SAWMILL.getName()),
            () -> new WoodenSawmillBlock(woodType));
    }

    private static RegistryObject<Block> registerSoulTorchBlock(final IWoodType woodType) {
        return registerTorchBlock(WoodenObjectTypes.SOUL_TORCH, woodType);
    }

    private static RegistryObject<Block> registerWallSoulTorchBlock(final IWoodType woodType) {
        return registerWallTorchBlock(WoodenObjectTypes.SOUL_TORCH, WoodenObjectTypes.WALL_SOUL_TORCH, woodType);
    }
}
