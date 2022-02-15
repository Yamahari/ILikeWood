package yamahari.ilikewood.client.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenBarrelBlock;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.Constants;

public final class WoodenBarrelTileEntity extends BarrelBlockEntity {
    public WoodenBarrelTileEntity(final BlockPos blockPos, final BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    protected Component getDefaultName() {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenBarrelBlock) {
            final String path = block.getRegistryName().getPath();
            return new TranslatableComponent(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }

        return super.getDefaultName();
    }

    @Override
    public BlockEntityType<?> getType() {
        return WoodenTileEntityTypes.WOODEN_BARREL.get();
    }
}
