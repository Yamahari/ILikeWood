package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public class DefaultBlockTagsProvider extends AbstractBlockTagsProvider {
    protected final WoodenBlockType blockType;
    protected final TagKey<Block> tag;

    public DefaultBlockTagsProvider(
        final DataGenerator generator, final ExistingFileHelper existingFileHelper,
        final String root, final WoodenBlockType blockType, final TagKey<Block> tag
    )
    {
        super(generator, existingFileHelper, root);
        this.blockType = blockType;
        this.tag = tag;
    }

    @Override
    protected void addTags() {
        this.registerTag(this.tag, this.blockType);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(this.tag);
    }
}
