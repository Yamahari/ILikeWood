package yamahari.ilikewood;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import yamahari.ilikewood.proxy.ClientProxy;
import yamahari.ilikewood.proxy.CommonProxy;
import yamahari.ilikewood.proxy.IProxy;
import yamahari.ilikewood.registry.*;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.resource.WoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.WoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.registry.woodtype.WoodTypeRegistry;
import yamahari.ilikewood.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Mod(Constants.MOD_ID)
public final class ILikeWood {
    public static final Logger LOGGER = LogManager.getLogger(ILikeWood.class);
    public static final WoodTypeRegistry WOOD_TYPE_REGISTRY = new WoodTypeRegistry();
    public static final WoodenItemTierRegistry WOODEN_ITEM_TIER_REGISTRY = new WoodenItemTierRegistry();
    public static final WoodenResourceRegistry WOODEN_RESOURCE_REGISTRY = new WoodenResourceRegistry();
    public static final ILikeWoodBlockRegistry BLOCK_REGISTRY = new ILikeWoodBlockRegistry();
    public static final ILikeWoodBlockItemRegistry BLOCK_ITEM_REGISTRY = new ILikeWoodBlockItemRegistry();
    public static final ILikeWoodItemRegistry ITEM_REGISTRY = new ILikeWoodItemRegistry();
    public static final ILikeWoodTieredItemRegistry TIERED_ITEM_REGISTRY = new ILikeWoodTieredItemRegistry();
    public static final ILikeWoodEntityTypeRegistry ENTITY_TYPE_REGISTRY = new ILikeWoodEntityTypeRegistry();
    public static final List<IModPlugin> PLUGINS = new ArrayList<>();
    private static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public ILikeWood() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(PROXY::onFMLCommonSetup);
        modEventBus.addListener(PROXY::onFMLClientSetup);

        getPlugins();

        for (final IModPlugin plugin : PLUGINS) {
            if (ModList.get().isLoaded(plugin.getModId())) {
                plugin.registerWoodTypes(WOOD_TYPE_REGISTRY);
                plugin.registerWoodenItemTiers(WOODEN_ITEM_TIER_REGISTRY);
                plugin.registerWoodenResources(WOODEN_RESOURCE_REGISTRY);
            }
        }

        // checkWoodTypes();

        BLOCK_REGISTRY.register(modEventBus);
        BLOCK_ITEM_REGISTRY.register(modEventBus);
        ITEM_REGISTRY.register(modEventBus);
        TIERED_ITEM_REGISTRY.register(modEventBus);
        ENTITY_TYPE_REGISTRY.register(modEventBus);
        ILikeWoodTileEntityTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodContainerRegistry.REGISTRY.register(modEventBus);

        ILikeWoodRecipeSerializerRegistry.REGISTRY.register(modEventBus);

