package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class FishingRodItemModelProvider extends AbstractItemModelProvider {
    public FishingRodItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.FISHING_ROD);
    }

    @Override
    protected void registerModel(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();

        this
            .getBuilder(item.getRegistryName().getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "handheld_rod"))))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectTypes.FISHING_ROD.getName(), woodType.getName())))
            .override()
            .predicate(mcLoc("cast"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(item.getRegistryName().getPath(), "cast")))))
            .end();

        this
            .getBuilder(Util.toRegistryName(item.getRegistryName().getPath(), "cast"))
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                item.getRegistryName().getPath()))))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectTypes.FISHING_ROD.getName(), "cast", woodType.getName())));
    }
}
