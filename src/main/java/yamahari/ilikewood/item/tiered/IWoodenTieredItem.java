package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

public interface IWoodenTieredItem {
    IWoodenItemTier getWoodenItemTier();

    WoodenTieredObjectType getWoodenTieredObjectType();
}
