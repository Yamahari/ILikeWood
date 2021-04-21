package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.objecttype.WoodenTieredItemType;

public interface IWoodenTieredItem {
    IWoodenItemTier getWoodenItemTier();

    WoodenTieredItemType getTieredItemType();
}
