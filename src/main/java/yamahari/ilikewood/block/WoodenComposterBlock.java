package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public final class WoodenComposterBlock extends ComposterBlock implements IWooden {
    private final WoodType type;

    public WoodenComposterBlock(final WoodType type) {
        super(Block.Properties.from(Blocks.COMPOSTER));
        this.type = type;
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }
}
