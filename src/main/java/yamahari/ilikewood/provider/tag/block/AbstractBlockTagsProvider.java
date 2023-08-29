package yamahari.ilikewood.provider.tag.block;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractBlockTagsProvider extends BlockTagsProvider {
    private final String root;

    public AbstractBlockTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper, final String root) {
        super(generator.getPackOutput(), lookupProvider, Constants.MOD_ID, existingFileHelper);
        this.root = root;
    }

    protected void registerTag(final TagKey<Block> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Block[]::new));
        }
        else {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(blockType).toArray(Block[]::new));
        }
    }

    @Override
    protected abstract void addTags(@Nonnull HolderLookup.Provider provider);

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - block tags - %s", Constants.MOD_ID, this.root);
    }
}
