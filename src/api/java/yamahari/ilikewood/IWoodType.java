package yamahari.ilikewood;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Optional;
import java.util.function.Supplier;

public interface IWoodType {
    String getModId();

    String getName();

    Properties getProperties(final WoodenObjectType objectType);

    default float getEnchantingPowerBonus() {
        return 1.F;
    }

    AbstractBlock.Properties getPanelProperties();

    default Optional<Supplier<Block>> getPlanks() {
        return Optional.empty();
    }

    default Optional<Supplier<Block>> getLog() {
        return Optional.empty();
    }

    default Optional<Supplier<Block>> getStrippedLog() {
        return Optional.empty();
    }

    // TODO remove as composter recipe was changed to 7 slaps in 1.15 x)
    default Optional<Supplier<Block>> getFence() {
        return Optional.empty();
    }

    default Optional<Supplier<Block>> getSlab() {
        return Optional.empty();
    }

    final class Properties {
        private final int burnTime;

        public Properties(final int burnTime) {
            this.burnTime = burnTime;
        }

        public int getBurnTime() {
            return this.burnTime;
        }
    }
}
