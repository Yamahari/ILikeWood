package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class PanelsStairsBlockStateProvider extends AbstractBlockStateProvider {
    public PanelsStairsBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.PANELS_STAIRS);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final ModelFile stairs =
            this.templateWithPlanks(block, "", Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "stairs"));
        final ModelFile stairsInner =
            this.templateWithPlanks(block, "/inner", Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "stairs"));
        final ModelFile stairsOuter =
            this.templateWithPlanks(block, "/outer", Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "stairs"));

        this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
    }
}
