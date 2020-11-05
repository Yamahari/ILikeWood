package yamahari.ilikewood.client.tileentity;

import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenChestTileEntity extends ChestTileEntity implements IWooden {
    private final IWoodType woodType;
    private final TranslationTextComponent defaultName;

    public WoodenChestTileEntity(final IWoodType woodType, final TileEntityType<?> type) {
        super(type);
        this.woodType = woodType;
        this.defaultName = new TranslationTextComponent(
                StringUtils.joinWith(".", "container", Constants.MOD_ID,
                        this.getWoodType().toString() + "_" + WoodenObjectType.CHEST.toString()));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ITextComponent getDefaultName() {
        return this.defaultName;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
