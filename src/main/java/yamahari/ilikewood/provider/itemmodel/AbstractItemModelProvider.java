package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

public abstract class AbstractItemModelProvider extends ItemModelProvider {
    private final WoodenItemType itemType;

    public AbstractItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper,
                                     final WoodenItemType itemType) {
        super(generator, Constants.MOD_ID, helper);
        this.itemType = itemType;
    }

    @Override
    protected void registerModels() {
        ILikeWood.ITEM_REGISTRY.getObjects(this.itemType).forEach(this::registerModel);
    }

    protected abstract void registerModel(Item item);

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - item models - %s", Constants.MOD_ID, this.itemType.getName());
    }
}
