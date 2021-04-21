package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenTieredItemType;

public final class AxeTieredItemModelProvider extends AbstractTieredItemModelProvider {
    public AxeTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenTieredItemType.AXE);
    }

    @Override
    protected void registerModel(final Item item) {
        this.tieredItem(item, WoodenTieredItemType.AXE.getName());
    }
}
