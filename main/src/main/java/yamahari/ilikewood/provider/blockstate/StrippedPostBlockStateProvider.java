package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class StrippedPostBlockStateProvider
    extends AbstractBlockStateProvider
{
    public StrippedPostBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.STRIPPED_POST);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.POST.getName());

        final var strippedLog = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();
        final var post = this
            .models()
            .withExistingParent(Util.toPath(path, "stripped", woodType.getModId(), name), modLoc(Util.toPath(path, "template")))
            .texture("wall", strippedLog);
        final var side = this
            .models()
            .withExistingParent(Util.toPath(path, "stripped", woodType.getModId(), "side", name), modLoc(Util.toPath(path, "side", "template")))
            .texture("texture", modLoc(Util.toPath(path, "stripped", woodType.getModId(), name)));

        this.models().withExistingParent(Util.toPath(path, "stripped", woodType.getModId(), "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
            .texture("wall", strippedLog);

        this.postBlock(this.getMultipartBuilder(block), post, side);
    }
}
