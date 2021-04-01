package yamahari.ilikewood.registry.woodtype;

import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

import java.util.Set;

public interface IWoodType {
    String getModId();

    String getName();

    Properties getProperties(final WoodenObjectType woodenObjectType);

    default float getEnchantingPowerBonus() {
        return 1.F;
    }

    Set<WoodenObjectType> getObjectTypes();

    Set<WoodenTieredObjectType> getTieredObjectTypes();

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
