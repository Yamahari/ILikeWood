package yamahari.ilikewood.plugin.vanilla;

import yamahari.ilikewood.ILikeWoodPlugin;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;
import yamahari.ilikewood.util.Constants;

@ILikeWoodPlugin
public class VanillaPlugin implements IModPlugin {
    @Override
    public String getModId() {
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
}
