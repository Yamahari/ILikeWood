package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class PostTextureProvider
    extends AbstractBlockTextureProvider
{
    public PostTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, BLOCK_FOLDER, helper, Constants.POST_PLURAL, WoodenBlockType.POST);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.POST.getName());

        final var logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);

        final var builder =
            this.withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), logResource.getSideTexture()).transform(this::postTransformer);

        final var properties = logResource.getSideTextureProperties();

        if (properties.animated())
        {
            builder.animate(properties.interpolate(), properties.frameTime());
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType))
        {
            final var strippedLogResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

            this
                .withExistingParent(Util.toPath(path, "stripped", woodType.getModId(), woodType.getName()), strippedLogResource.getSideTexture())
                .transform(this::postTransformer);
        }
    }
}
