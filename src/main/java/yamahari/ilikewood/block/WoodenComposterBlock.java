package yamahari.ilikewood.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenComposterBlock extends ComposterBlock implements IWooden {
    private final IWoodType type;

    public WoodenComposterBlock(final IWoodType type) {
        super(Block.Properties.copy(Blocks.COMPOSTER));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }
}
