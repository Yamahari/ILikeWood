package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenWallBlock extends WallBlock implements IWooden {
    private final WoodType type;

    public WoodenWallBlock(final WoodType type) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.F).sound(SoundType.WOOD));
        this.type = type;
        this.setRegistryName(Util.toRegistryName(this.getWoodType().toString(), WoodenObjectType.WALL.toString()));
    }

    @Override
    public WoodType getWoodType() {
        return type;
    }
}
