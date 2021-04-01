package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

public final class PickaxeTieredItemModelProvider extends AbstractTieredItemModelProvider {
    public PickaxeTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenTieredObjectTypes.PICKAXE);
    }

    @Override
    protected void registerModel(final Item item) {
        this.tieredItem(item, WoodenTieredObjectTypes.PICKAXE.getName());
    }
}
