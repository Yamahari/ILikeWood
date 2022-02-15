package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenChestBlock extends ChestBlock implements IWooden {
    private final IWoodType woodType;

    @SuppressWarnings("unchecked")
    public WoodenChestBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.CHEST),
            () -> (BlockEntityType<? extends WoodenChestTileEntity>) WoodenTileEntityTypes.WOODEN_CHEST.get());
        this.woodType = woodType;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@Nonnull final BlockPos blockPos, @Nonnull final BlockState state) {
        return WoodenTileEntityTypes.WOODEN_CHEST.get().create(blockPos, state);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
