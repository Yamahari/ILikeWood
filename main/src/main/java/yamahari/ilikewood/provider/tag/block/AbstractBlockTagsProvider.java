package yamahari.ilikewood.provider.tag.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.nio.file.Path;

public abstract class AbstractBlockTagsProvider extends BlockTagsProvider {
    private final String root;

    public AbstractBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper,
                                     final String root) {
        super(generator, Constants.MOD_ID, existingFileHelper);
        this.root = root;
    }

    protected void registerTag(final Tag.Named<Block> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Block[]::new));
        } else {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(blockType).toArray(Block[]::new));
        }
    }

    @Nonnull
    @Override
    protected final Path getPath(@Nonnull final ResourceLocation resourceLocation) {
        return this.generator
            .getOutputFolder()
            .resolve(Util.toPath(this.root,
                "data",
                resourceLocation.getNamespace(),
                "tags",
                "blocks",
                resourceLocation.getPath() + ".json"));
    }

    @Override
    protected abstract void addTags();

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - block tags - %s", Constants.MOD_ID, this.root);
    }
}
