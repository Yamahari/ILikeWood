package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class ChestBlockTagsProvider extends DefaultBlockTagsProvider {
    public ChestBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper, Constants.CHEST_PLURAL, WoodenBlockType.CHEST, ILikeWoodBlockTags.CHESTS);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(Tags.Blocks.CHESTS).addTag(ILikeWoodBlockTags.CHESTS);
        this.tag(Tags.Blocks.CHESTS_WOODEN).addTag(ILikeWoodBlockTags.CHESTS);
    }
}
