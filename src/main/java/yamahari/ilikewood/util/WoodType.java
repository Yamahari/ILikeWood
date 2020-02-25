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
    DARK_OAK(Constants.DARK_OAK),
    JUNGLE(Constants.JUNGLE),
    OAK(Constants.OAK),
    SPRUCE(Constants.SPRUCE),
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
    WILLOW(Constants.WILLOW, Constants.BOP_MOD_ID);

    private final String name;
    private final String modId;
    private final Map<WoodenObjectType, Properties> properties;
    private final LazyValue<Supplier<Double>> enchantingPowerBonus;

    WoodType(final String name) {
        this(name, Constants.MOD_ID);
    }

    WoodType(final String name, final String modId) {
        this.name = name;
        this.modId = modId;
        final Map<WoodenObjectType, Properties> properties = new EnumMap<>(WoodenObjectType.class);
        for (final WoodenObjectType objectType : WoodenObjectType.values()) {
            properties.put(objectType, new Properties(() -> Config.SERVER_CONFIG.BURN_TIME.get(name).get(objectType.toString())::get));
        }
        this.properties = Collections.unmodifiableMap(properties);
        this.enchantingPowerBonus = new LazyValue<>(() -> Config.SERVER_CONFIG.ENCHANTING_POWER_BONUS.get(name)::get);
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
