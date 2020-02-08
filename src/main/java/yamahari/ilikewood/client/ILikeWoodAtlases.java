package yamahari.ilikewood.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodTypes;

import java.util.Map;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ILikeWoodAtlases {
    private static final Map<String, Material> LEFT_CHESTS = makeChestMaterials(ChestType.LEFT);
    private static final Map<String, Material> RIGHT_CHESTS = makeChestMaterials(ChestType.RIGHT);
    private static final Map<String, Material> SINGLE_CHESTS = makeChestMaterials(ChestType.SINGLE);

    private ILikeWoodAtlases() {
    }

    private static Map<String, Material> makeChestMaterials(final ChestType chestType) {
        final ImmutableMap.Builder<String, Material> builder = new ImmutableMap.Builder<>();
        WoodTypes.get().map(WoodType::toString)
                .forEach(s -> builder.put(s,
                        new Material(Atlases.field_228747_f_,
                                new ResourceLocation(Constants.MOD_ID, Util.toPath("entity", "chest", chestType.getName(), s)))));
        return builder.build();
    }

    public static Material getChestMaterial(final String type, final ChestType chestType) {
        switch (chestType) {
            default:
            case SINGLE:
                return SINGLE_CHESTS.get(type);
            case LEFT:
                return LEFT_CHESTS.get(type);
            case RIGHT:
                return RIGHT_CHESTS.get(type);
        }
    }

    @SubscribeEvent
    public static void onTextureStitchPre(final TextureStitchEvent.Pre event) {
        final ResourceLocation atlas = event.getMap().func_229223_g_();
        Stream.of(SINGLE_CHESTS, LEFT_CHESTS, RIGHT_CHESTS)
                .flatMap(map -> map.values().stream())
                .filter(material -> material.func_229310_a_().equals(atlas))
                .forEach(material -> event.addSprite(material.func_229313_b_()));
    }
}
