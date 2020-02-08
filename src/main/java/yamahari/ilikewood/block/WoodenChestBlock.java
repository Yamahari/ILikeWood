package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import yamahari.ilikewood.objectholder.chest.WoodenChestTileEntityTypes;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.function.BiPredicate;

public final class WoodenChestBlock extends ChestBlock implements IWooden {
    private final WoodType woodType;
    private final LazyValue<TileEntityType<? extends ChestTileEntity>> tileEntityType;

    public WoodenChestBlock(final WoodType woodType) {
        super(Block.Properties.from(Blocks.CHEST), () -> Util.getTileEntityType(woodType.toString().toUpperCase(), WoodenChestTileEntityTypes.class));
        this.woodType = woodType;
        this.tileEntityType = new LazyValue<>(this.field_226859_a_);
        this.setRegistryName(Util.toRegistryName(this.getWoodType().toString(), WoodenObjectType.CHEST.toString()));
    }

    public TileEntityType<? extends ChestTileEntity> getTileEntityType() {
        return tileEntityType.getValue();
    }

    @Override
    public TileEntity createNewTileEntity(final IBlockReader reader) {
        return tileEntityType.getValue().create();
    }

    @Override
    public TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> func_225536_a_(final BlockState blockState, final World world, final BlockPos blockPos, final boolean isHopper) {
        final BiPredicate<IWorld, BlockPos> predicate;
        if (isHopper) {
            predicate = (w, p) -> false;
        } else {
            predicate = ChestBlock::isBlocked;
        }

        return TileEntityMerger.func_226924_a_(this.tileEntityType.getValue(), ChestBlock::func_226919_h_, ChestBlock::getDirectionToAttached, FACING, blockState, world, blockPos, predicate);
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
