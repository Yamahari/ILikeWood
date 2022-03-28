package yamahari.ilikewood.provider.tag.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.provider.tag.block.AbstractBlockTagsProvider;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

public final class TieredItemItemTagsProvider extends AbstractItemTagsProvider {
    private final WoodenTieredItemType tieredItemType;
    private final TagKey<Item> tag;

    public TieredItemItemTagsProvider(
        final DataGenerator generator, final AbstractBlockTagsProvider blockTagsProvider,
        final ExistingFileHelper existingFileHelper, final String root,
        final WoodenTieredItemType tieredItemType, final TagKey<Item> tag
    )
    {
        super(generator, blockTagsProvider, existingFileHelper, root);
        this.tieredItemType = tieredItemType;
        this.tag = tag;
    }

    @Override
    protected void addTags() {
        this.registerTag(this.tag, this.tieredItemType);
        this
            .tag(ItemTags.PIGLIN_LOVED)
            .add(ILikeWood.TIERED_ITEM_REGISTRY
                .getObjects(this.tieredItemType)
                .filter(tieredItem -> ((IWoodenTieredItem) tieredItem)
                    .getWoodenItemTier()
                    .equals(VanillaWoodenItemTiers.GOLDEN))
                .toArray(Item[]::new));
    }
}
