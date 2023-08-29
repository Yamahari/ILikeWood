package yamahari.ilikewood.provider.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class ClimbableBlockTagsProvider extends DefaultBlockTagsProvider {
    public ClimbableBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper, final String root, final WoodenBlockType blockType, final TagKey<Block> tag) {
        super(generator, lookupProvider, existingFileHelper, root, blockType, tag);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        super.addTags(provider);
        this.tag(BlockTags.CLIMBABLE).addTag(this.tag);
    }
}
