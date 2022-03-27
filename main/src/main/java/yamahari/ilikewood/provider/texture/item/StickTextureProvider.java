package yamahari.ilikewood.provider.texture.item;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.AbstractWoodenTieredObjectType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Function;

public final class StickTextureProvider extends AbstractItemTextureProvider {
    public StickTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, ITEM_FOLDER, helper, Constants.STICK_PLURAL, WoodenItemType.STICK);
    }

    @Override
    protected void createTexture(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String stickPath = Util.toPath(ITEM_FOLDER, WoodenItemType.STICK.getName());

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(stickPath, woodType.getName()), modLoc(Util.toPath(stickPath, "template")))
            .transform(colorMapTransformer);

        WoodenTieredItemType.getAll().map(AbstractWoodenTieredObjectType::getName).forEach(s -> {
            this
                .withExistingParent(
                    Util.toPath(stickPath, s, woodType.getName()),
                    modLoc(Util.toPath(stickPath, s, "template"))
                )
                .transform(colorMapTransformer);

            this
                .withExistingParent(
                    Util.toPath(stickPath, s, "netherite", woodType.getName()),
                    modLoc(Util.toPath(stickPath, s, "netherite", "template"))
                )
                .transform(colorMapTransformer);
        });
    }
}
