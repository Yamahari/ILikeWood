package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class PostBlockTagsProvider extends DefaultBlockTagsProvider {
    public PostBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper, Constants.POST_PLURAL, WoodenBlockType.POST, ILikeWoodBlockTags.POSTS);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ILikeWoodBlockTags.STRIPPED_POSTS);
    }
}
