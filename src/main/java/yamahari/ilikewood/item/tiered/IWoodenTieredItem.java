package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.IWoodenItemTier;
import yamahari.ilikewood.util.WoodenTieredObjectType;

public interface IWoodenTieredItem {
    IWoodenItemTier getWoodenItemTier();

    WoodenTieredObjectType getWoodenTieredObjectType();
}
