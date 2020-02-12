package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import yamahari.ilikewood.config.Config;

import java.util.Map;
import java.util.function.Supplier;

public enum WoodType {
    ACACIA(Constants.ACACIA),
    BIRCH(Constants.BIRCH),
    DARK_OAK(Constants.DARK_OAK),
    JUNGLE(Constants.JUNGLE),
    OAK(Constants.OAK),
    SPRUCE(Constants.SPRUCE);

    private final String name;
    private final Map<String, Properties> properties;
    private final Supplier<Double> enchantingPowerBonus;

    WoodType(final String name) {
        this.name = name;
        this.properties = Config.SERVER_CONFIG.BURN_TIME.get(name).entrySet().stream()
                .collect(ImmutableMap.toImmutableMap(ImmutableMap.Entry::getKey, entry -> new Properties(entry.getValue()::get)));
        this.enchantingPowerBonus = Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(name)::get;
    }

    public Properties getProperties(final WoodenObjectType objectType) {
        assert this.properties.containsKey(objectType.toString());
        return this.properties.get(objectType.toString());
    }

    public float getEnchantingPowerBonus() {
        return this.enchantingPowerBonus.get().floatValue();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static final class Properties {
        private final Supplier<Integer> burnTime;

        public Properties(final Supplier<Integer> burnTime) {
            this.burnTime = burnTime;
        }

        public int getBurnTime() {
            return this.burnTime.get();
        }
    }
}
