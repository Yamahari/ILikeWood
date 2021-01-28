package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

public final class PanelsSlabBlockStateProvider extends AbstractBlockStateProvider {
    public PanelsSlabBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.SLAB);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final String name = ((IWooden) block).getWoodType().getName();
        final ModelFile slabBottom = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
        final ModelFile slabTop =
            this.templateWithPlanks(block, "/top", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
        final ModelFile slabDouble =
            new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", name)));

        this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
    }
}
