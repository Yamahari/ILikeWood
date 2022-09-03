package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class CrateTextureProvider
    extends AbstractBlockTextureProvider
{
    public CrateTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, BLOCK_FOLDER, helper, Constants.CRATE_PLURAL, WoodenBlockType.CRATE);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.CRATE.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "frame", woodType.getName()), modLoc(Util.toPath(path, "frame", "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "body", woodType.getName()), modLoc(Util.toPath(path, "body", "template")))
            .transform(colorMapTransformer);
    }
}
