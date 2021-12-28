package yamahari.ilikewood.client;

import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class Atlases {
    private static final Map<IWoodType, Map<ChestType, Material>> CHESTS;

    static {
        final Map<IWoodType, Map<ChestType, Material>> chests = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getBlockTypes().contains(WoodenBlockType.CHEST))
            .forEach(woodType -> chests.put(woodType, makeChestMaterials(woodType)));
        CHESTS = Collections.unmodifiableMap(chests);
    }

    private Atlases() {
    }

    private static Map<ChestType, Material> makeChestMaterials(final IWoodType woodType) {
        final EnumMap<ChestType, Material> materials = new EnumMap<>(ChestType.class);
        for (final ChestType chestType : ChestType.values()) {
            materials.put(chestType,
                new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toPath("entity", "chest", chestType.getSerializedName(), woodType.getName()))));
        }
        return materials;
    }

    public static Map<ChestType, Material> getChestMaterials(final IWoodType woodType) {
        return CHESTS.get(woodType);
    }

    @SubscribeEvent
    public static void onTextureStitchPre(final TextureStitchEvent.Pre event) {
        final ResourceLocation atlas = event.getAtlas().location();
        CHESTS
            .values()
            .stream()
            .flatMap(materials -> materials.values().stream())
            .filter(material -> material.atlasLocation().equals(atlas))
            .forEach(material -> event.addSprite(material.texture()));

    }
}
