package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenLadderBlock extends LadderBlock implements IWooden {
    private final WoodType type;

    public WoodenLadderBlock(final WoodType type) {
        super(Block.Properties.from(Blocks.LADDER));
        this.type = type;
        this.setRegistryName(Util.toRegistryName(this.getWoodType().toString(), WoodenObjectType.LADDER.toString()));
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }
}
