package yamahari.ilikewood.provider.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class BarrelBlockTagsProvider extends DefaultBlockTagsProvider {
    public BarrelBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.BARREL_PLURAL, WoodenBlockType.BARREL, ILikeWoodBlockTags.BARRELS);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        super.addTags(provider);
        this.tag(Tags.Blocks.BARRELS).addTag(ILikeWoodBlockTags.BARRELS);
        this.tag(Tags.Blocks.BARRELS_WOODEN).addTag(ILikeWoodBlockTags.BARRELS);
    }
}
