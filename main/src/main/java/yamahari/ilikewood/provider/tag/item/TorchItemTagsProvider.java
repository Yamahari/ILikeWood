package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class TorchItemTagsProvider extends AbstractItemTagsProvider {
    public TorchItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                 final ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, existingFileHelper, Constants.TORCH_PLURAL);
    }

    @Override
    protected void addTags() {
        this.registerTag(ILikeWoodItemTags.TORCHES, WoodenBlockType.TORCH);
        this.registerTag(ILikeWoodItemTags.SOUL_TORCHES, WoodenBlockType.SOUL_TORCH);
        this.tag(ItemTags.PIGLIN_REPELLENTS).addTag(ILikeWoodItemTags.SOUL_TORCHES);
    }
}
