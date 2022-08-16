package yamahari.ilikewood.provider.texture.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BowTextureProvider
    extends AbstractItemTextureProvider
{
    public BowTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, ITEM_FOLDER, helper, Constants.BOW_PLURAL, WoodenItemType.BOW);
    }

    @Override
    protected void createTexture(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();
        final var path = Util.toPath(ITEM_FOLDER, WoodenItemType.BOW.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this.withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template"))).transform(colorMapTransformer);

        for (int i = 0; i < 3; ++i)
        {
            this.withExistingParent(
                Util.toPath(path, woodType.getModId(), "pulling", Integer.toString(i), woodType.getName()),
                modLoc(Util.toPath(path, "pulling", Integer.toString(i), "template"))
            ).transform(colorMapTransformer);
        }
    }
}
