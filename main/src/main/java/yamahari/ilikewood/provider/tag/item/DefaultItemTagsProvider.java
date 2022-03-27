package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;

public class DefaultItemTagsProvider extends AbstractItemTagsProvider {
    private final WoodenItemType itemType;
    private final Tag.Named<Item> tag;

    public DefaultItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                final ExistingFileHelper existingFileHelper, final String root,
                                final WoodenItemType itemType, final Tag.Named<Item> tag) {
        super(generator, blockTagsProvider, existingFileHelper, root);
        this.itemType = itemType;
        this.tag = tag;
    }

    @Override
    protected void addTags() {
        this.registerTag(this.tag, this.itemType);
    }
}
