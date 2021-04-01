package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class PanelsStairsBlockStateProvider extends AbstractBlockStateProvider {
    public PanelsStairsBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.STAIRS);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final ModelFile stairs = this.templateWithPlanks(block, "", WoodenObjectTypes.PANELS, WoodenObjectTypes.STAIRS);
        final ModelFile stairsInner =
            this.templateWithPlanks(block, "/inner", WoodenObjectTypes.PANELS, WoodenObjectTypes.STAIRS);
        final ModelFile stairsOuter =
            this.templateWithPlanks(block, "/outer", WoodenObjectTypes.PANELS, WoodenObjectTypes.STAIRS);

        this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
    }
}
