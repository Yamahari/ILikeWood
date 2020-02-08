package yamahari.ilikewood.client.tileentity;

import net.minecraft.block.BarrelBlock;
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
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenBarrelTileEntity extends BarrelTileEntity implements IWooden {
    public WoodenBarrelTileEntity(final TileEntityType<WoodenBarrelTileEntity> type) {
        super(type);
    }

    @Override
    public WoodType getWoodType() {
        return ((IWooden) this.getBlockState().getBlock()).getWoodType();
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent(
                StringUtils.joinWith(".", "container", Constants.MOD_ID,
                        this.getWoodType().toString() + "_" + WoodenObjectType.BARREL.toString()));
    }

    @Override
    public void func_213962_h() {
        final int x = this.pos.getX();
        final int y = this.pos.getY();
        final int z = this.pos.getZ();
        this.field_213967_b = ChestTileEntity.func_213976_a(this.world, this, x, y, z);
        if (this.field_213967_b > 0) {
            this.func_213964_r();
        } else {
            final BlockState blockState = this.getBlockState();
            if (!(blockState.getBlock() instanceof WoodenBarrelBlock)) {
                this.remove();
                return;
            }

            boolean open = blockState.get(BarrelBlock.PROPERTY_OPEN);
            if (open) {
                this.func_213965_a(blockState, SoundEvents.BLOCK_BARREL_CLOSE);
                this.func_213963_a(blockState, false);
            }
        }
    }
}
