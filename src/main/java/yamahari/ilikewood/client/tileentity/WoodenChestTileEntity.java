package yamahari.ilikewood.client.tileentity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

public final class WoodenChestTileEntity extends ChestTileEntity implements IWooden {
    private final IWoodType woodType;

    public WoodenChestTileEntity(final TileEntityType<?> type) {
        this(VanillaWoodTypes.DUMMY, type);
    }

    public WoodenChestTileEntity(final IWoodType woodType, final TileEntityType<?> type) {
        super(type);
        this.woodType = woodType;
    }

    @Override
    protected ITextComponent getDefaultName() {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenChestBlock) {
            final String path = block.getRegistryName().getPath();
            return new TranslationTextComponent(
                    StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }
        return super.getDefaultName();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
