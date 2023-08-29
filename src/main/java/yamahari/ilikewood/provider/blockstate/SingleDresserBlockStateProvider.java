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

public final class SingleDresserBlockStateProvider
    extends AbstractBlockStateProvider
{
    public SingleDresserBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.SINGLE_DRESSER);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var strippedLogResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);
        final var logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);
        final var planksResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType);

        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, "dresser", "single");

        final ModelFile chair = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("planks", planksResource.getTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("log_end", logResource.getEndTexture());

        this.horizontalBlock(block, chair);
    }
}
