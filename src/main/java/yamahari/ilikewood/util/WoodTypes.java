package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import yamahari.ilikewood.config.Config;

import java.util.Map;
import java.util.stream.Stream;

public final class WoodTypes {
    public static final WoodType ACACIA = createWoodType(Constants.ACACIA);
    public static final WoodType BIRCH = createWoodType(Constants.BIRCH);
    public static final WoodType DARK_OAK = createWoodType(Constants.DARK_OAK);
    public static final WoodType JUNGLE = createWoodType(Constants.JUNGLE);
    public static final WoodType OAK = createWoodType(Constants.OAK);
    public static final WoodType SPRUCE = createWoodType(Constants.SPRUCE);

    private WoodTypes() {
        // Nothing to do here!
    }

    private static WoodType createWoodType(final String woodType) {
        return new WoodType(woodType, createWoodTypeProperties(woodType), Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(woodType)::get);
    }

    private static Map<String, WoodType.Properties> createWoodTypeProperties(final String woodType) {
        return Config.SERVER_CONFIG.BURN_TIME.get(woodType).entrySet().stream()
                .collect(ImmutableMap.toImmutableMap(ImmutableMap.Entry::getKey, entry -> new WoodType.Properties(entry.getValue()::get)));
    }

    public static Stream<WoodType> get() {
        return Stream.of(ACACIA, BIRCH, DARK_OAK, JUNGLE, OAK, SPRUCE);
    }
}
