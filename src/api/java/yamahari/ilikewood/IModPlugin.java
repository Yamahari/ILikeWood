package yamahari.ilikewood;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import yamahari.ilikewood.registry.IWoodenObjectRegistry;
import yamahari.ilikewood.registry.IWoodenTieredItemRegistry;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.objecttype.WoodenEntityType;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public interface IModPlugin {
    String getModId();

    default void registerWoodTypes(final IWoodTypeRegistry registry) {
    }

    default void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
    }

    default void registerWoodenResources(final IWoodenResourceRegistry registry) {
    }

    default void acceptBlockRegistry(final IWoodenObjectRegistry<Block, WoodenBlockType> registry) {
    }

    default void acceptItemRegistry(final IWoodenObjectRegistry<Item, WoodenItemType> registry) {
    }

    default void acceptBlockItemRegistry(final IWoodenObjectRegistry<Item, WoodenBlockType> registry) {
    }

    default void acceptEntityTypeRegistry(final IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> registry) {
    }

    default void acceptTieredItemRegistry(final IWoodenTieredItemRegistry registry) {
    }
}
