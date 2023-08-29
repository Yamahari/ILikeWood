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

public final class CampfireBlockTagsProvider extends AbstractBlockTagsProvider {
    public CampfireBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.CAMPFIRE_PLURAL);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(ILikeWoodBlockTags.CAMPFIRES, WoodenBlockType.CAMPFIRE);
        this.registerTag(ILikeWoodBlockTags.SOUL_CAMPFIRES, WoodenBlockType.SOUL_CAMPFIRE);
        this.tag(BlockTags.CAMPFIRES).addTag(ILikeWoodBlockTags.CAMPFIRES);
        this.tag(BlockTags.CAMPFIRES).addTag(ILikeWoodBlockTags.SOUL_CAMPFIRES);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ILikeWoodBlockTags.CAMPFIRES);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ILikeWoodBlockTags.SOUL_CAMPFIRES);
        this.tag(BlockTags.PIGLIN_REPELLENTS).addTag(ILikeWoodBlockTags.SOUL_CAMPFIRES);
    }
}
