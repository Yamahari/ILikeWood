package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class PanelsSlabBlockStateProvider
    extends AbstractBlockStateProvider
{
    public PanelsSlabBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.PANELS_SLAB);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var slabBottom = this.templateWithPlanks(block, "", Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "slab"));
        final var slabTop = this.templateWithPlanks(block, "/top", Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "slab"));
        final var slabDouble =
            new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.PANELS.getName(), woodType.getModId(), name)));

        this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
    }
}
