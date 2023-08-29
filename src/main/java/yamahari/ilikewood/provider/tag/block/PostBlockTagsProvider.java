package yamahari.ilikewood.provider.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class PostBlockTagsProvider extends AbstractBlockTagsProvider {
    public PostBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.POST_PLURAL);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(ILikeWoodBlockTags.POSTS, WoodenBlockType.POST);
        this.registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);

        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ILikeWoodBlockTags.POSTS);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ILikeWoodBlockTags.STRIPPED_POSTS);
    }
}
