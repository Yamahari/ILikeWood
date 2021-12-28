package yamahari.ilikewood;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.registry.IWoodenObjectRegistry;
import yamahari.ilikewood.registry.IWoodenTieredItemRegistry;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;

/* TODO
    Add documentation for API
 */

public interface IModPlugin {
    /**
     * @return
     */
    String getModId();

    /**
     * @param registry
     */
    default void registerWoodTypes(final IWoodTypeRegistry registry) {
    }

    /**
     * @param registry
     */
    default void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
    }

    /**
     * @param registry
     */
    default void registerWoodenResources(final IWoodenResourceRegistry registry) {
    }

    /**
     * @param registry
     */
    default void acceptBlockRegistry(final IWoodenObjectRegistry<Block, WoodenBlockType> registry) {
    }

    /**
     * @param registry
     */
    default void acceptItemRegistry(final IWoodenObjectRegistry<Item, WoodenItemType> registry) {
    }

    /**
     * @param registry
     */
    default void acceptBlockItemRegistry(final IWoodenObjectRegistry<Item, WoodenBlockType> registry) {
    }

    /**
     * @param registry
     */
    default void acceptEntityTypeRegistry(final IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> registry) {
    }

    /**
     * @param registry
     */
    default void acceptTieredItemRegistry(final IWoodenTieredItemRegistry registry) {
    }
}
