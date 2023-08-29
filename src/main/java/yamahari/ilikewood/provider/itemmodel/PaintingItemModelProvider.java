package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public final class PaintingItemModelProvider
    extends AbstractItemModelProvider
{
    public PaintingItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenItemType.PAINTING);
    }

    @Override
    protected void registerModel(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();

        this
            .getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
            .texture("layer0", modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.PAINTING.getName(), woodType.getModId(), woodType.getName())));
    }
}
