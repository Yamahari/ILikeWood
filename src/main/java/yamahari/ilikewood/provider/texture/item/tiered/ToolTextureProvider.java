package yamahari.ilikewood.provider.texture.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.provider.texture.item.AbstractItemTextureProvider;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;

public final class ToolTextureProvider
    extends AbstractItemTextureProvider
{
    private final WoodenTieredItemType tieredItemType;

    public ToolTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper,
        final WoodenTieredItemType tieredItemType
    )
    {
        super(generator, ITEM_FOLDER, helper, tieredItemType.getNamePlural(), WoodenItemType.STICK); // TODO workaround with stick
        this.tieredItemType = tieredItemType;
    }

    @Override
    protected void createTexture(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();
        final var path = Util.toPath(ITEM_FOLDER, this.tieredItemType.getName());
        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "wooden", woodType.getName()), modLoc(Util.toPath(path, "wooden", "template")))
            .transform(colorMapTransformer);
    }

    @Nonnull
    @Override
    public String getName()
    {
        return String.format("%s - textures - %s", Constants.MOD_ID, this.tieredItemType.getName());
    }
}
