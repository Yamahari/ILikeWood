package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenStairsBlock extends StairsBlock implements IWooden {
    private final WoodType type;

    public WoodenStairsBlock(final WoodType type, final BlockState blockState, final Block.Properties properties) {
        super(() -> blockState, properties);
        this.type = type;
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }
}
