package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public class TorchBlockTagsProvider extends DefaultBlockTagsProvider {
    protected final Tag.Named<Block> wallTorchTag;
    private final WoodenBlockType wallTorchBlockType;

    public TorchBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, existingFileHelper, Constants.TORCH_PLURAL, WoodenBlockType.TORCH, ILikeWoodBlockTags.TORCHES);
        this.wallTorchBlockType = WoodenBlockType.WALL_TORCH;
        this.wallTorchTag = ILikeWoodBlockTags.WALL_TORCHES;
    }

    public TorchBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper,
                                  final String root, final WoodenBlockType torchBlockType,
                                  final Tag.Named<Block> torchTag, final WoodenBlockType wallTorchBlockType,
                                  final Tag.Named<Block> wallTorchTag) {
        super(generator, existingFileHelper, root, torchBlockType, torchTag);
        this.wallTorchBlockType = wallTorchBlockType;
        this.wallTorchTag = wallTorchTag;
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.registerTag(this.wallTorchTag, this.wallTorchBlockType);
        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(this.wallTorchTag);
    }
}
