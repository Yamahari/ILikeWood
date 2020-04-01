package yamahari.ilikewood.data.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.util.Constants;

public final class ItemTags {
    public static final Tag<Item> PANELS = makeWrapperTag("panels");
    public static final Tag<Item> PANELS_SLABS = makeWrapperTag("panels_slab");
    public static final Tag<Item> PANELS_STAIRS = makeWrapperTag("panels_stairs");
    public static final Tag<Item> BARRELS = makeWrapperTag("barrels");
    public static final Tag<Item> CHESTS = makeWrapperTag("chests");
    public static final Tag<Item> COMPOSTER = makeWrapperTag("composter");
    public static final Tag<Item> BOOKSHELFS = makeWrapperTag("bookshelfs");
    public static final Tag<Item> WALLS = makeWrapperTag("walls");
    public static final Tag<Item> LADDERS = makeWrapperTag("ladders");
    public static final Tag<Item> TORCHES = makeWrapperTag("torches");
    public static final Tag<Item> STICKS = makeWrapperTag("sticks");
    public static final Tag<Item> CRAFTING_TABLES = makeWrapperTag("crafting_tables");
    public static final Tag<Item> SCAFFOLDINGS = makeWrapperTag("scaffoldings");
    public static final Tag<Item> LECTERNS = makeWrapperTag("lecterns");
    public static final Tag<Item> POSTS = makeWrapperTag("posts");
    public static final Tag<Item> STRIPPED_POSTS = makeWrapperTag("stripped_posts");

    public static final Tag<Item> AXES = makeWrapperTag("axes");
    public static final Tag<Item> HOES = makeWrapperTag("hoes");
    public static final Tag<Item> PICKAXES = makeWrapperTag("pickaxes");
    public static final Tag<Item> SHOVELS = makeWrapperTag("shovels");
    public static final Tag<Item> SWORDS = makeWrapperTag("swords");

    private ItemTags() {
    }

    private static Tag<Item> makeWrapperTag(final String id) {
        return new net.minecraft.tags.ItemTags.Wrapper(new ResourceLocation(Constants.MOD_ID, id));
    }
}
