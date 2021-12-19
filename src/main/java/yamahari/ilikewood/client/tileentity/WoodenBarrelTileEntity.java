package yamahari.ilikewood.client.tileentity;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenBarrelBlock;
import yamahari.ilikewood.util.Constants;

public final class WoodenBarrelTileEntity extends BarrelTileEntity {
    public WoodenBarrelTileEntity(final TileEntityType<?> type) {
        super(type);
    }

    @Override
    protected ITextComponent getDefaultName() {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenBarrelBlock) {
            final String path = block.getRegistryName().getPath();
            return new TranslationTextComponent(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }
        return super.getDefaultName();
    }

    @Override
    public void recheckOpen() {
        final int x = this.worldPosition.getX();
        final int y = this.worldPosition.getY();
        final int z = this.worldPosition.getZ();
        this.openCount = ChestTileEntity.getOpenCount(this.level, this, x, y, z);
        if (this.openCount > 0) {
            this.scheduleRecheck();
        } else {
            final BlockState blockState = this.getBlockState();
            if (!(blockState.getBlock() instanceof WoodenBarrelBlock)) {
                this.setRemoved();
                return;
            }

            final boolean open = blockState.getValue(BarrelBlock.OPEN);
            if (open) {
                this.playSound(blockState, SoundEvents.BARREL_CLOSE);
                this.updateBlockState(blockState, false);
            }
        }
    }
}
