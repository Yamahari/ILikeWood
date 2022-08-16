package yamahari.ilikewood.provider.texture.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class CrossbowTextureProvider
    extends AbstractItemTextureProvider
{
    public CrossbowTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, ITEM_FOLDER, helper, Constants.CROSSBOW_PLURAL, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void createTexture(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();
        final var path = Util.toPath(ITEM_FOLDER, WoodenItemType.CROSSBOW.getName());

        final var colorMapTransformer = createColorMapTransformer(woodType);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "arrow", woodType.getName()), modLoc(Util.toPath(path, "arrow", "template")))
            .transform(colorMapTransformer);

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "firework", woodType.getName()), modLoc(Util.toPath(path, "firework", "template")))
            .transform(colorMapTransformer);

        for (int i = 0; i < 3; ++i)
        {
            this.withExistingParent(
                Util.toPath(path, woodType.getModId(), "pulling", Integer.toString(i), woodType.getName()),
                modLoc(Util.toPath(path, "pulling", Integer.toString(i), "template"))
            ).transform(colorMapTransformer);
        }

        this
            .withExistingParent(Util.toPath(path, woodType.getModId(), "standby", woodType.getName()), modLoc(Util.toPath(path, "standby", "template")))
            .transform(colorMapTransformer);
    }
}
