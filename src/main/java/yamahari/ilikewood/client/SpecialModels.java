package yamahari.ilikewood.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SpecialModels {
    public static final Map<WoodType, ResourceLocation> ITEM_FRAME_MODELS;
    public static final Map<WoodType, ResourceLocation> ITEM_FRAME_MAP_MODELS;

    static {
        final Map<WoodType, ResourceLocation> itemFrameModels = new EnumMap<>(WoodType.class);
        final Map<WoodType, ResourceLocation> itemFrameMapModels = new EnumMap<>(WoodType.class);

        // TODO Change to values()?
        WoodType.getLoadedValues().forEach(woodType -> {
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.ITEM_FRAME.toString());
            itemFrameModels.put(woodType, new ResourceLocation(Constants.MOD_ID, Util.toPath(path, woodType.getPrefixedName())));
            itemFrameMapModels.put(woodType, new ResourceLocation(Constants.MOD_ID, Util.toPath(path, "map", woodType.getPrefixedName())));
        });

        ITEM_FRAME_MODELS = Collections.unmodifiableMap(itemFrameModels);
        ITEM_FRAME_MAP_MODELS = Collections.unmodifiableMap(itemFrameMapModels);
    }

    @SubscribeEvent
    public static void onModelRegistry(@SuppressWarnings("unused") final ModelRegistryEvent event) {
        Stream.of(ITEM_FRAME_MAP_MODELS.entrySet(), ITEM_FRAME_MODELS.entrySet())
                .flatMap(Collection::stream)
                .map(Map.Entry::getValue)
                .forEach(ModelLoader::addSpecialModel);
    }

    private SpecialModels() {
    }
}
