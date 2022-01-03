package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodItemTags {
    public static final Tags.IOptionalNamedTag<Item> PANELS = createOptional("panels");
    public static final Tags.IOptionalNamedTag<Item> PANELS_SLABS = createOptional("panels_slab");
    public static final Tags.IOptionalNamedTag<Item> PANELS_STAIRS = createOptional("panels_stairs");
    public static final Tags.IOptionalNamedTag<Item> BARRELS = createOptional("barrels");
    public static final Tags.IOptionalNamedTag<Item> CHESTS = createOptional("chests");
    public static final Tags.IOptionalNamedTag<Item> COMPOSTER = createOptional("composter");
    public static final Tags.IOptionalNamedTag<Item> BOOKSHELFS = createOptional("bookshelfs");
    public static final Tags.IOptionalNamedTag<Item> WALLS = createOptional("walls");
    public static final Tags.IOptionalNamedTag<Item> LADDERS = createOptional("ladders");
    public static final Tags.IOptionalNamedTag<Item> TORCHES = createOptional("torches");
    public static final Tags.IOptionalNamedTag<Item> STICKS = createOptional("sticks");
    public static final Tags.IOptionalNamedTag<Item> CRAFTING_TABLES = createOptional("crafting_tables");
    public static final Tags.IOptionalNamedTag<Item> SCAFFOLDINGS = createOptional("scaffoldings");
    public static final Tags.IOptionalNamedTag<Item> LECTERNS = createOptional("lecterns");
    public static final Tags.IOptionalNamedTag<Item> POSTS = createOptional("posts");
    public static final Tags.IOptionalNamedTag<Item> STRIPPED_POSTS = createOptional("stripped_posts");
    public static final Tags.IOptionalNamedTag<Item> BOWS = createOptional("bows");
    public static final Tags.IOptionalNamedTag<Item> CROSSBOWS = createOptional("crossbows");
    public static final Tags.IOptionalNamedTag<Item> ITEM_FRAMES = createOptional("item_frames");
    public static final Tags.IOptionalNamedTag<Item> BEDS = createOptional("beds");
    public static final Tags.IOptionalNamedTag<Item> SAWMILLS = createOptional("sawmills");
    public static final Tags.IOptionalNamedTag<Item> FISHING_POLES = createOptional("fishing_rods");
    public static final Tags.IOptionalNamedTag<Item> SOUL_TORCHES = createOptional("soul_torches");
    public static final Tags.IOptionalNamedTag<Item> CHAIRS = createOptional("chairs");
    public static final Tags.IOptionalNamedTag<Item> TABLES = createOptional("tables");

    public static final Tags.IOptionalNamedTag<Item> AXES = createOptional("axes");
    public static final Tags.IOptionalNamedTag<Item> HOES = createOptional("hoes");
    public static final Tags.IOptionalNamedTag<Item> PICKAXES = createOptional("pickaxes");
    public static final Tags.IOptionalNamedTag<Item> SHOVELS = createOptional("shovels");
    public static final Tags.IOptionalNamedTag<Item> SWORDS = createOptional("swords");

    private ILikeWoodItemTags() {
    }

    private static Tags.IOptionalNamedTag<Item> createOptional(final String id) {
        return ItemTags.createOptional(new ResourceLocation(Constants.MOD_ID, id));
    }
}
