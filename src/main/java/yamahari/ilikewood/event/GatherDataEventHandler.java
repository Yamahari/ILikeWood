package yamahari.ilikewood.event;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import yamahari.ilikewood.provider.*;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT, Dist.DEDICATED_SERVER}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GatherDataEventHandler {
    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeServer()) {
            generator.addProvider(new ILikeWoodRecipeProvider(generator));
            generator.addProvider(new ILikeWoodLootTableProvider(generator));
            final ILikeWoodBlockTagsProvider blockTagsProvider = new ILikeWoodBlockTagsProvider(generator, existingFileHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ILikeWoodItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        }

        if (event.includeClient()) {
            generator.addProvider(new ILikeWoodBlockStateProvider(generator, existingFileHelper));
            generator.addProvider(new ILikeWoodItemModelProvider(generator, existingFileHelper));
            generator.addProvider(new ILikeWoodLanguageProvider(generator, "en_us"));
        }
    }

    private GatherDataEventHandler() {
    }
}
