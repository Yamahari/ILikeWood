package yamahari.ilikewood.client;

import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class ILikeWoodSheets {

    private static final Map<IWoodType, Map<ChestType, Material>> CHEST_MATERIALS;

    static {
        final Map<IWoodType, Map<ChestType, Material>> chestMaterials = new HashMap<>();

        if (ILikeWoodConfig.CHESTS_CONFIG.isEnabled()) {
            ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(woodType -> woodType.getBlockTypes().contains(WoodenBlockType.CHEST))
                .forEach(woodType -> chestMaterials.put(woodType, makeChestMaterials(woodType)));
        }

        CHEST_MATERIALS = Collections.unmodifiableMap(chestMaterials);
    }

    private ILikeWoodSheets() {
    }

    public static Map<ChestType, Material> getChestMaterials(final IWoodType woodType) {
        return CHEST_MATERIALS.get(woodType);
    }

    private static Map<ChestType, Material> makeChestMaterials(final IWoodType woodType) {
        final EnumMap<ChestType, Material> materials = new EnumMap<>(ChestType.class);
        for (final var chestType : ChestType.values()) {
            materials.put(chestType,
                new Material(net.minecraft.client.renderer.Sheets.CHEST_SHEET,
                    new ResourceLocation(Constants.MOD_ID, Util.toPath("entity", "chest", woodType.getModId(), chestType.getSerializedName(), woodType.getName()))));
        }
        return materials;
    }
}
