package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Util;

public final class ScaffoldingBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public ScaffoldingBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.SCAFFOLDING);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, Util.toPath(WoodenBlockType.SCAFFOLDING.getName(), "stable"));
    }
}
