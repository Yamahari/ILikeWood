package yamahari.ilikewood.client.tileentity;

import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenChestTileEntity extends ChestTileEntity implements IWooden {
    private final WoodType woodType;
    private final TranslationTextComponent defaultName;

    public WoodenChestTileEntity(final WoodType woodType, final TileEntityType<WoodenChestTileEntity> type) {
        super(type);
        this.woodType = woodType;
        this.defaultName = new TranslationTextComponent(
                StringUtils.joinWith(".", "container", Constants.MOD_ID,
                        this.getWoodType().toString() + "_" + WoodenObjectType.CHEST.toString()));
    }

    @Override
    protected ITextComponent getDefaultName() {
        return this.defaultName;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
