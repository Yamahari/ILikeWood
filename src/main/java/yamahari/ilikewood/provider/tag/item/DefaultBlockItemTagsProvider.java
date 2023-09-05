package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class DefaultBlockItemTagsProvider extends AbstractItemTagsProvider {
    protected final TagKey<Item> tag;
    private final WoodenBlockType blockType;

    public DefaultBlockItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper, final String root, final WoodenBlockType blockType, final TagKey<Item> tag) {
        super(generator, lookupProvider, existingFileHelper, root);
        this.blockType = blockType;
        this.tag = tag;
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(this.tag, this.blockType);
    }
}
