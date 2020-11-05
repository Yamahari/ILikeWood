package yamahari.ilikewood;

import yamahari.ilikewood.registry.resource.IWoodenResourceRegistry;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTierRegistry;
import yamahari.ilikewood.registry.woodtype.IWoodTypeRegistry;

public interface IModPlugin {
    String getModId();

    default void registerWoodTypes(final IWoodTypeRegistry registry) {
    }

    default void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
    }

    default void registerWoodenResources(final IWoodenResourceRegistry registry) {
    }
}
