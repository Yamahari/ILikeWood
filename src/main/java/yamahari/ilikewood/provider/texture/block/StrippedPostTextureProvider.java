package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class StrippedPostTextureProvider extends AbstractBlockTextureProvider {
    public StrippedPostTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, BLOCK_FOLDER, helper, WoodenBlockType.STRIPPED_POST);
    }

    @Override
    protected void createTexture(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.POST.getName(), "stripped");

        final IWoodenStrippedLogResource strippedLogResource =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getName()), strippedLogResource.getSideTexture())
            .transform(this::postTransformer);
    }
}
