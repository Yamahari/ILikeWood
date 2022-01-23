package yamahari.ilikewood.provider.texture.block;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Function;

public final class ScaffoldingTextureProvider extends AbstractBlockTextureProvider {
    public ScaffoldingTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, BLOCK_FOLDER, helper, WoodenBlockType.SCAFFOLDING);
    }

    @Override
    protected void createTexture(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String path = Util.toPath(BLOCK_FOLDER, WoodenBlockType.SCAFFOLDING.getName());

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, "bottom", woodType.getName()),
                modLoc(Util.toPath(path, "bottom", "template")))
            .transform(colorMapTransformer);
        this
            .withExistingParent(Util.toPath(path, "side", woodType.getName()),
                modLoc(Util.toPath(path, "side", "template")))
            .transform(colorMapTransformer);
        this
            .withExistingParent(Util.toPath(path, "top", woodType.getName()),
                modLoc(Util.toPath(path, "top", "template")))
            .transform(colorMapTransformer);
    }
}
