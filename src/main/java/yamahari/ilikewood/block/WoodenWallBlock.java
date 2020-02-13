package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public final class WoodenWallBlock extends WallBlock implements IWooden {
    private final WoodType type;

    public WoodenWallBlock(final WoodType type) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.F).sound(SoundType.WOOD));
        this.type = type;
    }

    @Override
    public WoodType getWoodType() {
        return type;
    }
}
