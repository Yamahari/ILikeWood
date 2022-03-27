package yamahari.ilikewood.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class SpecialModels {
    public static Map<IWoodType, ResourceLocation> ITEM_FRAME_MODELS;
    public static Map<IWoodType, ResourceLocation> ITEM_FRAME_MAP_MODELS;

    private SpecialModels() {
    }

    @SubscribeEvent
    public static void onModelRegistry(final ModelRegistryEvent event) {
        final Map<IWoodType, ResourceLocation> itemFrameModels = new HashMap<>();
        final Map<IWoodType, ResourceLocation> itemFrameMapModels = new HashMap<>();

        if (ILikeWoodConfig.ITEM_FRAMES_CONFIG.isEnabled()) {
            ILikeWood.WOOD_TYPE_REGISTRY
                .getWoodTypes()
                .filter(woodType -> woodType.getItemTypes().contains(WoodenItemType.ITEM_FRAME))
                .forEach(woodType -> {
                    final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenItemType.ITEM_FRAME.getName());
                    itemFrameModels.put(
                        woodType,
                        new ResourceLocation(Constants.MOD_ID, Util.toPath(path, woodType.getName()))
                    );
                    itemFrameMapModels.put(
                        woodType,
                        new ResourceLocation(Constants.MOD_ID, Util.toPath(path, "map", woodType.getName()))
                    );
                });
        }

        ITEM_FRAME_MODELS = Collections.unmodifiableMap(itemFrameModels);
        ITEM_FRAME_MAP_MODELS = Collections.unmodifiableMap(itemFrameMapModels);

        Stream
            .of(ITEM_FRAME_MAP_MODELS.entrySet(), ITEM_FRAME_MODELS.entrySet())
            .flatMap(Collection::stream)
            .map(Map.Entry::getValue)
            .forEach(ForgeModelBakery::addSpecialModel);
    }
}
