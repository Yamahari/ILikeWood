package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class PostBlockStateProvider
    extends AbstractBlockStateProvider
{
    public PostBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.POST);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.POST.getName());
        final var log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
        final var post = this.models().withExistingParent(Util.toPath(path, woodType.getModId(), name), modLoc(Util.toPath(path, "template"))).texture("wall", log);
        final var side = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "side", name), modLoc(Util.toPath(path, "side", "template")))
            .texture("texture", modLoc(Util.toPath(path, woodType.getModId(), name)));

        this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
            .texture("wall", log);

        this.postBlock(this.getMultipartBuilder(block), post, side);
    }
}
