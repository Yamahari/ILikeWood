package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

public final class PickaxeTieredItemModelProvider extends AbstractTieredItemModelProvider {
    public PickaxeTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenTieredItemType.PICKAXE);
    }

    @Override
    protected void registerModel(final Item item) {
        this.tieredItem(item, WoodenTieredItemType.PICKAXE.getName());
    }
}
