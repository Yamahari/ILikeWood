package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenSlabBlock extends SlabBlock implements IWooden {
    private final IWoodType type;

    public WoodenSlabBlock(final IWoodType type, final Block.Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
