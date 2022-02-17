package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public final class ClimbableBlockTagsProvider extends DefaultBlockTagsProvider {
    public ClimbableBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper,
                                      final String root, final WoodenBlockType blockType, final Tag.Named<Block> tag) {
        super(generator, existingFileHelper, root, blockType, tag);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(BlockTags.CLIMBABLE).addTag(this.tag);
    }
}
