package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.ILikeWoodPlugin;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.IWoodTypeRegistry;
import yamahari.ilikewood.IWoodenItemTierRegistry;
import yamahari.ilikewood.util.Constants;

@ILikeWoodPlugin
public class VanillaPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(Constants.MOD_ID, "vanilla_plugin");

    @Override
    public ResourceLocation getUId() {
        return UID;
    }

    @Override
    public void registerWoodTypes(final IWoodTypeRegistry registry) {
        registry.register(VanillaWoodTypes.ACACIA);
        registry.register(VanillaWoodTypes.BIRCH);
        registry.register(VanillaWoodTypes.CRIMSON);
        registry.register(VanillaWoodTypes.DARK_OAK);
        registry.register(VanillaWoodTypes.JUNGLE);
        registry.register(VanillaWoodTypes.OAK);
        registry.register(VanillaWoodTypes.SPRUCE);
        registry.register(VanillaWoodTypes.WARPED);
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
}
