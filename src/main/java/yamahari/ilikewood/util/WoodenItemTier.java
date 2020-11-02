package yamahari.ilikewood.util;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import yamahari.ilikewood.IWoodenItemTier;
import yamahari.ilikewood.config.Config;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class WoodenItemTier implements IWoodenItemTier {
    private final String modId;
    private final String name;
    private final boolean isWood;
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;
    private final Map<WoodenTieredObjectType, Properties> properties;

    public WoodenItemTier(final String name, final boolean isWood, final Supplier<Ingredient> repairMaterial) {
        this(Constants.MOD_ID, name, isWood, repairMaterial);
    }

    // TODO remove config and hardcode values
    public WoodenItemTier(final String modId, final String name, final boolean isWood, final Supplier<Ingredient> repairMaterial) {
        this.modId = modId;
        this.name = name;
        this.isWood = isWood;
        this.harvestLevel = new LazyValue<>(() -> Config.SERVER_CONFIG.HARVEST_LEVEL.get(name)::get);
        this.maxUses = new LazyValue<>(() -> Config.SERVER_CONFIG.MAX_USES.get(name)::get);
        this.efficiency = new LazyValue<>(() -> Config.SERVER_CONFIG.EFFICIENCY.get(name)::get);
        this.attackDamage = new LazyValue<>(() -> Config.SERVER_CONFIG.ATTACK_DAMAGE.get(name)::get);
        this.enchantability = new LazyValue<>(() -> Config.SERVER_CONFIG.ENCHANTABILITY.get(name)::get);
        this.repairMaterial = new LazyValue<>(repairMaterial);
        final Map<WoodenTieredObjectType, Properties> properties = new EnumMap<>(WoodenTieredObjectType.class);
        for (final WoodenTieredObjectType tieredObjectType : WoodenTieredObjectType.values()) {
            final String type = tieredObjectType.toString();
            properties.put(tieredObjectType, new WoodenItemTier.Properties(
                    () -> Config.SERVER_CONFIG.TIERED_ATTACK_SPEED.get(name).get(type)::get,
                    () -> Config.SERVER_CONFIG.TIERED_ATTACK_DAMAGE.get(name).get(type)::get,
                    isWood ? () -> Config.SERVER_CONFIG.TIERED_BURN_TIME.get(name).get(type)::get : () -> () -> -1
            ));
        }
        this.properties = Collections.unmodifiableMap(properties);
    }

    @Override
    public String getModId() {
        return this.modId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isWood() {
        return isWood;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    @Override
    public Properties getProperties(final WoodenTieredObjectType tieredObjectType) {
        assert this.properties.containsKey(tieredObjectType);
        return properties.get(tieredObjectType);
    }
}
