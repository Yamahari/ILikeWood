package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenComposterBlock extends ComposterBlock implements IWooden {
    private final IWoodType type;

    public WoodenComposterBlock(final IWoodType type) {
        super(Block.Properties.from(Blocks.COMPOSTER));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
