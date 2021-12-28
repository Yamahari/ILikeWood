package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Util;

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

        this.stairsBlock((StairBlock) block, stairs, stairsInner, stairsOuter);
    }
}
