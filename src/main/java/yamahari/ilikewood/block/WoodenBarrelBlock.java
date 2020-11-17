package yamahari.ilikewood.block;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenBarrelBlock extends BarrelBlock implements IWooden {
    private final IWoodType woodType;

    public WoodenBarrelBlock(final IWoodType woodType) {
        super(Block.Properties.from(Blocks.BARREL));
        this.woodType = woodType;
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return WoodenTileEntityTypes.WOODEN_BARREL.get().create();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
