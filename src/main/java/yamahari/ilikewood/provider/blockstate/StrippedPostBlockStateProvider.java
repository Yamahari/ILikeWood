package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

public final class StrippedPostBlockStateProvider extends AbstractBlockStateProvider {
    public StrippedPostBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.STRIPPED_POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG));
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = woodType.getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.POST.toString());

        final ResourceLocation strippedLog =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();
        final ModelFile post = this
            .models()
            .withExistingParent(Util.toPath(path, "stripped", name), modLoc(Util.toPath(path, "template")))
            .texture("wall", strippedLog);
        final ModelFile side = this
            .models()
            .withExistingParent(Util.toPath(path, "stripped", "side", name),
                modLoc(Util.toPath(path, "side", "template")))
            .texture("texture", modLoc(Util.toPath(path, "stripped", name)));

        this
            .models()
            .withExistingParent(Util.toPath(path, "stripped", "inventory", name),
                modLoc(Util.toPath(path, "inventory", "template")))
            .texture("wall", strippedLog);

        this.postBlock(this.getMultipartBuilder(block), post, side);
    }
}
