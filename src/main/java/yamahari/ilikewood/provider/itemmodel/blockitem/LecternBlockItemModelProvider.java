package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class LecternBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public LecternBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.LECTERN);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenObjectTypes.LECTERN.getName());
    }
}
