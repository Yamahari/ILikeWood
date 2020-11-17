package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenChestBlock extends ChestBlock implements IWooden {
    private final IWoodType woodType;

    @SuppressWarnings("unchecked")
    public WoodenChestBlock(final IWoodType woodType) {
        super(Block.Properties.from(Blocks.CHEST),
                () -> (TileEntityType<? extends WoodenChestTileEntity>) WoodenTileEntityTypes.WOODEN_CHEST.get());
        this.woodType = woodType;
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return WoodenTileEntityTypes.WOODEN_CHEST.get().create();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
