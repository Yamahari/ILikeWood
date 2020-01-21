package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenBookshelfBlock extends WoodenBlock {
    public WoodenBookshelfBlock(final WoodType type) {
        super(type, Block.Properties.from(Blocks.BOOKSHELF));
        this.setRegistryName(Constants.MOD_ID, Util.toRegistryName(this.getWoodType().toString(), WoodenObjectType.BOOKSHELF.toString()));
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return this.getWoodType().getEnchantingPowerBonus();
    }
}
