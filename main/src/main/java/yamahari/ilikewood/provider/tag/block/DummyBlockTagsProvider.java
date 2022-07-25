package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public final class DummyBlockTagsProvider extends AbstractBlockTagsProvider
{
    public DummyBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper, "");
    }

    @Override
    protected void addTags() {
        throw new RuntimeException("");
    }

    @Nonnull
    @Override
    protected TagBuilder getOrCreateRawBuilder(@Nonnull final TagKey<Block> tag) {
        throw new RuntimeException("");
    }
}
