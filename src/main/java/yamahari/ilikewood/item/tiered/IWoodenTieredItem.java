package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.util.WoodenItemTier;
import yamahari.ilikewood.util.WoodenTieredObjectType;

public interface IWoodenTieredItem {
    WoodenItemTier getWoodenItemTier();

    WoodenTieredObjectType getWoodenTieredObjectType();
}
