package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ChestTextureProvider
    extends AbstractBlockTextureProvider
{
    public ChestTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, ENTITY_FOLDER, helper, Constants.CHEST_PLURAL, WoodenBlockType.CHEST);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var path = Util.toPath(ENTITY_FOLDER, WoodenBlockType.CHEST.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "left", woodType.getName()), modLoc(Util.toPath(path, "left", "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "right", woodType.getName()), modLoc(Util.toPath(path, "right", "template")))
            .transform(colorMapTransformer);

        this.withExistingParent(Util.toPath(path, woodType.getModId(), "single", woodType.getName()), modLoc(Util.toPath(path, "single", "template")))
            .transform(colorMapTransformer);
    }
}
