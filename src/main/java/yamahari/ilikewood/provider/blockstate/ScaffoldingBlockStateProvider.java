package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ScaffoldingBlockStateProvider
    extends AbstractBlockStateProvider
{
    public ScaffoldingBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.SCAFFOLDING);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = ((IWooden) block).getWoodType().getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.SCAFFOLDING.getName());
        this.getVariantBuilder(block).forAllStates(blockState ->
        {
            final var stable = blockState.getValue(ScaffoldingBlock.BOTTOM) ? "unstable" : "stable";
            final var template = this
                .models()
                .withExistingParent(Util.toPath(path, woodType.getModId(), stable, name), modLoc(Util.toPath(path, stable, "template")))
                .texture("top", modLoc(Util.toPath(path, woodType.getModId(), "top", name)))
                .texture("side", modLoc(Util.toPath(path, woodType.getModId(), "side", name)))
                .texture("bottom", modLoc(Util.toPath(path, woodType.getModId(), "bottom", name)));
            return ConfiguredModel.builder().modelFile(template).build();
        });
    }
}
