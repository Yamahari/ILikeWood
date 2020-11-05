package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenWallBlock extends WallBlock implements IWooden {
    private final IWoodType type;

    public WoodenWallBlock(final IWoodType type) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.F).sound(SoundType.WOOD));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return type;
    }
}
