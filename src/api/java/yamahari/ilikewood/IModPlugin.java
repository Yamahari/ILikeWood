package yamahari.ilikewood;

public interface IModPlugin {
    String getModId();

    default void registerWoodTypes(final IWoodTypeRegistry registry) {
    }

    default void registerWoodenItemTiers(final IWoodenItemTierRegistry registry) {
    }
}
