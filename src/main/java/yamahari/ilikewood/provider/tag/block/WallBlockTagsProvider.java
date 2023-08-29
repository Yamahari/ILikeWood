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

public final class WallBlockTagsProvider extends DefaultBlockTagsProvider {
    public WallBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.WALL_PLURAL, WoodenBlockType.WALL, ILikeWoodBlockTags.WALLS);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        super.addTags(provider);
        this.tag(BlockTags.WALLS).addTag(ILikeWoodBlockTags.WALLS);
    }
}
