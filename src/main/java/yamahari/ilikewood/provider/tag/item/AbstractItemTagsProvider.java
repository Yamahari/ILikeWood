package yamahari.ilikewood.provider.tag.item;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractItemTagsProvider extends ItemTagsProvider {
    private final String root;

    public AbstractItemTagsProvider(final DataGenerator generator, final CompletableFuture<HolderLookup.Provider> lookupProvider, final ExistingFileHelper existingFileHelper, final String root) {
        super(generator.getPackOutput(), lookupProvider, CompletableFuture.completedFuture(TagLookup.empty()), Constants.MOD_ID, existingFileHelper);
        this.root = root;
    }

    protected void registerTag(final TagKey<Item> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Item[]::new));
        }
        else {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(blockType).toArray(Item[]::new));
        }
    }

    protected void registerTag(final TagKey<Item> tag, final WoodenItemType itemType) {
        this.tag(tag).add(ILikeWood.ITEM_REGISTRY.getObjects(itemType).toArray(Item[]::new));
    }

    protected void registerTag(final TagKey<Item> tag, final WoodenTieredItemType tieredItemType) {
        this.tag(tag).add(ILikeWood.TIERED_ITEM_REGISTRY.getObjects(tieredItemType).toArray(Item[]::new));
    }

    @Override
    protected abstract void addTags(@Nonnull HolderLookup.Provider provider);

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - item tags - %s", Constants.MOD_ID, this.root);
    }
}
