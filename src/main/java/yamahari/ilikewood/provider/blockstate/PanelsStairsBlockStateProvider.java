package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.WoodenObjectType;

public final class PanelsStairsBlockStateProvider extends AbstractBlockStateProvider {
    public PanelsStairsBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.STAIRS);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final ModelFile stairs = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
        final ModelFile stairsInner =
            this.templateWithPlanks(block, "/inner", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
        final ModelFile stairsOuter =
            this.templateWithPlanks(block, "/outer", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);

        this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
    }
}
