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

public final class ItemFrameTextureProvider extends AbstractItemTextureProvider {
    public ItemFrameTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, ITEM_FOLDER, helper, Constants.ITEM_FRAME_PLURAL, WoodenItemType.ITEM_FRAME);
    }

    @Override
    protected void createTexture(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String path = Util.toPath(ITEM_FOLDER, WoodenItemType.ITEM_FRAME.getName());

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getName()), modLoc(Util.toPath(path, "template")))
            .transform(colorMapTransformer);
    }
}
