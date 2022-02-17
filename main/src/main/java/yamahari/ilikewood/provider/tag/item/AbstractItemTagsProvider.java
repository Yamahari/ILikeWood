package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.nio.file.Path;

public abstract class AbstractItemTagsProvider extends ItemTagsProvider {
    private final String root;

    public AbstractItemTagsProvider(final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
                                    final ExistingFileHelper existingFileHelper, final String root) {
        super(generator, blockTagsProvider, Constants.MOD_ID, existingFileHelper);
        this.root = root;
    }

    protected void registerTag(final Tag.Named<Item> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Item[]::new));
        } else {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(blockType).toArray(Item[]::new));
        }
    }

    protected void registerTag(final Tag.Named<Item> tag, final WoodenItemType itemType) {
        this.tag(tag).add(ILikeWood.ITEM_REGISTRY.getObjects(itemType).toArray(Item[]::new));
    }

    protected void registerTag(final Tag.Named<Item> tag, final WoodenTieredItemType tieredItemType) {
        this.tag(tag).add(ILikeWood.TIERED_ITEM_REGISTRY.getObjects(tieredItemType).toArray(Item[]::new));
    }

    @Nonnull
    @Override
    protected Path getPath(@Nonnull final ResourceLocation resourceLocation) {
        return this.generator
            .getOutputFolder()
            .resolve(Util.toPath(this.root,
                "data",
                resourceLocation.getNamespace(),
                "tags",
                "items",
                resourceLocation.getPath() + ".json"));
    }

    @Override
    protected abstract void addTags();

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - item tags - %s", Constants.MOD_ID, this.root);
    }
}
