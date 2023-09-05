package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DefaultItemTagsProvider extends AbstractItemTagsProvider {
    private final WoodenItemType itemType;
    private final TagKey<Item> tag;

    public DefaultItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper, final String root, final WoodenItemType itemType, final TagKey<Item> tag) {
        super(generator, lookupProvider, existingFileHelper, root);
        this.itemType = itemType;
        this.tag = tag;
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(this.tag, this.itemType);
    }
}
