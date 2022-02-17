package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class WallBlockTagsProvider extends DefaultBlockTagsProvider {
    public WallBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper, Constants.WALL_PLURAL, WoodenBlockType.WALL, ILikeWoodBlockTags.WALLS);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(BlockTags.WALLS).addTag(ILikeWoodBlockTags.WALLS);
    }
}
