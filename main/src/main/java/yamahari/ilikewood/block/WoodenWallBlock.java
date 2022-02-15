package yamahari.ilikewood.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenWallBlock extends WallBlock implements IWooden {
    private final IWoodType type;

    public WoodenWallBlock(final IWoodType type) {
        super(Block.Properties.of(Material.WOOD).strength(2.F).sound(SoundType.WOOD));
        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return type;
    }
}
