package yamahari.ilikewood;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import yamahari.ilikewood.provider.*;
import yamahari.ilikewood.proxy.ClientProxy;
import yamahari.ilikewood.proxy.CommonProxy;
import yamahari.ilikewood.proxy.IProxy;
import yamahari.ilikewood.registry.*;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenSlabResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;
import yamahari.ilikewood.util.Constants;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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

        final List<String> names = ModList.get().getAllScanData().stream()
                .flatMap(scanData -> scanData.getAnnotations().stream())
                .filter(annotationData -> Objects.equals(annotationData.getAnnotationType(), Type.getType(ILikeWoodPlugin.class)))
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
        // TODO replace all Util.HAS_PLANKS with Util.HAS_PLANKS.and(Util.HAS_SLAB)
        ILikeWoodBlockRegistry.REGISTRY.register(modEventBus);
        ILikeWoodItemRegistry.REGISTRY.register(modEventBus);
        ILikeWoodTileEntityTypeRegistry.REGISTRY.register(modEventBus);
        ILikeWoodContainerRegistry.REGISTRY.register(modEventBus);
        ILikeWoodEntityTypeRegistry.REGISTRY.register(modEventBus);
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new ILikeWoodRecipeProvider(generator));
            generator.addProvider(new ILikeWoodLootTableProvider(generator));
            final ILikeWoodBlockTagsProvider blockTagsProvider = new ILikeWoodBlockTagsProvider(generator);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ILikeWoodItemTagsProvider(generator, blockTagsProvider));
        }

        if (event.includeClient()) {
            final ExistingFileHelper helper = event.getExistingFileHelper();

            generator.addProvider(new ILikeWoodBlockStateProvider(generator, helper));
            generator.addProvider(new ILikeWoodItemModelProvider(generator, helper));
            generator.addProvider(new ILikeWoodLanguageProvider(generator, "en_us"));
        }
    }

    public static final class WoodTypeRegistry implements IWoodTypeRegistry {
        private final List<IWoodType> woodTypes = new ArrayList<>();

        @Override
        public void register(final IWoodType woodType) {
            this.woodTypes.add(woodType);
        }

        public Stream<IWoodType> getWoodTypes() {
            try {
                final String dataModId = System.getProperty("ilikewood.datagen.modid");
                if (dataModId != null) {
                    return this.woodTypes.stream().filter(woodType -> woodType.getModId().equals(dataModId));
                }
            } catch (NullPointerException | SecurityException | IllegalArgumentException e) {
                ILikeWood.LOGGER.error(e.getMessage());
            }
            return this.woodTypes.stream().filter(woodType -> ModList.get().isLoaded(woodType.getModId()));
        }
    }

    public static final class WoodenItemTierRegistry implements IWoodenItemTierRegistry {
        private final List<IWoodenItemTier> woodenItemTiers = new ArrayList<>();

        @Override
        public void register(final IWoodenItemTier itemTier) {
            this.woodenItemTiers.add(itemTier);
        }

        public Stream<IWoodenItemTier> getWoodenItemTiers() {
            try {
                final String dataModId = System.getProperty("ilikewood.datagen.modid");
                if (dataModId != null) {
                    return this.woodenItemTiers.stream().filter(itemTier -> !itemTier.isWood() || itemTier.getModId().equals(dataModId));
                }
            } catch (NullPointerException | SecurityException | IllegalArgumentException e) {
                ILikeWood.LOGGER.error(e.getMessage());
            }
            return this.woodenItemTiers.stream().filter(itemTier -> ModList.get().isLoaded(itemTier.getModId()));
        }
    }

    public static final class WoodenResourceRegistry implements IWoodenResourceRegistry {
        private final Map<IWoodType, IWoodenPlanksResource> planks = new HashMap<>();
        private final Map<IWoodType, IWoodenLogResource> logs = new HashMap<>();
        private final Map<IWoodType, IWoodenStrippedLogResource> strippedLogs = new HashMap<>();
        private final Map<IWoodType, IWoodenSlabResource> slabs = new HashMap<>();

        @Override
        public void registerPlanksResource(final IWoodType woodType, final IWoodenPlanksResource planks) {
            this.planks.put(woodType, planks);
        }

        @Override
        public void registerLogResource(final IWoodType woodType, final IWoodenLogResource log) {
            this.logs.put(woodType, log);
        }

        @Override
        public void registerStrippedLogResource(final IWoodType woodType, final IWoodenStrippedLogResource strippedLog) {
            this.strippedLogs.put(woodType, strippedLog);
        }

        @Override
        public void registerSlabResource(final IWoodType woodType, final IWoodenSlabResource slab) {
            this.slabs.put(woodType, slab);
        }

        public boolean hasPlanks(final IWoodType woodType) {
            return this.planks.containsKey(woodType);
        }

        public boolean hasLog(final IWoodType woodType) {
            return this.logs.containsKey(woodType);
        }

        public boolean hasStrippedLog(final IWoodType woodType) {
            return this.strippedLogs.containsKey(woodType);
        }

        public boolean hasSlab(final IWoodType woodType) {
            return this.slabs.containsKey(woodType);
        }

        public IWoodenPlanksResource getPlanks(final IWoodType woodType) {
            return this.planks.get(woodType);
        }

        public IWoodenLogResource getLog(final IWoodType woodType) {
            return this.logs.get(woodType);
        }

        public IWoodenStrippedLogResource getStrippedLog(final IWoodType woodType) {
            return this.strippedLogs.get(woodType);
        }

        public IWoodenSlabResource getSlab(final IWoodType woodType) {
            return this.slabs.get(woodType);
        }
    }
}
