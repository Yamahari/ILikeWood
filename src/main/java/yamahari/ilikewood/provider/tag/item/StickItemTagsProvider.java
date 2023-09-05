package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class StickItemTagsProvider extends DefaultItemTagsProvider {
    public StickItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, existingFileHelper, Constants.STICK_PLURAL, WoodenItemType.STICK, ILikeWoodItemTags.STICKS);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        super.addTags(provider);
        this.tag(Tags.Items.RODS).addTag(ILikeWoodItemTags.STICKS);
    }
}
