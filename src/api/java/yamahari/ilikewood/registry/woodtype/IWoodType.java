package yamahari.ilikewood.registry.woodtype;

import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

import java.util.Set;

public interface IWoodType {
    String getModId();

    String getName();

    //Properties getProperties(final WoodenObjectType woodenObjectType);

    Properties getProperties(final WoodenBlockType blockType);

    Properties getProperties(final WoodenItemType itemType);

    default float getEnchantingPowerBonus() {
        return 1.F;
    }

    Set<WoodenBlockType> getBlockTypes();

    Set<WoodenItemType> getItemTypes();

    Set<WoodenTieredItemType> getTieredItemTypes();

    Set<WoodenBlockType> getBuiltinBlockTypes();

    Set<WoodenItemType> getBuiltinItemTypes();

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
