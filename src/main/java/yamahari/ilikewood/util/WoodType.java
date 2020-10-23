package yamahari.ilikewood.util;

import net.minecraft.util.LazyValue;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum WoodType {
    ACACIA(Constants.ACACIA),
    BIRCH(Constants.BIRCH),
    CRIMSON(Constants.CRIMSON),
    DARK_OAK(Constants.DARK_OAK),
    JUNGLE(Constants.JUNGLE),
    OAK(Constants.OAK),
    SPRUCE(Constants.SPRUCE),
    WARPED(Constants.WARPED),
    CHERRY(Constants.CHERRY, Constants.BOP_MOD_ID),
    DEAD(Constants.DEAD, Constants.BOP_MOD_ID),
    FIR(Constants.FIR, Constants.BOP_MOD_ID),
    HELLBARK(Constants.HELLBARK, Constants.BOP_MOD_ID),
    JACARANDA(Constants.JACARANDA, Constants.BOP_MOD_ID),
    MAGIC(Constants.MAGIC, Constants.BOP_MOD_ID),
    MAHOGANY(Constants.MAHOGANY, Constants.BOP_MOD_ID),
    PALM(Constants.PALM, Constants.BOP_MOD_ID),
    REDWOOD(Constants.REDWOOD, Constants.BOP_MOD_ID),
    UMBRAN(Constants.UMBRAN, Constants.BOP_MOD_ID),
    WILLOW(Constants.WILLOW, Constants.BOP_MOD_ID),
    TRM_DOUGLAS(Constants.TRM_DOUGLAS, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_PINE(Constants.TRM_PINE, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_LARCH(Constants.TRM_LARCH, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_FIR(Constants.TRM_FIR, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_MAPLE(Constants.TRM_MAPLE, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_JAPANESE(Constants.TRM_JAPANESE, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_BEECH(Constants.TRM_BEECH, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_CHERRY(Constants.TRM_CHERRY, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_ALDER(Constants.TRM_ALDER, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_CHESTNUT(Constants.TRM_CHESTNUT, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_PLANE(Constants.TRM_PLANE, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_ASH(Constants.TRM_ASH, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_LINDEN(Constants.TRM_LINDEN, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_ROBINIA(Constants.TRM_ROBINIA, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_WILLOW(Constants.TRM_WILLOW, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_POMEGRANATE(Constants.TRM_POMEGRANATE, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_MAGNOLIA(Constants.TRM_MAGNOLIA, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_WALNUT(Constants.TRM_WALNUT, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_CEDAR(Constants.TRM_CEDAR, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_POPLAR(Constants.TRM_POPLAR, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_ELM(Constants.TRM_ELM, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_RAINBOW_EUCALYPTUS(Constants.TRM_RAINBOW_EUCALYPTUS, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX),
    TRM_JUNIPER(Constants.TRM_JUNIPER, Constants.TRM_MOD_ID, Constants.TRM_MOD_PREFIX);

    private final String name;
    private final String modPrefix;
    private final String modId;
    private final Map<WoodenObjectType, Properties> properties;
    private final LazyValue<Supplier<Double>> enchantingPowerBonus;

    WoodType(final String name) {
        this(name, Constants.MOD_ID);
    }

    WoodType(final String name, final String modId) {
        this(name, modId, "");
    }

    WoodType(final String name, final String modId, final String modPrefix) {
        this.modPrefix = modPrefix;
        this.name = name;
        this.modId = modId;
        String prefixed = modPrefix + name;
        final Map<WoodenObjectType, Properties> properties = new EnumMap<>(WoodenObjectType.class);
        for (final WoodenObjectType objectType : WoodenObjectType.values()) {
            properties.put(objectType, new Properties(() -> Config.SERVER_CONFIG.BURN_TIME.get(prefixed).get(objectType.toString())::get));
        }
        this.properties = Collections.unmodifiableMap(properties);
        this.enchantingPowerBonus = new LazyValue<>(() -> Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(prefixed)::get);
    }

    public static Stream<WoodType> getLoadedValues() {
        try {
            final String dataModId = System.getProperty("ilikewood.datagen.modid");
            if (dataModId != null) {
                return Arrays.stream(values()).filter(woodType -> woodType.getModId().equals(dataModId));
            }
        } catch (NullPointerException | SecurityException | IllegalArgumentException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return Arrays.stream(values()).filter(woodType -> ModList.get().isLoaded(woodType.getModId()));
    }

    public Properties getProperties(final WoodenObjectType objectType) {
        return this.properties.get(objectType);
    }

    public float getEnchantingPowerBonus() {
        return this.enchantingPowerBonus.getValue().get().floatValue();
    }

    public String getModId() {
        return this.modId;
    }

    @Override
    public String toString() {
        return this.modPrefix + this.name;
    }

    public String getPrefixedName() {
        return this.modPrefix + this.name;
    }

    public String getName() {
        return this.name;
    }

    public static final class Properties {
        private final LazyValue<Supplier<Integer>> burnTime;

        public Properties(final Supplier<Supplier<Integer>> burnTime) {
            this.burnTime = new LazyValue<>(burnTime);
        }

        public int getBurnTime() {
            return this.burnTime.getValue().get();
        }
    }
}
