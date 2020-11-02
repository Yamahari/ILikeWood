package yamahari.ilikewood.block;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyValue;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenBarrelBlock extends BarrelBlock implements IWooden {
    private final IWoodType woodType;
    private final LazyValue<TileEntityType<?>> tileEntityType;

    public WoodenBarrelBlock(final IWoodType woodType) {
        super(Block.Properties.from(Blocks.BARREL));
        this.woodType = woodType;
        this.tileEntityType = new LazyValue<>(WoodenTileEntityTypes.getRegistryObject(WoodenObjectType.BARREL, woodType));
    }

    @Override
    public TileEntity createNewTileEntity(@SuppressWarnings("NullableProblems") final IBlockReader reader) {
        return this.tileEntityType.getValue().create();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
