package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class PostBlockStateProvider extends AbstractBlockStateProvider {
    public PostBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.POST);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = woodType.getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.POST.getName());
        final ResourceLocation log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
        final ModelFile post = this
            .models()
            .withExistingParent(Util.toPath(path, name), modLoc(Util.toPath(path, "template")))
            .texture("wall", log);
        final ModelFile side = this
            .models()
            .withExistingParent(Util.toPath(path, "side", name), modLoc(Util.toPath(path, "side", "template")))
            .texture("texture", modLoc(Util.toPath(path, name)));

        this
            .models()
            .withExistingParent(Util.toPath(path, "inventory", name),
                modLoc(Util.toPath(path, "inventory", "template")))
            .texture("wall", log);

        this.postBlock(this.getMultipartBuilder(block), post, side);
    }
}
