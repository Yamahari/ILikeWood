package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class ComposterBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public ComposterBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.COMPOSTER);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenObjectTypes.COMPOSTER.getName());
    }
}
