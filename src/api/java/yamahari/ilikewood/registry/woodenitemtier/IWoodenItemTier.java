package yamahari.ilikewood.registry.woodenitemtier;

import net.minecraft.item.IItemTier;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.IWooden;

// TODO Does this really need to extend IWooden??
public interface IWoodenItemTier extends IItemTier, IWooden {
    String getModId();

    String getName();

    default boolean isWood() {
        return true;
    }

    Properties getProperties(final WoodenTieredItemType tieredItemType);

    final class Properties {
        private final float attackSpeed;
        private final float attackDamage;
        private final int burnTime;

        public Properties(final float attackSpeed, final float attackDamage, final int burnTime) {
            this.attackSpeed = attackSpeed;
            this.attackDamage = attackDamage;
            this.burnTime = burnTime;
        }

        public float getAttackSpeed() {
            return this.attackSpeed;
        }

        public float getAttackDamage() {
            return this.attackDamage;
        }

        public int getBurnTime() {
            return this.burnTime;
        }
    }
}
