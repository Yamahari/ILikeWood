package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public final class ItemFrameItemModelProvider extends AbstractItemModelProvider {
    public ItemFrameItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenItemType.ITEM_FRAME);
    }

    @Override
    protected void registerModel(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        this
            .getBuilder(Util.toRegistryName(woodType.getName(), WoodenItemType.ITEM_FRAME.getName()))
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.ITEM_FRAME.getName(), woodType.getName())));
    }
}
