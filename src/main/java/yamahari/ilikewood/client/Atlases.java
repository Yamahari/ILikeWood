package yamahari.ilikewood.client;

import net.minecraft.client.renderer.model.Material;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class Atlases {
    private static final Map<WoodType, Map<ChestType, Material>> CHESTS;

    static {
        final Map<WoodType, Map<ChestType, Material>> chests = new EnumMap<>(WoodType.class);
        for (final WoodType woodType : WoodType.values()) {
            chests.put(woodType, makeChestMaterials(woodType));
        }
        CHESTS = Collections.unmodifiableMap(chests);
    }

    private Atlases() {
    }

    private static Map<ChestType, Material> makeChestMaterials(final WoodType woodType) {
        final EnumMap<ChestType, Material> materials = new EnumMap<>(ChestType.class);
        for (final ChestType chestType : ChestType.values()) {
            materials.put(chestType, new Material(net.minecraft.client.renderer.Atlases.CHEST_ATLAS,
                    new ResourceLocation(Constants.MOD_ID, Util.toPath("entity", "chest", chestType.getName(), woodType.toString()))));
        }
        return materials;
    }

    public static Map<ChestType, Material> getChestMaterials(final WoodType woodType) {
        return CHESTS.get(woodType);
    }

    @SubscribeEvent
    public static void onTextureStitchPre(final TextureStitchEvent.Pre event) {
        final ResourceLocation atlas = event.getMap().getTextureLocation();
        CHESTS.values().stream()
                .flatMap(materials -> materials.values().stream())
                .filter(material -> material.getAtlasLocation().equals(atlas))
                .forEach(material -> event.addSprite(material.getTextureLocation()));

    }
}
