package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class StrippedPostBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public StrippedPostBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.STRIPPED_POST);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, Util.toPath(WoodenObjectTypes.POST.getName(), "stripped", "inventory"));
    }
}
