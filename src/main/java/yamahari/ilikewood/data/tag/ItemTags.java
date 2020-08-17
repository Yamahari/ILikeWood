package yamahari.ilikewood.data.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.Tag;

public final class ItemTags {
    public static final INamedTag<Item> PANELS = makeWrapperTag("panels");
    public static final INamedTag<Item> PANELS_SLABS = makeWrapperTag("panels_slab");
    public static final INamedTag<Item> PANELS_STAIRS = makeWrapperTag("panels_stairs");
    public static final INamedTag<Item> BARRELS = makeWrapperTag("barrels");
    public static final INamedTag<Item> CHESTS = makeWrapperTag("chests");
    public static final INamedTag<Item> COMPOSTER = makeWrapperTag("composter");
    public static final INamedTag<Item> BOOKSHELFS = makeWrapperTag("bookshelfs");
    public static final INamedTag<Item> WALLS = makeWrapperTag("walls");
    public static final INamedTag<Item> LADDERS = makeWrapperTag("ladders");
    public static final INamedTag<Item> TORCHES = makeWrapperTag("torches");
    public static final INamedTag<Item> STICKS = makeWrapperTag("sticks");
    public static final INamedTag<Item> CRAFTING_TABLES = makeWrapperTag("crafting_tables");
    public static final INamedTag<Item> SCAFFOLDINGS = makeWrapperTag("scaffoldings");
    public static final INamedTag<Item> LECTERNS = makeWrapperTag("lecterns");
    public static final INamedTag<Item> POSTS = makeWrapperTag("posts");
    public static final INamedTag<Item> STRIPPED_POSTS = makeWrapperTag("stripped_posts");

    public static final INamedTag<Item> AXES = makeWrapperTag("axes");
    public static final INamedTag<Item> HOES = makeWrapperTag("hoes");
    public static final INamedTag<Item> PICKAXES = makeWrapperTag("pickaxes");
    public static final INamedTag<Item> SHOVELS = makeWrapperTag("shovels");
    public static final INamedTag<Item> SWORDS = makeWrapperTag("swords");

    private ItemTags() {
    }

    private static INamedTag<Item> makeWrapperTag(final String id) {
        return net.minecraft.tags.ItemTags.makeWrapperTag(id);
    }
}
