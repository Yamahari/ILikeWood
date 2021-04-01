package yamahari.ilikewood;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import yamahari.ilikewood.proxy.ClientProxy;
import yamahari.ilikewood.proxy.CommonProxy;
import yamahari.ilikewood.proxy.IProxy;
import yamahari.ilikewood.registry.*;
import yamahari.ilikewood.registry.resource.WoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.WoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.WoodTypeRegistry;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mod(Constants.MOD_ID)
public final class ILikeWood {
    public static final Logger LOGGER = LogManager.getLogger(ILikeWood.class);
    private static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final WoodTypeRegistry WOOD_TYPE_REGISTRY = new WoodTypeRegistry();

    public static final WoodenItemTierRegistry WOODEN_ITEM_TIER_REGISTRY = new WoodenItemTierRegistry();

    // TODO only need this during data gen not normal run
    public static final WoodenResourceRegistry WOODEN_RESOURCE_REGISTRY = new WoodenResourceRegistry();

    public ILikeWood() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(PROXY::onFMLCommonSetup);
        modEventBus.addListener(PROXY::onFMLClientSetup);

        getPlugins();
        checkWoodTypes();

        ILikeWoodBlockRegistry.REGISTRY.register(modEventBus);
        ILikeWoodItemRegistry.REGISTRY.register(modEventBus);
        ILikeWoodTileEntityTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodContainerRegistry.REGISTRY.register(modEventBus);
        ILikeWoodEntityTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodRecipeSerializerRegistry.REGISTRY.register(modEventBus);
    }

    private static void getPlugins() {
        final List<String> names = ModList
            .get()
            .getAllScanData()
            .stream()
            .flatMap(scanData -> scanData.getAnnotations().stream())
            .filter(annotationData -> Objects.equals(annotationData.getAnnotationType(),
                Type.getType(ILikeWoodPlugin.class)))
            .map(ModFileScanData.AnnotationData::getMemberName)
            .collect(Collectors.toList());

        final List<IModPlugin> plugins = new ArrayList<>();

        for (final String name : names) {
            try {
                plugins.add(Class.forName(name).asSubclass(IModPlugin.class).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) {
                LOGGER.error("Failed to load: {}", name, e);
            }
        }

        for (final IModPlugin plugin : plugins) {
            if (ModList.get().isLoaded(plugin.getModId())) {
                plugin.registerWoodTypes(WOOD_TYPE_REGISTRY);
                plugin.registerWoodenItemTiers(WOODEN_ITEM_TIER_REGISTRY);
                plugin.registerWoodenResources(WOODEN_RESOURCE_REGISTRY);
            }
        }
    }

    private static void checkWoodTypes() {
        WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final Set<WoodenObjectType> objectTypes = woodType.getObjectTypes();
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
            }

            final Set<WoodenTieredObjectType> tieredObjectTypes = woodType.getTieredObjectTypes();
            for (final WoodenTieredObjectType tieredObjectType : tieredObjectTypes) {
                if (!WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenTieredObjectType[%s] that requires planks resource but no planks resource was registered",
                        woodType.getName(),
                        tieredObjectType.getName()));
                }
                if (!WOODEN_RESOURCE_REGISTRY.hasSlab(woodType)) {
                    throw new RuntimeException(String.format(
                        "WoodType[%s] has WoodenTieredObjectType[%s] that requires slab resource but no slab resource was registered",
                        woodType.getName(),
                        tieredObjectType.getName()));
                }
            }
        });
    }
}
