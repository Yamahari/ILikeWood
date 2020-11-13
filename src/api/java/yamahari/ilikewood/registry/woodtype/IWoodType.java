package yamahari.ilikewood.registry.woodtype;

import yamahari.ilikewood.util.WoodenObjectType;

public interface IWoodType {
    String getModId();

    String getName();

    Properties getProperties(final WoodenObjectType woodenObjectType);

    default float getEnchantingPowerBonus() {
        return 1.F;
    }

    final class Properties {
        private final int burnTime;

        public Properties(final int burnTime) {
            this.burnTime = burnTime;
        }

        public int getBurnTime() {
            return this.burnTime;
        }
    }
}
