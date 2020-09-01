package yamahari.ilikewood;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.config.Config;
import yamahari.ilikewood.provider.*;
import yamahari.ilikewood.proxy.ClientProxy;
import yamahari.ilikewood.proxy.CommonProxy;
import yamahari.ilikewood.proxy.IProxy;
import yamahari.ilikewood.registry.*;
import yamahari.ilikewood.util.Constants;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ILikeWood {
    public static final Logger LOGGER = LogManager.getLogger(ILikeWood.class);
    private static final IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public ILikeWood() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(proxy::onFMLCommonSetup);
        modEventBus.addListener(proxy::onFMLClientSetup);

        WoodenBlocks.REGISTRY.register(modEventBus);
        WoodenItems.REGISTRY.register(modEventBus);
        WoodenTileEntityTypes.REGISTRY.register(modEventBus);
        WoodenContainerTypes.REGISTRY.register(modEventBus);
        WoodenEntityTypes.REGISTRY.register(modEventBus);
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
}
