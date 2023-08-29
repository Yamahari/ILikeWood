package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LogPileBlockStateProvider
    extends AbstractBlockStateProvider
{
    public LogPileBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.LOG_PILE);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);

        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LOG_PILE.getName());

        final ModelFile logPile = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("log_side", logResource.getSideTexture())
            .texture("log_pile_end", modLoc(Util.toPath(path, woodType.getModId(), woodType.getName())));

        this.horizontalBlock(block, logPile);
    }
}
