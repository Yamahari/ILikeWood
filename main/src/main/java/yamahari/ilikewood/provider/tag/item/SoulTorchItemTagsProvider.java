package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class SoulTorchItemTagsProvider extends BlockItemItemTagsProvider {
    public SoulTorchItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                     final ExistingFileHelper existingFileHelper) {
        super(generator,
            blockTagsProvider,
            existingFileHelper,
            Constants.SOUL_TORCH_PLURAL,
            WoodenBlockType.SOUL_TORCH,
            ILikeWoodItemTags.SOUL_TORCHES);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(ItemTags.PIGLIN_REPELLENTS).addTag(this.tag);
    }
}
