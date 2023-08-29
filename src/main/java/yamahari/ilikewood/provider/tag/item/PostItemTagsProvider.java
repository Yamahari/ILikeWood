package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class PostItemTagsProvider extends AbstractItemTagsProvider {
    public PostItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final AbstractBlockTagsProvider blockTagsProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, blockTagsProvider, existingFileHelper, Constants.POST_PLURAL);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(ILikeWoodItemTags.POSTS, WoodenBlockType.POST);
        this.registerTag(ILikeWoodItemTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);
    }
}
