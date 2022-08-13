package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public class CampfireItemTagsProvider
    extends AbstractItemTagsProvider
{
    public CampfireItemTagsProvider(
        final DataGenerator generator,
        final AbstractBlockTagsProvider blockTagsProvider,
        final ExistingFileHelper existingFileHelper
    )
    {
        super(generator, blockTagsProvider, existingFileHelper, Constants.CAMPFIRE_PLURAL);
    }

    @Override
    protected void addTags()
    {
        this.registerTag(ILikeWoodItemTags.CAMPFIRES, WoodenBlockType.CAMPFIRE);
        this.registerTag(ILikeWoodItemTags.SOUL_CAMPFIRES, WoodenBlockType.SOUL_CAMPFIRE);
    }
}
