package yamahari.ilikewood.util;

import net.minecraft.util.LazyValue;
import yamahari.ilikewood.config.Config;

import java.util.Collections;
import java.util.EnumMap;
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
    private final Map<WoodenObjectType, Properties> properties;
    private final LazyValue<Supplier<Double>> enchantingPowerBonus;

    WoodType(final String name) {
        this.name = name;
        final Map<WoodenObjectType, Properties> properties = new EnumMap<>(WoodenObjectType.class);
        for (final WoodenObjectType objectType : WoodenObjectType.values()) {
            properties.put(objectType, new Properties(() -> Config.SERVER_CONFIG.BURN_TIME.get(name).get(objectType.toString())::get));
        }
        this.properties = Collections.unmodifiableMap(properties);
        this.enchantingPowerBonus = new LazyValue<>(() -> Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(name)::get);
    }

    public Properties getProperties(final WoodenObjectType objectType) {
        return this.properties.get(objectType);
    }

    public float getEnchantingPowerBonus() {
        return this.enchantingPowerBonus.getValue().get().floatValue();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static final class Properties {
        private final LazyValue<Supplier<Integer>> burnTime;

        public Properties(final Supplier<Supplier<Integer>> burnTime) {
            this.burnTime = new LazyValue<>(burnTime);
        }

        public int getBurnTime() {
            return this.burnTime.getValue().get();
        }
    }
}
