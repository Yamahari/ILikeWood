package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class ScaffoldingBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public ScaffoldingBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.SCAFFOLDING);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, Util.toPath(WoodenObjectTypes.SCAFFOLDING.getName(), "stable"));
    }
}
