package yamahari.ilikewood.util;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.Map;
import java.util.function.Supplier;

public final class WoodenItemTier {
    private final String name;
    private final boolean isWood;
    private final Supplier<Integer> harvestLevel;
    private final Supplier<Integer> maxUses;
    private final Supplier<Double> efficiency;
    private final Supplier<Double> attackDamage;
    private final Supplier<Integer> enchantability;
    private final LazyValue<Ingredient> repairMaterial;
    private final Map<String, Properties> properties;

    public WoodenItemTier(
            final String name,
            final boolean isWood,
            final Supplier<Integer> harvestLevel,
            final Supplier<Integer> maxUses,
            final Supplier<Double> efficiency,
            final Supplier<Double> attackDamage,
            final Supplier<Integer> enchantability,
            final Supplier<Ingredient> repairMaterial,
            final Map<String, Properties> properties) {
        this.name = name;
        this.isWood = isWood;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.properties = properties;
    }

    public boolean isWood() {
        return isWood;
    }

    public int getHarvestLevel() {
        return harvestLevel.get();
    }

    public int getMaxUses() {
        return maxUses.get();
    }

    public float getEfficiency() {
        return efficiency.get().floatValue();
    }

    public float getAttackDamage() {
        return attackDamage.get().floatValue();
    }

    public int getEnchantability() {
        return enchantability.get();
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    public Properties getProperties(WoodenTieredObjectType tieredObjectType) {
        assert this.properties.containsKey(tieredObjectType.toString());
        return properties.get(tieredObjectType.toString());
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static final class Properties {
        private final Supplier<Double> attackSpeed;
        private final Supplier<Double> attackDamage;
        private final Supplier<Integer> burnTime;

        public Properties(final Supplier<Double> attackSpeed, final Supplier<Double> attackDamage,
                          final Supplier<Integer> burnTime) {
            this.attackSpeed = attackSpeed;
            this.attackDamage = attackDamage;
            this.burnTime = burnTime;
        }

        public float getAttackSpeed() {
            return this.attackSpeed.get().floatValue();
        }

        public float getAttackDamage() {
            return this.attackDamage.get().floatValue();
        }

        public int getBurnTime() {
            return this.burnTime.get();
        }
    }
}
