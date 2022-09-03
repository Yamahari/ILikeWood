package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class CrateBlockStateProvider
    extends AbstractBlockStateProvider
{
    public CrateBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.CRATE);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CRATE.getName());

        final var template = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("frame", Util.toPath(path, woodType.getModId(), "frame", woodType.getName()))
            .texture("body", Util.toPath(path, woodType.getModId(), "body", woodType.getName()));

        this.directionalBlock(block, template);
    }
}