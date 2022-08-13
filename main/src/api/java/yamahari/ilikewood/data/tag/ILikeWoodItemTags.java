package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodItemTags
{
    public static final TagKey<Item> PANELS = create(Constants.PANELS_PLURAL);
    public static final TagKey<Item> PANELS_SLABS = create(Constants.PANELS_SLAB_PLURAL);
    public static final TagKey<Item> PANELS_STAIRS = create(Constants.PANELS_STAIRS_PLURAL);
    public static final TagKey<Item> BARRELS = create(Constants.BARREL_PLURAL);
    public static final TagKey<Item> CHESTS = create(Constants.CHEST_PLURAL);
    public static final TagKey<Item> COMPOSTER = create(Constants.COMPOSTER_PLURAL);
    public static final TagKey<Item> BOOKSHELVES = create(Constants.BOOKSHELF_PLURAL);
    public static final TagKey<Item> WALLS = create(Constants.WALL_PLURAL);
    public static final TagKey<Item> LADDERS = create(Constants.LADDER_PLURAL);
    public static final TagKey<Item> TORCHES = create(Constants.TORCH_PLURAL);
    public static final TagKey<Item> STICKS = create(Constants.STICK_PLURAL);
    public static final TagKey<Item> CRAFTING_TABLES = create(Constants.CRAFTING_TABLE_PLURAL);
    public static final TagKey<Item> SCAFFOLDINGS = create(Constants.SCAFFOLDING_PLURAL);
    public static final TagKey<Item> LECTERNS = create(Constants.LECTERN_PLURAL);
    public static final TagKey<Item> POSTS = create(Constants.POST_PLURAL);
    public static final TagKey<Item> STRIPPED_POSTS = create(Constants.STRIPPED_POST_PLURAL);
    public static final TagKey<Item> BOWS = create(Constants.BOW_PLURAL);
    public static final TagKey<Item> CROSSBOWS = create(Constants.CROSSBOW_PLURAL);
    public static final TagKey<Item> ITEM_FRAMES = create(Constants.ITEM_FRAME_PLURAL);
    public static final TagKey<Item> BEDS = create(Constants.BEDS);
    public static final TagKey<Item> SAWMILLS = create(Constants.SAWMILL_PLURAL);
    public static final TagKey<Item> FISHING_RODS = create(Constants.FISHING_ROD_PLURAL);
    public static final TagKey<Item> SOUL_TORCHES = create(Constants.SOUL_TORCH_PLURAL);
    public static final TagKey<Item> CHAIRS = create(Constants.CHAIR_PLURAL);
    public static final TagKey<Item> TABLES = create(Constants.TABLE_PLURAL);
    public static final TagKey<Item> STOOLS = create(Constants.STOOL_PLURAL);
    public static final TagKey<Item> SINGLE_DRESSER = create(Constants.SINGLE_DRESSER_PLURAL);
    public static final TagKey<Item> LOG_PILES = create(Constants.LOG_PILE_PLURAL);
    public static final TagKey<Item> CAMPFIRES = create(Constants.CAMPFIRE_PLURAL);
    public static final TagKey<Item> SOUL_CAMPFIRES = create(Constants.SOUL_CAMPFIRE_PLURAL);

    public static final TagKey<Item> AXES = create(Constants.AXE_PLURAL);
    public static final TagKey<Item> HOES = create(Constants.HOE_PLURAL);
    public static final TagKey<Item> PICKAXES = create(Constants.PICKAXE_PLURAL);
    public static final TagKey<Item> SHOVELS = create(Constants.SHOVEL_PLURAL);
    public static final TagKey<Item> SWORDS = create(Constants.SWORD_PLURAL);

    private ILikeWoodItemTags()
    {
    }

    private static TagKey<Item> create(final String id) {
        return ItemTags.create(new ResourceLocation(Constants.MOD_ID, id));
    }
}
