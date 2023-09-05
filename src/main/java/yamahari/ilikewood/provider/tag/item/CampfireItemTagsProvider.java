package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class CampfireItemTagsProvider extends AbstractItemTagsProvider {
    public CampfireItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.CAMPFIRE_PLURAL);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.registerTag(ILikeWoodItemTags.CAMPFIRES, WoodenBlockType.CAMPFIRE);
        this.registerTag(ILikeWoodItemTags.SOUL_CAMPFIRES, WoodenBlockType.SOUL_CAMPFIRE);
    }
}
