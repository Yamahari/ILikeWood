package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;

public final class StickItemTagsProvider extends DefaultItemTagsProvider {
    public StickItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                 final ExistingFileHelper existingFileHelper) {
        super(generator,
            blockTagsProvider,
            existingFileHelper,
            Constants.STICK_PLURAL,
            WoodenItemType.STICK,
            ILikeWoodItemTags.STICKS);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(Tags.Items.RODS).addTag(ILikeWoodItemTags.STICKS);
    }
}
