package yamahari.ilikewood.registry.woodtype;

import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

import java.util.Set;

public interface IWoodType {
    String getModId();

    String getName();

    Properties getProperties(final WoodenBlockType blockType);

    Properties getProperties(final WoodenItemType itemType);

    default float getEnchantingPowerBonus() {
        return 1.F;
    }

    Set<WoodenBlockType> getBlockTypes();

    Set<WoodenItemType> getItemTypes();

    Set<WoodenEntityType> getEntityTypes();

    Set<WoodenTieredItemType> getTieredItemTypes();

    Set<WoodenBlockType> getBuiltinBlockTypes();

    Set<WoodenItemType> getBuiltinItemTypes();

    Colors getColors();

    final class Colors {
        private final int[] colors;

        public Colors(final int c0, final int c1, final int c2, final int c3, final int c4, final int c5, final int c6,
                      final int c7) {
            this.colors = new int[]{c0, c1, c2, c3, c4, c5, c6, c7};
        }

        public int getColor(final int index) throws IllegalArgumentException {
            if (index < 0 || index > 7) {
                throw new IllegalArgumentException("Index out of range");
            }
            return this.colors[index];
        }
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
