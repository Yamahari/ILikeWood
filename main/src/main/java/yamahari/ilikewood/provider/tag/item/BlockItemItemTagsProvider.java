package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public class BlockItemItemTagsProvider extends AbstractItemTagsProvider {
    protected final Tag.Named<Item> tag;
    private final WoodenBlockType blockType;

    public BlockItemItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                     final ExistingFileHelper existingFileHelper, final String root,
                                     final WoodenBlockType blockType, final Tag.Named<Item> tag) {
        super(generator, blockTagsProvider, existingFileHelper, root);
        this.blockType = blockType;
        this.tag = tag;
    }

    @Override
    protected void addTags() {
        registerTag(this.tag, this.blockType);
    }
}
