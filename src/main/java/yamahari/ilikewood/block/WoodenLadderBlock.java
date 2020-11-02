package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenLadderBlock extends LadderBlock implements IWooden {
    private final IWoodType type;

    public WoodenLadderBlock(final IWoodType type) {
        super(Block.Properties.from(Blocks.LADDER));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
