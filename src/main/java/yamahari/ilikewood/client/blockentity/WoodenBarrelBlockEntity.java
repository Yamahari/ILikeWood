package yamahari.ilikewood.client.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenBarrelBlock;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

public final class WoodenBarrelBlockEntity
    extends BarrelBlockEntity
{
    public WoodenBarrelBlockEntity(
        final BlockPos blockPos,
        final BlockState blockState
    )
    {
        super(blockPos, blockState);
    }

    @Nonnull
    @Override
    protected Component getDefaultName()
    {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenBarrelBlock)
        {
            final String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
            return Component.translatable(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }

        return super.getDefaultName();
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType()
    {
        return WoodenBlockEntityTypes.WOODEN_BARREL.get();
    }
}
