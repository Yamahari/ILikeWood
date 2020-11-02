package yamahari.ilikewood;

import net.minecraft.item.IItemTier;
import net.minecraft.util.LazyValue;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.function.Supplier;

public interface IWoodenItemTier extends IItemTier {
    String getModId();

    String getName();

    boolean isWood();

    Properties getProperties(final WoodenTieredObjectType tieredObjectType);

    final class Properties {
        private final LazyValue<Supplier<Double>> attackSpeed;
        private final LazyValue<Supplier<Double>> attackDamage;
        private final LazyValue<Supplier<Integer>> burnTime;

        public Properties(final Supplier<Supplier<Double>> attackSpeed, final Supplier<Supplier<Double>> attackDamage,
                          final Supplier<Supplier<Integer>> burnTime) {
            this.attackSpeed = new LazyValue<>(attackSpeed);
            this.attackDamage = new LazyValue<>(attackDamage);
            this.burnTime = new LazyValue<>(burnTime);
        }

        public float getAttackSpeed() {
            return this.attackSpeed.getValue().get().floatValue();
        }

        public float getAttackDamage() {
            return this.attackDamage.getValue().get().floatValue();
        }

        public int getBurnTime() {
            return this.burnTime.getValue().get();
        }
    }
}
