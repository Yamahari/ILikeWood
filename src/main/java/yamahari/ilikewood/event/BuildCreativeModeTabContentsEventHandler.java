package yamahari.ilikewood.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.ILikeWoodCreativeModeTabs;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT, Dist.DEDICATED_SERVER}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BuildCreativeModeTabContentsEventHandler {
    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ILikeWoodCreativeModeTabs.CREATIVE_MODE_TAB.getKey()) {
            ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getAll()).forEach(event::accept);
            ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.getAll()).forEach(event::accept);
            WoodenTieredItemType.getAll().forEach(woodenTieredItemType -> ILikeWood.TIERED_ITEM_REGISTRY.getObjects(woodenTieredItemType).forEach(event::accept));
        }
    }
}
