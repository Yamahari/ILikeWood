package yamahari.ilikewood.data.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollectionSupplier;
import net.minecraft.tags.TagRegistry;
import net.minecraft.tags.TagRegistryManager;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodItemTags {
    public static final TagRegistry<Item> COLLECTION =
        TagRegistryManager.create(new ResourceLocation(Constants.MOD_ID, "item"), ITagCollectionSupplier::getItemTags);
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
    public static final INamedTag<Item> BOWS = makeWrapperTag("bows");
    public static final INamedTag<Item> CROSSBOWS = makeWrapperTag("crossbows");
    public static final INamedTag<Item> ITEM_FRAMES = makeWrapperTag("item_frames");
    public static final INamedTag<Item> BEDS = makeWrapperTag("beds");
    public static final INamedTag<Item> SAWMILLS = makeWrapperTag("sawmills");
    public static final INamedTag<Item> FISHING_POLES = makeWrapperTag("fishing_rods");
    public static final INamedTag<Item> SOUL_TORCHES = makeWrapperTag("soul_torches");

    public static final INamedTag<Item> AXES = makeWrapperTag("axes");
    public static final INamedTag<Item> HOES = makeWrapperTag("hoes");
    public static final INamedTag<Item> PICKAXES = makeWrapperTag("pickaxes");
    public static final INamedTag<Item> SHOVELS = makeWrapperTag("shovels");
    public static final INamedTag<Item> SWORDS = makeWrapperTag("swords");

    private ILikeWoodItemTags() {
    }

    private static INamedTag<Item> makeWrapperTag(final String id) {
        return COLLECTION.createOptional(new ResourceLocation(Constants.MOD_ID, id), null);
    }
}
