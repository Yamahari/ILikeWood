package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWoodPlugin;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.registry.IWoodenObjectRegistry;
import yamahari.ilikewood.registry.IWoodenTieredItemRegistry;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;
import yamahari.ilikewood.util.Constants;

@ILikeWoodPlugin
public class VanillaPlugin implements IModPlugin {
    public static IWoodenObjectRegistry<Block, WoodenBlockType> BLOCK_REGISTRY;
    public static IWoodenObjectRegistry<Item, WoodenItemType> ITEM_REGISTRY;
    public static IWoodenObjectRegistry<Item, WoodenBlockType> BLOCK_ITEM_REGISTRY;
    public static IWoodenTieredItemRegistry TIERED_ITEM_REGISTRY;
    public static IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> ENTITY_TYPES;

    @Override
    public String getModId() {
        return Constants.MOD_ID;
    }

    @Override
    public String getPluginModId() {
        return Constants.MOD_ID;
    }

    @Override
    public void registerWoodTypes(final IWoodTypeRegistry registry) {
        VanillaWoodTypes.get().forEach(registry::register);
    }

    @Override
    public void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
        registry.register(VanillaWoodenItemTiers.ACACIA);
        registry.register(VanillaWoodenItemTiers.BIRCH);
        registry.register(VanillaWoodenItemTiers.CRIMSON);
        registry.register(VanillaWoodenItemTiers.DARK_OAK);
        registry.register(VanillaWoodenItemTiers.JUNGLE);
        registry.register(VanillaWoodenItemTiers.OAK);
        registry.register(VanillaWoodenItemTiers.SPRUCE);
        registry.register(VanillaWoodenItemTiers.WARPED);
        registry.register(VanillaWoodenItemTiers.STONE);
        registry.register(VanillaWoodenItemTiers.IRON);
        registry.register(VanillaWoodenItemTiers.GOLDEN);
        registry.register(VanillaWoodenItemTiers.DIAMOND);
        registry.register(VanillaWoodenItemTiers.NETHERITE);
    }

    @Override
    public void registerWoodenResources(final IWoodenResourceRegistry registry) {
        VanillaWoodTypes.get().forEach(woodType -> {
            registry.registerPlanksResource(woodType, VanillaWoodenResources.PLANKS.get(woodType));
            registry.registerLogResource(woodType, VanillaWoodenResources.LOGS.get(woodType));
            registry.registerStrippedLogResource(woodType, VanillaWoodenResources.STRIPPED_LOGS.get(woodType));
            registry.registerSlabResource(woodType, VanillaWoodenResources.SLABS.get(woodType));
        });
    }

    @Override
    public void acceptBlockRegistry(final IWoodenObjectRegistry<Block, WoodenBlockType> registry) {
        BLOCK_REGISTRY = registry;
    }

    @Override
    public void acceptItemRegistry(final IWoodenObjectRegistry<Item, WoodenItemType> registry) {
        ITEM_REGISTRY = registry;
    }

    @Override
    public void acceptBlockItemRegistry(final IWoodenObjectRegistry<Item, WoodenBlockType> registry) {
        BLOCK_ITEM_REGISTRY = registry;
    }

    @Override
    public void acceptEntityTypeRegistry(final IWoodenObjectRegistry<EntityType<?>, WoodenEntityType> registry) {
        ENTITY_TYPES = registry;
    }

    @Override
    public void acceptTieredItemRegistry(final IWoodenTieredItemRegistry registry) {
        TIERED_ITEM_REGISTRY = registry;
    }
}