        for (final IModPlugin plugin : PLUGINS) {
            plugin.acceptBlockRegistry(BLOCK_REGISTRY);
            plugin.acceptBlockItemRegistry(BLOCK_ITEM_REGISTRY);
            plugin.acceptItemRegistry(ITEM_REGISTRY);
            plugin.acceptTieredItemRegistry(TIERED_ITEM_REGISTRY);
            plugin.acceptEntityTypeRegistry(ENTITY_TYPE_REGISTRY);
        }
    }

    public static Block getBlock(final IWoodType woodType, final WoodenBlockType blockType) {
        if (woodType.getBlockTypes().contains(blockType)) {
            return BLOCK_REGISTRY.getObject(woodType, blockType);
        }
        if (woodType.getBuiltinBlockTypes().contains(blockType)) {
            return ForgeRegistries.BLOCKS.getValue(WOODEN_RESOURCE_REGISTRY
                .getBlockResource(woodType, blockType)
                .getResource());
        }
        throw new IllegalArgumentException("");
    }

    public static Stream<Block> getBlocks(final WoodenBlockType blockType) {
        return WOOD_TYPE_REGISTRY.getWoodTypes().map(woodType -> getBlock(woodType, blockType));
    }

    public static Stream<Block> getBlocks(Stream<WoodenBlockType> blockTypes) {
        return blockTypes.flatMap(ILikeWood::getBlocks);
    }

    public static Item getItem(final IWoodType woodType, final WoodenItemType itemType) {
        if (woodType.getItemTypes().contains(itemType)) {
            return ITEM_REGISTRY.getObject(woodType, itemType);
        }
        if (woodType.getBuiltinItemTypes().contains(itemType)) {
            return ForgeRegistries.ITEMS.getValue(WOODEN_RESOURCE_REGISTRY
                .getItemResource(woodType, itemType)
                .getResource());
        }
        throw new IllegalArgumentException("");
    }

    public static Stream<Item> getItems(final WoodenItemType itemType) {
        return WOOD_TYPE_REGISTRY.getWoodTypes().map(woodType -> getItem(woodType, itemType));
    }

    public static Stream<Item> getItems(Stream<WoodenItemType> itemTypes) {
        return itemTypes.flatMap(ILikeWood::getItems);
    }

    private static void getPlugins() {
        final List<String> names = ModList
            .get()
            .getAllScanData()
            .stream()
            .flatMap(scanData -> scanData.getAnnotations().stream())
            .filter(annotationData -> Objects.equals(annotationData.annotationType(),
                Type.getType(ILikeWoodPlugin.class)))
            .map(ModFileScanData.AnnotationData::memberName)
            .toList();

        for (final String name : names) {
            try {
                PLUGINS.add(Class.forName(name).asSubclass(IModPlugin.class).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) {
                LOGGER.error("Failed to load: {}", name, e);
            }
        }
    }

    // TODO check fuel maps & maybe delay throwing exception until every wood type has been checked
    /*private static void checkWoodTypes() {
        WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final Set<WoodenObjectType> objectTypes = woodType.getObjectTypes();
            final Set<WoodenObjectType> builtinObjectTypes = woodType.getBuiltinObjectTypes();
            for (final WoodenObjectType objectType : objectTypes) {
                if (objectType.requiresPlanks() && !WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenObjectType[%s] that requires planks resource but no planks resource was registered",
                        woodType.getName(),
                        objectType.getName()));
                }

                if (objectType.requiresSlab() && !WOODEN_RESOURCE_REGISTRY.hasSlab(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenObjectType[%s] that requires slab resource but no slab resource was registered",
                        woodType.getName(),
                        objectType.getName()));
                }

                if (objectType.requiresLog() && !WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenObjectType[%s] that requires log resource but no log resource was registered",
                        woodType.getName(),
                        objectType.getName()));
                }

                if (objectType.requiresStrippedLog() && !WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenObjectType[%s] that requires stripped log resource but no stripped log resource was registered",
                        woodType.getName(),
                        objectType.getName()));
                }
                final Set<WoodenObjectType> dependencies = objectType.getDependencies();
                for (final WoodenObjectType dependency : dependencies) {
                    if (!(objectTypes.contains(dependency) || builtinObjectTypes.contains(dependency))) {
                        throw new RuntimeException(String.format(
                            "WoodType[%s] has WoodenObjectType[%s] that depends on WoodenObjectType[%s] but no matching object type was found",
                            woodType.getName(),
                            objectType.getName(),
                            dependency.getName()));
                    }
                }
            }

            if (objectTypes.contains(WoodenObjectTypes.TORCH) && !objectTypes.contains(WoodenObjectTypes.WALL_TORCH)) {
                throw new RuntimeException(String.format(
                    "WoodType[%s] has 'torch' object that requires 'wall_torch' object type but no matching object type was found",
                    woodType.getName()));
            }

            if (objectTypes.contains(WoodenObjectTypes.SOUL_TORCH) &&
                !objectTypes.contains(WoodenObjectTypes.WALL_SOUL_TORCH)) {
                throw new RuntimeException(String.format(
                    "WoodType[%s] has 'soul_torch' object that requires 'soul_wall_torch' object type but no matching object type was found",
                    woodType.getName()));
            }

            final Set<WoodenTieredObjectType> tieredObjectTypes = woodType.getTieredObjectTypes();
            for (final WoodenTieredObjectType tieredObjectType : tieredObjectTypes) {
                if (!(objectTypes.contains(WoodenObjectTypes.STICK) ||
                      builtinObjectTypes.contains(WoodenObjectTypes.STICK))) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenTieredObjectType[%s] that requires 'stick' object type but no matching object type was found",
                        woodType.getName(),
                        tieredObjectType.getName()));
                }
            }

            for (final WoodenObjectType builtinObjectType : builtinObjectTypes) {
                if (!WOODEN_RESOURCE_REGISTRY.hasObject(woodType, builtinObjectType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has builtin WoodenObjectType[%s] but no object resource was registered",
                        woodType.getName(),
                        builtinObjectType.getName()));
                }
                if (objectTypes.contains(builtinObjectType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenObjectType[%s] that is also marked as 'builtin'.",
                        woodType.getName(),
                        builtinObjectType.getName()));
                }
            }
        });
    }*/
}
