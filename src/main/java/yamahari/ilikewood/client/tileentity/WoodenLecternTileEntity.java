package yamahari.ilikewood.client.tileentity;

import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyValue;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenLecternTileEntity extends LecternTileEntity implements IWooden {
    private final IWoodType woodType;
    private final TranslationTextComponent displayName;
    private final LazyValue<TileEntityType<?>> tileEntityType;

    public WoodenLecternTileEntity(final IWoodType woodType) {
        this.woodType = woodType;
        this.displayName = new TranslationTextComponent(
                StringUtils.joinWith(".", "container", Constants.MOD_ID,
                        this.getWoodType().toString() + "_" + WoodenObjectType.LECTERN.toString()));
        this.tileEntityType = new LazyValue<>(WoodenTileEntityTypes.getRegistryObject(WoodenObjectType.LECTERN, woodType));
    }

    @Override
    public TileEntityType<?> getType() {
        return this.tileEntityType.getValue();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ITextComponent getDisplayName() {
        return this.displayName;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
