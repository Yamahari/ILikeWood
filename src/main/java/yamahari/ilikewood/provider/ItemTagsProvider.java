package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import yamahari.ilikewood.data.tag.ItemTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

public final class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {
    public ItemTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    private static void registerTag(final WoodenObjectType objectType, final Tag.Builder<Item> builder) {
        builder.add(WoodenItems.getItems(objectType).toArray(Item[]::new));
    }

    private static void registerTieredTag(final WoodenTieredObjectType tieredObjectType, final Tag.Builder<Item> builder) {
        builder.add(WoodenItems.getTieredItems(tieredObjectType).toArray(Item[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(WoodenObjectType.BARREL, this.getBuilder(ItemTags.BARRELS));
        registerTag(WoodenObjectType.CHEST, this.getBuilder(ItemTags.CHESTS));
        registerTag(WoodenObjectType.COMPOSTER, this.getBuilder(ItemTags.COMPOSTER));
        registerTag(WoodenObjectType.BOOKSHELF, this.getBuilder(ItemTags.BOOKSHELFS));
        registerTag(WoodenObjectType.SLAB, this.getBuilder(ItemTags.PANELS_SLABS));
        registerTag(WoodenObjectType.STAIRS, this.getBuilder(ItemTags.PANELS_STAIRS));
        registerTag(WoodenObjectType.PANELS, this.getBuilder(ItemTags.PANELS));
        registerTag(WoodenObjectType.WALL, this.getBuilder(ItemTags.WALLS));
        registerTag(WoodenObjectType.LADDER, this.getBuilder(ItemTags.LADDERS));
        registerTag(WoodenObjectType.TORCH, this.getBuilder(ItemTags.TORCHES));
        registerTag(WoodenObjectType.STICK, this.getBuilder(ItemTags.STICKS));
        registerTag(WoodenObjectType.CRAFTING_TABLE, this.getBuilder(ItemTags.CRAFTING_TABLES));
        registerTag(WoodenObjectType.SCAFFOLDING, this.getBuilder(ItemTags.SCAFFOLDINGS));
        registerTag(WoodenObjectType.LECTERN, this.getBuilder(ItemTags.LECTERNS));

        registerTieredTag(WoodenTieredObjectType.AXE, this.getBuilder(ItemTags.AXES));
        registerTieredTag(WoodenTieredObjectType.HOE, this.getBuilder(ItemTags.HOES));
        registerTieredTag(WoodenTieredObjectType.PICKAXE, this.getBuilder(ItemTags.PICKAXES));
        registerTieredTag(WoodenTieredObjectType.SHOVEL, this.getBuilder(ItemTags.SHOVELS));
        registerTieredTag(WoodenTieredObjectType.SWORD, this.getBuilder(ItemTags.SWORDS));
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
