package yamahari.ilikewood;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.ILikeWoodBlockItemRegistry;
import yamahari.ilikewood.registry.ILikeWoodBlockRegistry;
import yamahari.ilikewood.registry.ILikeWoodContainerRegistry;
import yamahari.ilikewood.registry.ILikeWoodEntityTypeRegistry;
import yamahari.ilikewood.registry.ILikeWoodItemRegistry;
import yamahari.ilikewood.registry.ILikeWoodPaintingRegistry;
import yamahari.ilikewood.registry.ILikeWoodParticleTypeRegistry;
import yamahari.ilikewood.registry.ILikeWoodRecipeSerializerRegistry;
import yamahari.ilikewood.registry.ILikeWoodRecipeTypeRegister;
import yamahari.ilikewood.registry.ILikeWoodTieredItemRegistry;
import yamahari.ilikewood.registry.ILikeWoodTileEntityTypeRegistry;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.resource.WoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.WoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.registry.woodtype.WoodTypeRegistry;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.validation.ObjectTypeValidator;
import yamahari.ilikewood.validation.WoodTypeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Mod(Constants.MOD_ID)
public final class ILikeWood
{
    public static final Logger LOGGER = LogManager.getLogger(ILikeWood.class);
    public static final WoodTypeRegistry WOOD_TYPE_REGISTRY = new WoodTypeRegistry();
    public static final WoodenItemTierRegistry WOODEN_ITEM_TIER_REGISTRY = new WoodenItemTierRegistry();
    public static final WoodenResourceRegistry WOODEN_RESOURCE_REGISTRY = new WoodenResourceRegistry();
    public static final ILikeWoodBlockRegistry BLOCK_REGISTRY = new ILikeWoodBlockRegistry();
    public static final ILikeWoodBlockItemRegistry BLOCK_ITEM_REGISTRY = new ILikeWoodBlockItemRegistry();
    public static final ILikeWoodItemRegistry ITEM_REGISTRY = new ILikeWoodItemRegistry();
    public static final ILikeWoodTieredItemRegistry TIERED_ITEM_REGISTRY = new ILikeWoodTieredItemRegistry();
    public static final ILikeWoodEntityTypeRegistry ENTITY_TYPE_REGISTRY = new ILikeWoodEntityTypeRegistry();
    public static final ILikeWoodRecipeTypeRegister RECIPE_TYPE_REGISTER = new ILikeWoodRecipeTypeRegister();
    public static final List<IModPlugin> PLUGINS = new ArrayList<>();

