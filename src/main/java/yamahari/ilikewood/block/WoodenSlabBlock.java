package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenSlabBlock extends SlabBlock implements IWooden {
    private final WoodType type;

    public WoodenSlabBlock(final WoodType type, final Block.Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public WoodType getType() {
        return this.type;
    }
}
