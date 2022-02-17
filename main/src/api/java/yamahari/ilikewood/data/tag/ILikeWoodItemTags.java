package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodItemTags {
    public static final Tags.IOptionalNamedTag<Item> PANELS = createOptional(Constants.PANELS_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> PANELS_SLABS = createOptional(Constants.PANELS_SLAB_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> PANELS_STAIRS = createOptional(Constants.PANELS_STAIRS_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> BARRELS = createOptional(Constants.BARREL_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> CHESTS = createOptional(Constants.CHEST_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> COMPOSTER = createOptional(Constants.COMPOSTER_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> BOOKSHELVES = createOptional(Constants.BOOKSHELF_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> WALLS = createOptional(Constants.WALL_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> LADDERS = createOptional(Constants.LADDER_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> TORCHES = createOptional(Constants.TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> STICKS = createOptional(Constants.STICK_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> CRAFTING_TABLES = createOptional(Constants.CRAFTING_TABLE_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> SCAFFOLDINGS = createOptional(Constants.SCAFFOLDING_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> LECTERNS = createOptional(Constants.LECTERN_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> POSTS = createOptional(Constants.POST_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> STRIPPED_POSTS = createOptional(Constants.STRIPPED_POST_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> BOWS = createOptional(Constants.BOW_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> CROSSBOWS = createOptional(Constants.CROSSBOW_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> ITEM_FRAMES = createOptional(Constants.ITEM_FRAME_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> BEDS = createOptional(Constants.BEDS);
    public static final Tags.IOptionalNamedTag<Item> SAWMILLS = createOptional(Constants.SAWMILL_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> FISHING_RODS = createOptional(Constants.FISHING_ROD_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> SOUL_TORCHES = createOptional(Constants.SOUL_TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> CHAIRS = createOptional(Constants.CHAIR_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> TABLES = createOptional(Constants.TABLE_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> STOOLS = createOptional(Constants.STOOL_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> SINGLE_DRESSER = createOptional(Constants.SINGLE_DRESSER_PLURAL);

    public static final Tags.IOptionalNamedTag<Item> AXES = createOptional(Constants.AXE_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> HOES = createOptional(Constants.HOE_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> PICKAXES = createOptional(Constants.PICKAXE_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> SHOVELS = createOptional(Constants.SHOVEL_PLURAL);
    public static final Tags.IOptionalNamedTag<Item> SWORDS = createOptional(Constants.SWORD_PLURAL);

    private ILikeWoodItemTags() {
    }

    private static Tags.IOptionalNamedTag<Item> createOptional(final String id) {
        return ItemTags.createOptional(new ResourceLocation(Constants.MOD_ID, id));
    }
}
