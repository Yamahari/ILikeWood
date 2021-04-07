package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

public final class ILikeWoodItemTagsProvider extends ItemTagsProvider {
    public ILikeWoodItemTagsProvider(final DataGenerator generator, final ILikeWoodBlockTagsProvider blockTagsProvider,
                                     final ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final ITag.INamedTag<Item> tag, final WoodenObjectType objectType) {
        if (objectType.equals(WoodenObjectTypes.BED)) {
            this.getOrCreateBuilder(tag).add(WoodenItems.getBedItems().toArray(Item[]::new));
        } else {
            this.getOrCreateBuilder(tag).add(WoodenItems.getItems(objectType).toArray(Item[]::new));
        }
    }

    private void registerTag(final ITag.INamedTag<Item> tag, final WoodenTieredObjectType tieredObjectType) {
        this.getOrCreateBuilder(tag).add(WoodenItems.getTieredItems(tieredObjectType).toArray(Item[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(ILikeWoodItemTags.BARRELS, WoodenObjectTypes.BARREL);
        registerTag(ILikeWoodItemTags.CHESTS, WoodenObjectTypes.CHEST);
        registerTag(Tags.Items.CHESTS, WoodenObjectTypes.CHEST);
        registerTag(Tags.Items.CHESTS_WOODEN, WoodenObjectTypes.CHEST);
        registerTag(ILikeWoodItemTags.COMPOSTER, WoodenObjectTypes.COMPOSTER);
        registerTag(ILikeWoodItemTags.BOOKSHELFS, WoodenObjectTypes.BOOKSHELF);
        registerTag(ILikeWoodItemTags.PANELS_SLABS, WoodenObjectTypes.SLAB);
        registerTag(ILikeWoodItemTags.PANELS_STAIRS, WoodenObjectTypes.STAIRS);
        registerTag(ILikeWoodItemTags.PANELS, WoodenObjectTypes.PANELS);
        registerTag(ILikeWoodItemTags.WALLS, WoodenObjectTypes.WALL);
        registerTag(ILikeWoodItemTags.LADDERS, WoodenObjectTypes.LADDER);
        registerTag(ILikeWoodItemTags.TORCHES, WoodenObjectTypes.TORCH);
        registerTag(ILikeWoodItemTags.STICKS, WoodenObjectTypes.STICK);
        registerTag(Tags.Items.RODS, WoodenObjectTypes.STICK);
        registerTag(ILikeWoodItemTags.CRAFTING_TABLES, WoodenObjectTypes.CRAFTING_TABLE);
        registerTag(ILikeWoodItemTags.SCAFFOLDINGS, WoodenObjectTypes.SCAFFOLDING);
        registerTag(ILikeWoodItemTags.LECTERNS, WoodenObjectTypes.LECTERN);
        registerTag(ILikeWoodItemTags.POSTS, WoodenObjectTypes.POST);
        registerTag(ILikeWoodItemTags.STRIPPED_POSTS, WoodenObjectTypes.STRIPPED_POST);
        registerTag(ILikeWoodItemTags.BOWS, WoodenObjectTypes.BOW);
        registerTag(ILikeWoodItemTags.CROSSBOWS, WoodenObjectTypes.CROSSBOW);
        registerTag(ILikeWoodItemTags.ITEM_FRAMES, WoodenObjectTypes.ITEM_FRAME);
        registerTag(ILikeWoodItemTags.BEDS, WoodenObjectTypes.BED);
        registerTag(ILikeWoodItemTags.SAWMILLS, WoodenObjectTypes.SAWMILL);
        registerTag(ILikeWoodItemTags.FISHING_POLES, WoodenObjectTypes.FISHING_ROD);
        registerTag(ILikeWoodItemTags.SOUL_TORCHES, WoodenObjectTypes.SOUL_TORCH);
        registerTag(ItemTags.PIGLIN_REPELLENTS, WoodenObjectTypes.SOUL_TORCH);

        registerTag(ILikeWoodItemTags.AXES, WoodenTieredObjectTypes.AXE);
        registerTag(ILikeWoodItemTags.HOES, WoodenTieredObjectTypes.HOE);
        registerTag(ILikeWoodItemTags.PICKAXES, WoodenTieredObjectTypes.PICKAXE);
        registerTag(ILikeWoodItemTags.SHOVELS, WoodenTieredObjectTypes.SHOVEL);
        registerTag(ILikeWoodItemTags.SWORDS, WoodenTieredObjectTypes.SWORD);

        this
            .getOrCreateBuilder(ItemTags.PIGLIN_LOVED)
            .add(WoodenItems
                .getTieredItems(WoodenTieredObjectTypes.AXE,
                    WoodenTieredObjectTypes.HOE,
                    WoodenTieredObjectTypes.PICKAXE,
                    WoodenTieredObjectTypes.SHOVEL,
                    WoodenTieredObjectTypes.SWORD)
                .filter(item -> ((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.GOLDEN))
                .toArray(Item[]::new));
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
