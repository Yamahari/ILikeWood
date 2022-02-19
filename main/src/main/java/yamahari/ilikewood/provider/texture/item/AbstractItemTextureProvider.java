package yamahari.ilikewood.provider.texture.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.provider.texture.AbstractTextureProvider;
import yamahari.ilikewood.registry.ILikeWoodItemRegistry;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;

public abstract class AbstractItemTextureProvider
    extends AbstractTextureProvider<Item, WoodenItemType, ILikeWoodItemRegistry> {
    public AbstractItemTextureProvider(final DataGenerator generator, final String folder,
                                       final ExistingFileHelper helper, final String root,
                                       final WoodenItemType itemType) {
        super(generator, folder, helper, root, itemType, ILikeWood.ITEM_REGISTRY);
    }
}
