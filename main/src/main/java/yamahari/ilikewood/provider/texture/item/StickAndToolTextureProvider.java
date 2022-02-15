package yamahari.ilikewood.provider.texture.item;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.AbstractWoodenTieredObjectType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Function;

// TODO split this into separate stick & tool texture providers
public final class StickAndToolTextureProvider extends AbstractItemTextureProvider {
    public StickAndToolTextureProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, ITEM_FOLDER, helper, WoodenItemType.STICK);
    }

    @Override
    protected void createTexture(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String stickPath = Util.toPath(ITEM_FOLDER, WoodenItemType.STICK.getName());
        final String axePath = Util.toPath(ITEM_FOLDER, WoodenTieredItemType.AXE.getName(), "wooden");
        final String hoePath = Util.toPath(ITEM_FOLDER, WoodenTieredItemType.HOE.getName(), "wooden");
        final String pickaxePath = Util.toPath(ITEM_FOLDER, WoodenTieredItemType.PICKAXE.getName(), "wooden");
        final String shovelPath = Util.toPath(ITEM_FOLDER, WoodenTieredItemType.SHOVEL.getName(), "wooden");
        final String swordPath = Util.toPath(ITEM_FOLDER, WoodenTieredItemType.SWORD.getName(), "wooden");

        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(stickPath, woodType.getName()), modLoc(Util.toPath(stickPath, "template")))
            .transform(colorMapTransformer);

        WoodenTieredItemType.getAll().map(AbstractWoodenTieredObjectType::getName).forEach(s -> {
            this
                .withExistingParent(Util.toPath(stickPath, s, woodType.getName()),
                    modLoc(Util.toPath(stickPath, s, "template")))
                .transform(colorMapTransformer);

            this
                .withExistingParent(Util.toPath(stickPath, s, "netherite", woodType.getName()),
                    modLoc(Util.toPath(stickPath, s, "netherite", "template")))
                .transform(colorMapTransformer);
        });

        this
            .withExistingParent(Util.toPath(axePath, woodType.getName()), modLoc(Util.toPath(axePath, "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(hoePath, woodType.getName()), modLoc(Util.toPath(hoePath, "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(pickaxePath, woodType.getName()),
                modLoc(Util.toPath(pickaxePath, "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(shovelPath, woodType.getName()),
                modLoc(Util.toPath(shovelPath, "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(swordPath, woodType.getName()), modLoc(Util.toPath(swordPath, "template")))
            .transform(colorMapTransformer);
    }
}
