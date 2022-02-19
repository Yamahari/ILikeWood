package yamahari.ilikewood.provider.texture.item.tiered;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.provider.texture.item.AbstractItemTextureProvider;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.function.Function;

public final class ToolTextureProvider extends AbstractItemTextureProvider {
    private final WoodenTieredItemType tieredItemType;

    public ToolTextureProvider(final DataGenerator generator, final ExistingFileHelper helper,
                               final WoodenTieredItemType tieredItemType) {
        super(generator,
            ITEM_FOLDER,
            helper,
            tieredItemType.getNamePlural(),
            WoodenItemType.STICK); // TODO workaround with stick
        this.tieredItemType = tieredItemType;
    }

    @Override
    protected void createTexture(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String path = Util.toPath(ITEM_FOLDER, this.tieredItemType.getName(), "wooden");
        final Function<NativeImage, NativeImage> colorMapTransformer = createColorMapTransformer(woodType);
        this
            .withExistingParent(Util.toPath(path, woodType.getName()), modLoc(Util.toPath(path, "template")))
            .transform(colorMapTransformer);
    }

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - textures - %s", Constants.MOD_ID, this.tieredItemType.getName());
    }
}
