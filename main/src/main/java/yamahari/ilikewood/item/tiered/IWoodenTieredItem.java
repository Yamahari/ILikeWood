package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;

public interface IWoodenTieredItem {
    IWoodenItemTier getWoodenItemTier();

    WoodenTieredItemType getTieredItemType();
}
