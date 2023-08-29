package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LecternTextureProvider
    extends AbstractBlockTextureProvider
{
    public LecternTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, BLOCK_FOLDER, helper, Constants.LECTERN_PLURAL, WoodenBlockType.LECTERN);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.LECTERN.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "base", woodType.getName()), modLoc(Util.toPath(path, "base", "template")))
            .transform(colorMapTransformer);
        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "front", woodType.getName()), modLoc(Util.toPath(path, "front", "template")))
            .transform(colorMapTransformer);
        this.withExistingParent(Util.toPath(path, woodType.getModId(), "sides", woodType.getName()), modLoc(Util.toPath(path, "sides", "template")))
            .transform(colorMapTransformer);

        this.withExistingParent(Util.toPath(path, woodType.getModId(), "top", woodType.getName()), modLoc(Util.toPath(path, "top", "template")))
            .transform(colorMapTransformer);
    }
}
