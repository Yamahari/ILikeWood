package yamahari.ilikewood;

import net.minecraft.util.ResourceLocation;

public interface IModPlugin {
    ResourceLocation getUId();

    default void registerWoodTypes(final IWoodTypeRegistry registry) {
    }

    default void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
    }
}
