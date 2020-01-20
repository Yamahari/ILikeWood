package yamahari.ilikewood;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
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
import yamahari.ilikewood.util.Constants;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ILikeWood {
    public static final Logger LOGGER = LogManager.getLogger(ILikeWood.class);
    private static final IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public ILikeWood() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(proxy::onFMLCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(proxy::onFMLClientSetup);
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();

        if (event.includeServer()) {
            generator.addProvider(new RecipeProvider(generator));
            generator.addProvider(new LootTableProvider(generator));
        }

        if (event.includeClient()) {
            final ExistingFileHelper helper = event.getExistingFileHelper();

            generator.addProvider(new BlockStateProvider(generator, helper));
            generator.addProvider(new ItemModelProvider(generator, helper));
            generator.addProvider(new LanguageProvider(generator, "en_us"));
        }
    }
}
