package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenBlock extends Block implements IWooden {
    private final WoodType type;

    public WoodenBlock(final WoodType type, final Block.Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }
}
