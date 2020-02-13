package yamahari.ilikewood.client;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class Atlases {
    private static final Map<String, Map<ChestType, Material>> CHESTS;

    static {
        final ImmutableMap.Builder<String, Map<ChestType, Material>> builder = new ImmutableMap.Builder<>();
        Arrays.stream(WoodType.values()).map(Object::toString).forEach(woodType -> builder.put(woodType, ImmutableMap.copyOf(makeChestMaterials(woodType))));
        CHESTS = builder.build();
    }

    private Atlases() {
    }

    private static Map<ChestType, Material> makeChestMaterials(final String woodType) {
        final EnumMap<ChestType, Material> materials = new EnumMap<>(ChestType.class);
        for (final ChestType chestType : ChestType.values()) {
            materials.put(chestType, new Material(net.minecraft.client.renderer.Atlases.field_228747_f_,
                    new ResourceLocation(Constants.MOD_ID, Util.toPath("entity", "chest", chestType.getName(), woodType))));
        }
        return materials;
    }

    public static Map<ChestType, Material> getChestMaterials(final String type) {
        return CHESTS.get(type);
    }

    @SubscribeEvent
    public static void onTextureStitchPre(final TextureStitchEvent.Pre event) {
        final ResourceLocation atlas = event.getMap().func_229223_g_();
        CHESTS.values().stream()
                .flatMap(materials -> materials.values().stream())
                .filter(material -> material.func_229310_a_().equals(atlas))
                .forEach(material -> event.addSprite(material.func_229313_b_()));

    }
}