    public ILikeWood()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ILikeWoodConfig.COMMON_SPEC);
        ConfigTracker.INSTANCE.loadConfigs(ModConfig.Type.COMMON, FMLPaths.CONFIGDIR.get());

        getPlugins();

        for (final IModPlugin plugin : PLUGINS)
        {
            if (ModList.get().isLoaded(plugin.getModId()))
            {
                plugin.registerWoodTypes(WOOD_TYPE_REGISTRY);
                plugin.registerWoodenItemTiers(WOODEN_ITEM_TIER_REGISTRY);
                plugin.registerWoodenResources(WOODEN_RESOURCE_REGISTRY);
            }
        }

        final var builder = new StringBuilder();
        if (!validate(builder))
        {
            throw new RuntimeException("Validation failed! See log for more details." + System.lineSeparator() + builder);
        }

        BLOCK_REGISTRY.register(modEventBus);
        BLOCK_ITEM_REGISTRY.register(modEventBus);
        ITEM_REGISTRY.register(modEventBus);
        TIERED_ITEM_REGISTRY.register(modEventBus);
        ENTITY_TYPE_REGISTRY.register(modEventBus);
        ILikeWoodTileEntityTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodContainerRegistry.REGISTRY.register(modEventBus);
        RECIPE_TYPE_REGISTER.register(modEventBus);
        ILikeWoodParticleTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodRecipeSerializerRegistry.REGISTRY.register(modEventBus);
        ILikeWoodPaintingRegistry.REGISTRY.register(modEventBus);

        for (final IModPlugin plugin : PLUGINS)
        {
            plugin.acceptBlockRegistry(BLOCK_REGISTRY);
            plugin.acceptBlockItemRegistry(BLOCK_ITEM_REGISTRY);
            plugin.acceptItemRegistry(ITEM_REGISTRY);
            plugin.acceptTieredItemRegistry(TIERED_ITEM_REGISTRY);
            plugin.acceptEntityTypeRegistry(ENTITY_TYPE_REGISTRY);
        }
    }

    public static Block getBlock(
        final IWoodType woodType,
        final WoodenBlockType blockType
    )
    {
        if (woodType.getBlockTypes().contains(blockType))
        {
            return BLOCK_REGISTRY.getObject(woodType, blockType);
        }
        if (woodType.getBuiltinBlockTypes().contains(blockType))
        {
            return ForgeRegistries.BLOCKS.getValue(WOODEN_RESOURCE_REGISTRY.getBlockResource(woodType, blockType).getResource());
        }
        throw new IllegalArgumentException("");
    }

    public static Stream<Block> getBlocks(final WoodenBlockType blockType)
    {
        return WOOD_TYPE_REGISTRY.getWoodTypes().map(woodType -> getBlock(woodType, blockType));
    }

    public static Stream<Block> getBlocks(Stream<WoodenBlockType> blockTypes)
    {
        return blockTypes.flatMap(ILikeWood::getBlocks);
    }

    public static Item getItem(
        final IWoodType woodType,
        final WoodenItemType itemType
    )
    {
        if (woodType.getItemTypes().contains(itemType))
        {
            return ITEM_REGISTRY.getObject(woodType, itemType);
        }
        if (woodType.getBuiltinItemTypes().contains(itemType))
        {
            return ForgeRegistries.ITEMS.getValue(WOODEN_RESOURCE_REGISTRY.getItemResource(woodType, itemType).getResource());
        }
        throw new IllegalArgumentException("");
    }

    public static Stream<Item> getItems(final WoodenItemType itemType)
    {
        return WOOD_TYPE_REGISTRY.getWoodTypes().map(woodType -> getItem(woodType, itemType));
    }

    public static Stream<Item> getItems(Stream<WoodenItemType> itemTypes)
    {
        return itemTypes.flatMap(ILikeWood::getItems);
    }

    private static void getPlugins()
    {
        final List<String> names = ModList
            .get()
            .getAllScanData()
            .stream()
            .flatMap(scanData -> scanData.getAnnotations().stream())
            .filter(annotationData -> Objects.equals(annotationData.annotationType(), Type.getType(ILikeWoodPlugin.class)))
            .map(ModFileScanData.AnnotationData::memberName)
            .toList();

        for (final String name : names)
        {
            try
            {
                PLUGINS.add(Class.forName(name).asSubclass(IModPlugin.class).newInstance());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e)
            {
                LOGGER.error("Failed to load: {}", name, e);
            }
        }
    }

    private static boolean validate(final StringBuilder builder)
    {

        final var blockTypeValidation = WoodenBlockType.getAll().map(objectType -> ObjectTypeValidator.validate(builder, objectType)).reduce(true, Boolean::logicalAnd);

        if (!blockTypeValidation)
        {
            builder.append("Block type validation failed!");
            builder.append(System.lineSeparator());
        }

        final var itemTypeValidation = WoodenItemType.getAll().map(objectType -> ObjectTypeValidator.validate(builder, objectType)).reduce(true, Boolean::logicalAnd);

        if (!itemTypeValidation)
        {
            builder.append("Item type validation failed!");
            builder.append(System.lineSeparator());
        }

        final var tieredItemTypeValidation =
            WoodenTieredItemType.getAll().map(objectType -> ObjectTypeValidator.validate(builder, objectType)).reduce(true, Boolean::logicalAnd);

        if (!tieredItemTypeValidation)
        {
            builder.append("Tiered item type validation failed!");
            builder.append(System.lineSeparator());
        }

        final var entityTypeValidation = WoodenEntityType.getAll().map(objectType -> ObjectTypeValidator.validate(builder, objectType)).reduce(true, Boolean::logicalAnd);

        if (!entityTypeValidation)
        {
            builder.append("Entity type validation failed!");
            builder.append(System.lineSeparator());
        }

        final var woodTypeValidation = WOOD_TYPE_REGISTRY.getWoodTypes().map(woodType -> WoodTypeValidator.validate(builder, woodType)).reduce(true, Boolean::logicalAnd);

        if (!woodTypeValidation)
        {
            builder.append("Wood type validation failed!");
            builder.append(System.lineSeparator());
        }

        return blockTypeValidation && itemTypeValidation && tieredItemTypeValidation && entityTypeValidation && woodTypeValidation;
    }
}
