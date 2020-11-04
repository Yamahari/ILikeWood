package yamahari.ilikewood.plugin.biomesoplenty;

import yamahari.ilikewood.ILikeWoodPlugin;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.IWoodTypeRegistry;
import yamahari.ilikewood.IWoodenItemTierRegistry;
import yamahari.ilikewood.util.Constants;

@ILikeWoodPlugin
public final class BiomesOPlentyPlugin implements IModPlugin {
    @Override
    public String getModId() {
        return Constants.BOP_MOD_ID;
    }

    @Override
    public void registerWoodTypes(final IWoodTypeRegistry registry) {
        registry.register(BiomesOPlentyWoodTypes.CHERRY);
        registry.register(BiomesOPlentyWoodTypes.DEAD);
        registry.register(BiomesOPlentyWoodTypes.FIR);
        registry.register(BiomesOPlentyWoodTypes.HELLBARK);
        registry.register(BiomesOPlentyWoodTypes.JACARANDA);
        registry.register(BiomesOPlentyWoodTypes.MAGIC);
        registry.register(BiomesOPlentyWoodTypes.MAHOGANY);
        registry.register(BiomesOPlentyWoodTypes.PALM);
        registry.register(BiomesOPlentyWoodTypes.REDWOOD);
        registry.register(BiomesOPlentyWoodTypes.UMBRAN);
        registry.register(BiomesOPlentyWoodTypes.WILLOW);
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
}
