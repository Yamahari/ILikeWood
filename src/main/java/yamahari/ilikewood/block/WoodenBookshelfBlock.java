package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import yamahari.ilikewood.registry.woodtype.IWoodType;

public final class WoodenBookshelfBlock extends WoodenBlock {
    public WoodenBookshelfBlock(final IWoodType type) {
        super(type, Block.Properties.from(Blocks.BOOKSHELF));
    }

    @Override
    public float getEnchantPowerBonus(final BlockState state, final IWorldReader world, final BlockPos pos) {
        return this.getWoodType().getEnchantingPowerBonus();
    }
}
