package yamahari.ilikewood.util;

import java.util.Map;
import java.util.function.Supplier;

public final class WoodType {
    private final String name;
    private final Map<String, Properties> properties;
    private final Supplier<Double> enchantingPowerBonus;

    public WoodType(final String name, final Map<String, Properties> properties, Supplier<Double> enchantingPowerBonus) {
        this.name = name;
        this.properties = properties;
        this.enchantingPowerBonus = enchantingPowerBonus;
    }

    public Properties getProperties(WoodenObjectType objectType) {
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
