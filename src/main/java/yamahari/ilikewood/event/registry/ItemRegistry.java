package yamahari.ilikewood.event.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.item.WoodenBlockItem;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ItemRegistry {
    private static final Logger logger = LogManager.getLogger(ItemRegistry.class);

    private ItemRegistry() {
        // Nothing to do here!
    }

    @SubscribeEvent
    public static void onRegisterItem(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        Util.getBlocks(WoodenPanelsBlocks.class)
                .forEach(block -> registry.register(new WoodenBlockItem(WoodenObjectType.PANELS, block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS))));

        Util.getBlocks(WoodenPanelsStairsBlocks.class)
                .forEach(block -> registry.register(new WoodenBlockItem(WoodenObjectType.STAIRS, block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS))));

        Util.getBlocks(WoodenPanelsSlabBlocks.class)
                .forEach(block -> registry.register(new WoodenBlockItem(WoodenObjectType.SLAB, block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS))));

        Util.getBlocks(WoodenBarrelBlocks.class)
                .forEach(block -> registry.register(new WoodenBlockItem(WoodenObjectType.BARREL, block, (new Item.Properties()).group(ItemGroup.DECORATIONS))));

        Util.getBlocks(WoodenBookshelfBlocks.class)
                .forEach(block -> registry.register(new WoodenBlockItem(WoodenObjectType.BOOKSHELF, block, (new Item.Properties()).group(ItemGroup.DECORATIONS))));
    }
}
