package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

public final class SwordTieredItemModelProvider extends AbstractTieredItemModelProvider {
    public SwordTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenTieredItemType.SWORD);
    }

    @Override
    protected void registerModel(final Item item) {
        this.tieredItem(item, WoodenTieredItemType.SWORD.getName());
    }
}
