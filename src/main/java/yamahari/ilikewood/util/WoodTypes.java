package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import yamahari.ilikewood.config.Config;

import java.util.Map;
import java.util.stream.Stream;

public final class WoodTypes {
    public static final WoodType ACACIA = new WoodType(
            Constants.ACACIA,
            createWoodTypeProperties(Constants.ACACIA),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.ACACIA)::get);

    public static final WoodType BIRCH = new WoodType(
            Constants.BIRCH,
            createWoodTypeProperties(Constants.BIRCH),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.BIRCH)::get);

    public static final WoodType DARK_OAK = new WoodType(
            Constants.DARK_OAK,
            createWoodTypeProperties(Constants.DARK_OAK),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.DARK_OAK)::get);

    public static final WoodType JUNGLE = new WoodType(
            Constants.JUNGLE,
            createWoodTypeProperties(Constants.JUNGLE),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.JUNGLE)::get);

    public static final WoodType OAK = new WoodType(
            Constants.OAK,
            createWoodTypeProperties(Constants.OAK),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.OAK)::get);

    public static final WoodType SPRUCE = new WoodType(
            Constants.SPRUCE,
            createWoodTypeProperties(Constants.SPRUCE),
            Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(Constants.SPRUCE)::get);

    private WoodTypes() {
        // Nothing to do here!
    }

    private static Map<String, WoodType.Properties> createWoodTypeProperties(final String woodType) {
        return Config.SERVER_CONFIG.BURN_TIME.get(woodType).entrySet().stream()
                .collect(ImmutableMap.toImmutableMap(ImmutableMap.Entry::getKey, entry -> new WoodType.Properties(entry.getValue()::get)));
    }

    public static Stream<WoodType> get() {
        return Stream.of(ACACIA, BIRCH, DARK_OAK, JUNGLE, OAK, SPRUCE);
    }
}
