package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.woodtype.IWoodType;

public final class WoodenBookshelfBlock extends WoodenBlock {
    public WoodenBookshelfBlock(final IWoodType type) {
        super(type, Block.Properties.copy(Blocks.BOOKSHELF));
    }

    @Override
    public float getEnchantPowerBonus(final BlockState state, final LevelReader world, final BlockPos pos) {
        return this.getWoodType().getEnchantingPowerBonus();
    }
}
