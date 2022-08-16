package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BarrelTextureProvider
    extends AbstractBlockTextureProvider
{
    public BarrelTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, BLOCK_FOLDER, helper, Constants.BARREL_PLURAL, WoodenBlockType.BARREL);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.BARREL.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "bottom", woodType.getName()), modLoc(Util.toPath(path, "bottom", "template")))
            .transform(colorMapTransformer);
        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "side", woodType.getName()), modLoc(Util.toPath(path, "side", "template")))
            .transform(colorMapTransformer);
        this.withExistingParent(Util.toPath(path, woodType.getModId(), "top", woodType.getName()), modLoc(Util.toPath(path, "top", "template")))
            .transform(colorMapTransformer);

        this.withExistingParent(Util.toPath(path, woodType.getModId(), "top", "open", woodType.getName()), modLoc(Util.toPath(path, "top", "open", "template")))
            .transform(colorMapTransformer);
    }
}