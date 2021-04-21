package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class PanelsBlockStateProvider extends AbstractBlockStateProvider {
    public PanelsBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.PANELS);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        this.simpleBlock(block, this.templateWithPlanks(block, WoodenBlockType.PANELS));
    }
}
