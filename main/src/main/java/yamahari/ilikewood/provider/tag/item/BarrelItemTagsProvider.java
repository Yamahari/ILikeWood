package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class BarrelItemTagsProvider extends DefaultBlockItemTagsProvider {
    public BarrelItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                  final ExistingFileHelper existingFileHelper) {
        super(generator,
            blockTagsProvider,
            existingFileHelper,
            Constants.BARREL_PLURAL,
            WoodenBlockType.BARREL,
            ILikeWoodItemTags.BARRELS);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(Tags.Items.BARRELS).addTag(ILikeWoodItemTags.BARRELS);
        this.tag(Tags.Items.BARRELS_WOODEN).addTag(ILikeWoodItemTags.BARRELS);
    }
}
