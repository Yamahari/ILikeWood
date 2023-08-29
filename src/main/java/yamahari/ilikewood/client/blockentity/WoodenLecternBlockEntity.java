package yamahari.ilikewood.client.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;

import javax.annotation.Nonnull;

public final class WoodenLecternBlockEntity
    extends LecternBlockEntity
{
    public WoodenLecternBlockEntity(
        final BlockPos blockPos,
        final BlockState blockState
    )
    {
        super(blockPos, blockState);
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType()
    {
        return WoodenBlockEntityTypes.WOODEN_LECTERN.get();
    }
}
