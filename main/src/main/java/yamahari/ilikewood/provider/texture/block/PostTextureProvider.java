package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.provider.texture.TextureBuilder;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class PostTextureProvider extends AbstractBlockTextureProvider {
    public PostTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, BLOCK_FOLDER, helper, Constants.POST_PLURAL, WoodenBlockType.POST);
    }

    @Override
    protected void createTexture(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.POST.getName());

        final IWoodenLogResource logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);

        final TextureBuilder builder = this
            .withExistingParent(Util.toPath(path, woodType.getName()), logResource.getSideTexture())
            .transform(this::postTransformer);

        final IWoodenLogResource.SideTextureProperties properties = logResource.getSideTextureProperties();

        if (properties.animated()) {
            builder.animate(properties.interpolate(), properties.frameTime());
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
            final String strippedPath = Util.toPath(path, "stripped");

            final IWoodenStrippedLogResource strippedLogResource =
                ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

            this
                .withExistingParent(Util.toPath(strippedPath, woodType.getName()), strippedLogResource.getSideTexture())
                .transform(this::postTransformer);
        }
    }
}
