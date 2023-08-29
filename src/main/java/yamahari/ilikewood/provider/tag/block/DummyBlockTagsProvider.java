package yamahari.ilikewood.provider.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class DummyBlockTagsProvider extends AbstractBlockTagsProvider {
    public DummyBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, "");
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        throw new RuntimeException("");
    }

    @Nonnull
    @Override
    protected TagBuilder getOrCreateRawBuilder(@Nonnull final TagKey<Block> tag) {
        throw new RuntimeException("");
    }
}
