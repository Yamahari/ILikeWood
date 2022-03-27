package yamahari.ilikewood.provider.texture.item;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Function;

public final class CrossbowTextureProvider extends AbstractItemTextureProvider {
    public CrossbowTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, ITEM_FOLDER, helper, Constants.CROSSBOW_PLURAL, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void createTexture(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String path = Util.toPath(ITEM_FOLDER, WoodenItemType.CROSSBOW.getName());

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(
                Util.toPath(path, "arrow", woodType.getName()),
                modLoc(Util.toPath(path, "arrow", "template"))
            )
            .transform(colorMapTransformer);

        this
            .withExistingParent(
                Util.toPath(path, "firework", woodType.getName()),
                modLoc(Util.toPath(path, "firework", "template"))
            )
            .transform(colorMapTransformer);

        for (int i = 0; i < 3; ++i) {
            this
                .withExistingParent(
                    Util.toPath(path, "pulling", Integer.toString(i), woodType.getName()),
                    modLoc(Util.toPath(path, "pulling", Integer.toString(i), "template"))
                )
                .transform(colorMapTransformer);
        }

        this
            .withExistingParent(
                Util.toPath(path, "standby", woodType.getName()),
                modLoc(Util.toPath(path, "standby", "template"))
            )
            .transform(colorMapTransformer);
    }
}
