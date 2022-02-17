package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class SoulTorchBlockTagsProvider extends TorchBlockTagsProvider {
    public SoulTorchBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator,
            existingFileHelper,
            Constants.SOUL_TORCH_PLURAL,
            WoodenBlockType.SOUL_TORCH,
            ILikeWoodBlockTags.SOUL_TORCHES,
            WoodenBlockType.WALL_SOUL_TORCH,
            ILikeWoodBlockTags.WALL_SOUL_TORCHES);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(BlockTags.PIGLIN_REPELLENTS).addTag(this.tag);
        this.tag(BlockTags.PIGLIN_REPELLENTS).addTag(this.wallTorchTag);
    }
}
