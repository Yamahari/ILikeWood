package yamahari.ilikewood.item.tiered;

import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.WoodenTieredObjectType;

public interface IWoodenTieredItem {
    IWoodenItemTier getWoodenItemTier();

    WoodenTieredObjectType getWoodenTieredObjectType();
}
