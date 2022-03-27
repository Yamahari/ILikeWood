package yamahari.ilikewood.provider.texture.block;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Function;

public final class ChestTextureProvider extends AbstractBlockTextureProvider {
    public ChestTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, ENTITY_FOLDER, helper, Constants.CHEST_PLURAL, WoodenBlockType.CHEST);
    }

    @Override
    protected void createTexture(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String path = Util.toPath(ENTITY_FOLDER, WoodenBlockType.CHEST.getName());

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(
                Util.toPath(path, "left", woodType.getName()),
                modLoc(Util.toPath(path, "left", "template"))
            )
            .transform(colorMapTransformer);

        this
            .withExistingParent(
                Util.toPath(path, "right", woodType.getName()),
                modLoc(Util.toPath(path, "right", "template"))
            )
            .transform(colorMapTransformer);

        this
            .withExistingParent(
                Util.toPath(path, "single", woodType.getName()),
                modLoc(Util.toPath(path, "single", "template"))
            )
            .transform(colorMapTransformer);
    }
}
