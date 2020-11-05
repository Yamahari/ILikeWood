package yamahari.ilikewood.plugin.biomesoplenty;

import yamahari.ilikewood.ILikeWoodPlugin;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;
import yamahari.ilikewood.util.Constants;

@ILikeWoodPlugin
public final class BiomesOPlentyPlugin implements IModPlugin {
    @Override
    public String getModId() {
        return Constants.BOP_MOD_ID;
    }

    @Override
    public void registerWoodTypes(final IWoodTypeRegistry registry) {
        BiomesOPlentyWoodTypes.get().forEach(registry::register);
    }

    @Override
    public void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
        registry.register(BiomesOPlentyWoodenItemTiers.CHERRY);
        registry.register(BiomesOPlentyWoodenItemTiers.DEAD);
        registry.register(BiomesOPlentyWoodenItemTiers.FIR);
        registry.register(BiomesOPlentyWoodenItemTiers.HELLBARK);
        registry.register(BiomesOPlentyWoodenItemTiers.JACARANDA);
        registry.register(BiomesOPlentyWoodenItemTiers.MAGIC);
        registry.register(BiomesOPlentyWoodenItemTiers.MAHOGANY);
        registry.register(BiomesOPlentyWoodenItemTiers.PALM);
        registry.register(BiomesOPlentyWoodenItemTiers.REDWOOD);
        registry.register(BiomesOPlentyWoodenItemTiers.UMBRAN);
        registry.register(BiomesOPlentyWoodenItemTiers.WILLOW);
    }

    @Override
    public void registerWoodenResources(final IWoodenResourceRegistry registry) {
        BiomesOPlentyWoodTypes.get().forEach(woodType -> {
            registry.registerPlanksResource(woodType, BiomesOPlentyWoodenResources.PLANKS.get(woodType));
            registry.registerLogResource(woodType, BiomesOPlentyWoodenResources.LOGS.get(woodType));
            registry.registerStrippedLogResource(woodType, BiomesOPlentyWoodenResources.STRIPPED_LOGS.get(woodType));
            registry.registerSlabResource(woodType, BiomesOPlentyWoodenResources.SLABS.get(woodType));
        });
    }
}
