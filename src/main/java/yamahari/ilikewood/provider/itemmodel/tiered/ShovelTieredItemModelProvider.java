package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

public final class ShovelTieredItemModelProvider extends AbstractTieredItemModelProvider {
    public ShovelTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenTieredObjectTypes.SHOVEL);
    }

    @Override
    protected void registerModel(final Item item) {
        this.tieredItem(item, WoodenTieredObjectTypes.SHOVEL.getName());
    }
}
