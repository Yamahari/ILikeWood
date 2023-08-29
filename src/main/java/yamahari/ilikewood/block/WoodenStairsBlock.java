package yamahari.ilikewood.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenStairsBlock extends StairBlock implements IWooden {
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
