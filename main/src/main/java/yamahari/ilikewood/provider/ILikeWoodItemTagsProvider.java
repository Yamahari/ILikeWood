package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

public final class ILikeWoodItemTagsProvider extends ItemTagsProvider {
    public ILikeWoodItemTagsProvider(final DataGenerator generator, final ILikeWoodBlockTagsProvider blockTagsProvider,
                                     final ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final Tag.Named<Item> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Item[]::new));
        } else {
            this.tag(tag).add(ILikeWood.BLOCK_ITEM_REGISTRY.getObjects(blockType).toArray(Item[]::new));
        }
    }

    private void registerTag(final Tag.Named<Item> tag, final WoodenItemType itemType) {
        this.tag(tag).add(ILikeWood.ITEM_REGISTRY.getObjects(itemType).toArray(Item[]::new));
    }

    private void registerTag(final Tag.Named<Item> tag, final WoodenTieredItemType tieredItemType) {
        this.tag(tag).add(ILikeWood.TIERED_ITEM_REGISTRY.getObjects(tieredItemType).toArray(Item[]::new));
    }

    @Override
    protected void addTags() {
        registerTag(ILikeWoodItemTags.BARRELS, WoodenBlockType.BARREL);
        registerTag(ILikeWoodItemTags.CHESTS, WoodenBlockType.CHEST);
        registerTag(ILikeWoodItemTags.COMPOSTER, WoodenBlockType.COMPOSTER);
        registerTag(ILikeWoodItemTags.BOOKSHELFS, WoodenBlockType.BOOKSHELF);
        registerTag(ILikeWoodItemTags.PANELS_SLABS, WoodenBlockType.PANELS_SLAB);
        registerTag(ILikeWoodItemTags.PANELS_STAIRS, WoodenBlockType.PANELS_STAIRS);
        registerTag(ILikeWoodItemTags.PANELS, WoodenBlockType.PANELS);
        registerTag(ILikeWoodItemTags.WALLS, WoodenBlockType.WALL);
        registerTag(ILikeWoodItemTags.LADDERS, WoodenBlockType.LADDER);
        registerTag(ILikeWoodItemTags.TORCHES, WoodenBlockType.TORCH);
        registerTag(ILikeWoodItemTags.STICKS, WoodenItemType.STICK);
        registerTag(ILikeWoodItemTags.CRAFTING_TABLES, WoodenBlockType.CRAFTING_TABLE);
        registerTag(ILikeWoodItemTags.SCAFFOLDINGS, WoodenBlockType.SCAFFOLDING);
        registerTag(ILikeWoodItemTags.LECTERNS, WoodenBlockType.LECTERN);
        registerTag(ILikeWoodItemTags.POSTS, WoodenBlockType.POST);
        registerTag(ILikeWoodItemTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);
        registerTag(ILikeWoodItemTags.BOWS, WoodenItemType.BOW);
        registerTag(ILikeWoodItemTags.CROSSBOWS, WoodenItemType.CROSSBOW);
        registerTag(ILikeWoodItemTags.ITEM_FRAMES, WoodenItemType.ITEM_FRAME);
        registerTag(ILikeWoodItemTags.BEDS, WoodenBlockType.WHITE_BED);
        registerTag(ILikeWoodItemTags.SAWMILLS, WoodenBlockType.SAWMILL);
        registerTag(ILikeWoodItemTags.FISHING_POLES, WoodenItemType.FISHING_ROD);
        registerTag(ILikeWoodItemTags.SOUL_TORCHES, WoodenBlockType.SOUL_TORCH);
        registerTag(ILikeWoodItemTags.CHAIRS, WoodenBlockType.CHAIR);
        registerTag(ILikeWoodItemTags.TABLES, WoodenBlockType.TABLE);
        registerTag(ILikeWoodItemTags.STOOLS, WoodenBlockType.STOOL);
        registerTag(ILikeWoodItemTags.SINGLE_DRESSER, WoodenBlockType.SINGLE_DRESSER);

        registerTag(ILikeWoodItemTags.AXES, WoodenTieredItemType.AXE);
        registerTag(ILikeWoodItemTags.HOES, WoodenTieredItemType.HOE);
        registerTag(ILikeWoodItemTags.PICKAXES, WoodenTieredItemType.PICKAXE);
        registerTag(ILikeWoodItemTags.SHOVELS, WoodenTieredItemType.SHOVEL);
        registerTag(ILikeWoodItemTags.SWORDS, WoodenTieredItemType.SWORD);

        this.tag(Tags.Items.CHESTS).addTag(ILikeWoodItemTags.CHESTS);
        this.tag(Tags.Items.CHESTS_WOODEN).addTag(ILikeWoodItemTags.CHESTS);
        this.tag(Tags.Items.BARRELS).addTag(ILikeWoodItemTags.BARRELS);
        this.tag(Tags.Items.BARRELS_WOODEN).addTag(ILikeWoodItemTags.BARRELS);

        this.tag(Tags.Items.RODS).addTag(ILikeWoodItemTags.STICKS);

        this.tag(ItemTags.PIGLIN_REPELLENTS).addTag(ILikeWoodItemTags.SOUL_TORCHES);

        this
            .tag(ItemTags.PIGLIN_LOVED)
            .add(WoodenTieredItemType
                .getAll()
                .flatMap(ILikeWood.TIERED_ITEM_REGISTRY::getObjects)
                .filter(item -> ((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.GOLDEN))
                .toArray(Item[]::new));
    }

    @Nonnull
    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
