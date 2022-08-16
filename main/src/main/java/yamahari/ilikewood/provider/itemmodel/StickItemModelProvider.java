package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public final class StickItemModelProvider
    extends AbstractItemModelProvider
{
    public StickItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenItemType.STICK);
    }

    @Override
    protected void registerModel(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();

        this.singleTexture(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath(), mcLoc(Util.toPath(ITEM_FOLDER, "handheld")), "layer0",
            modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.STICK.getName(), woodType.getModId(), woodType.getName()))
        );
    }
}
