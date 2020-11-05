package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenStairsBlock extends StairsBlock implements IWooden {
    private final IWoodType type;

    public WoodenStairsBlock(final IWoodType type, final BlockState blockState, final Block.Properties properties) {
        super(() -> blockState, properties);
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
