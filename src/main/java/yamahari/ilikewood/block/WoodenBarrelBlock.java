package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenBarrelBlock extends BarrelBlock implements IWooden {
    private final IWoodType woodType;

    public WoodenBarrelBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.BARREL));
        this.woodType = woodType;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@Nonnull final BlockPos blockPos, @Nonnull final BlockState state) {
        return WoodenTileEntityTypes.WOODEN_BARREL.get().create(blockPos, state);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
