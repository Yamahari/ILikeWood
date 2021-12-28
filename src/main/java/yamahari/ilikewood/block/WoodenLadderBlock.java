package yamahari.ilikewood.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenLadderBlock extends LadderBlock implements IWooden {
    private final IWoodType type;

    public WoodenLadderBlock(final IWoodType type) {
        super(Block.Properties.copy(Blocks.LADDER));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
