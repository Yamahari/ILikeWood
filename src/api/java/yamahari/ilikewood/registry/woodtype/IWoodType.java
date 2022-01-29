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
        return 1.0F;
    }

    Set<WoodenBlockType> getBlockTypes();

    Set<WoodenItemType> getItemTypes();

    Set<WoodenEntityType> getEntityTypes();

    Set<WoodenTieredItemType> getTieredItemTypes();

    Set<WoodenBlockType> getBuiltinBlockTypes();

    Set<WoodenItemType> getBuiltinItemTypes();

    Colors getColors();

    record Properties(int burnTime) {
    }

    record Colors(int[] colors) {
        public Colors {
            if (colors.length != 8) {
                throw new IllegalArgumentException("\"colors\" needs to contain 8 colors.");
            }
        }
    }
}