package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public final class StickItemModelProvider extends AbstractItemModelProvider {
    public StickItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenItemType.STICK);
    }

    @Override
    protected void registerModel(final Item item) {
        this.singleTexture(item.getRegistryName().getPath(),
            mcLoc(Util.toPath(ITEM_FOLDER, "handheld")),
            "layer0",
            modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.STICK.getName(), ((IWooden) item).getWoodType().getName())));
    }
}
