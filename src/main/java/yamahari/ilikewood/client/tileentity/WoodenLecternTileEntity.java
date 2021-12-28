package yamahari.ilikewood.client.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;

public final class WoodenLecternTileEntity extends LecternBlockEntity {
    public WoodenLecternTileEntity(final BlockPos blockPos, final BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return WoodenTileEntityTypes.WOODEN_LECTERN.get();
    }
}
