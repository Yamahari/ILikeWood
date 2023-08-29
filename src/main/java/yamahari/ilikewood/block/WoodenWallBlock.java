package yamahari.ilikewood.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenWallBlock extends WallBlock implements IWooden {
    private final IWoodType type;

    public WoodenWallBlock(final IWoodType type) {
        super(Block.Properties.of().mapColor(MapColor.WOOD).ignitedByLava().instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD));

        this.type = type;
    }

    @Override
    public IWoodType getWoodType() {
        return type;
    }
}
