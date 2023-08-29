package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ChairBlockStateProvider
    extends AbstractBlockStateProvider
{
    public ChairBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.CHAIR);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var strippedLogResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CHAIR.getName());

        final var chair = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("stripped_log_end", strippedLogResource.getEndTexture());

        this.horizontalBlock(block, chair);
    }
}
