package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import yamahari.ilikewood.data.tag.ItemTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

public final class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {
    public ItemTagsProvider(final DataGenerator generator, BlockTagsProvider blockTagsProvider) {
        super(generator, blockTagsProvider);
    }

    private void registerTag(ITag.INamedTag<Item> tag, WoodenObjectType blocktype){
        this.func_240522_a_(tag).func_240534_a_(WoodenItems.getItems(blocktype).toArray(Item[]::new));
    }

    private void registerTag(ITag.INamedTag<Item> tag, WoodenTieredObjectType blocktype){
        this.func_240522_a_(tag).func_240534_a_(WoodenItems.getTieredItems(blocktype).toArray(Item[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(ItemTags.BARRELS, WoodenObjectType.BARREL);
        registerTag(ItemTags.CHESTS, WoodenObjectType.CHEST);
        registerTag(ItemTags.COMPOSTER, WoodenObjectType.COMPOSTER);
        registerTag(ItemTags.BOOKSHELFS, WoodenObjectType.BOOKSHELF);
        registerTag(ItemTags.PANELS_SLABS, WoodenObjectType.SLAB);
        registerTag(ItemTags.PANELS_STAIRS, WoodenObjectType.STAIRS);
        registerTag(ItemTags.PANELS, WoodenObjectType.PANELS);
        registerTag(ItemTags.WALLS, WoodenObjectType.WALL);
        registerTag(ItemTags.LADDERS, WoodenObjectType.LADDER);
        registerTag(ItemTags.TORCHES, WoodenObjectType.TORCH);
        registerTag(ItemTags.STICKS, WoodenObjectType.STICK);
        registerTag(ItemTags.CRAFTING_TABLES, WoodenObjectType.CRAFTING_TABLE);
        registerTag(ItemTags.SCAFFOLDINGS, WoodenObjectType.SCAFFOLDING);
        registerTag(ItemTags.LECTERNS, WoodenObjectType.LECTERN);
        registerTag(ItemTags.POSTS, WoodenObjectType.POST);
        registerTag(ItemTags.STRIPPED_POSTS, WoodenObjectType.STRIPPED_POST);

        registerTag(ItemTags.AXES, WoodenTieredObjectType.AXE);
        registerTag(ItemTags.HOES, WoodenTieredObjectType.HOE);
        registerTag(ItemTags.PICKAXES, WoodenTieredObjectType.PICKAXE);
        registerTag(ItemTags.SHOVELS, WoodenTieredObjectType.SHOVEL);
        registerTag(ItemTags.SWORDS, WoodenTieredObjectType.SWORD);
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
