package yamahari.ilikewood.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenBedBlock extends BedBlock implements IWooden {
    private final IWoodType type;
    private final DyeColor color;

    public WoodenBedBlock(final IWoodType type, final DyeColor color) {
        super(color, getProperties(color));
        this.type = type;
        this.color = color;
    }

    private static Block.Properties getProperties(final DyeColor color) {
        switch (color) {
        default:
        case WHITE:
            return Block.Properties.copy(Blocks.WHITE_BED);
        case ORANGE:
            return Block.Properties.copy(Blocks.ORANGE_BED);
        case MAGENTA:
            return Block.Properties.copy(Blocks.MAGENTA_BED);
        case LIGHT_BLUE:
            return Block.Properties.copy(Blocks.LIGHT_BLUE_BED);
        case YELLOW:
            return Block.Properties.copy(Blocks.YELLOW_BED);
        case LIME:
            return Block.Properties.copy(Blocks.LIME_BED);
        case PINK:
            return Block.Properties.copy(Blocks.PINK_BED);
        case GRAY:
            return Block.Properties.copy(Blocks.GRAY_BED);
        case LIGHT_GRAY:
            return Block.Properties.copy(Blocks.LIGHT_GRAY_BED);
        case CYAN:
            return Block.Properties.copy(Blocks.CYAN_BED);
        case PURPLE:
            return Block.Properties.copy(Blocks.PURPLE_BED);
        case BLUE:
            return Block.Properties.copy(Blocks.BLUE_BED);
        case BROWN:
            return Block.Properties.copy(Blocks.BROWN_BED);
        case GREEN:
            return Block.Properties.copy(Blocks.GREEN_BED);
        case RED:
            return Block.Properties.copy(Blocks.RED_BED);
        case BLACK:
            return Block.Properties.copy(Blocks.BLACK_BED);
        }
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }

    public DyeColor getDyeColor() {
        return this.color;
    }

    @Override
    public TileEntity newBlockEntity(@Nonnull final IBlockReader reader) {
        return null;
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderShape(@Nonnull final BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isBed(final BlockState state, final IBlockReader world, final BlockPos pos,
                         @Nullable final Entity player) {
        return true;
    }
}
