package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;

public abstract class AbstractItemModelProvider extends ItemModelProvider {
    private final WoodenObjectType objectType;

    public AbstractItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper,
                                     final WoodenObjectType objectType) {
        super(generator, Constants.MOD_ID, helper);
        this.objectType = objectType;
    }

    @Override
    protected void registerModels() {
        if (objectType == WoodenObjectTypes.BED) {
            WoodenItems.getBedItems().forEach(this::registerModel);
        } else {
            WoodenItems.getItems(this.objectType).forEach(this::registerModel);
        }
    }

    protected abstract void registerModel(Item item);

    @Nonnull
    @Override
    public String getName() {
        return super.getName();
    }

}
