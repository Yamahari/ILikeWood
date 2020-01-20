package yamahari.ilikewood.block;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.objectholder.WoodenTileEntityTypes;
import yamahari.ilikewood.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenBarrelBlock extends BarrelBlock implements IWooden {
    private final WoodType type;

    public WoodenBarrelBlock(final WoodType type) {
        super(Block.Properties.from(Blocks.BARREL));
        this.type = type;
        this.setRegistryName(Constants.MOD_ID, this.type.toString() + "_" + WoodenObjectType.BARREL.toString());
    }

    public TileEntityType<WoodenBarrelTileEntity> getTileEntityType() {
        return WoodenTileEntityTypes.BARREL;
    }

    @Override
    public TileEntity createNewTileEntity(final IBlockReader reader) {
        return this.getTileEntityType().create();
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }
}
