package yamahari.ilikewood.util.objecttype;

import java.util.stream.Stream;

public final class WoodenObjectTypes {
    public static final WoodenObjectType BARREL = requiresPlanksAndSlab("barrel");
    public static final WoodenObjectType BED = requiresPlanksAndSlab("bed");
    public static final WoodenObjectType BOOKSHELF = requiresPlanksAndSlab("bookshelf");
    public static final WoodenObjectType BOW = requiresPlanksAndSlab("bow");
    public static final WoodenObjectType CHEST = requiresPlanksAndSlab("chest");
    public static final WoodenObjectType COMPOSTER = requiresPlanksAndSlab("composter");
    public static final WoodenObjectType CRAFTING_TABLE = requiresPlanksAndSlab("crafting_table");
    public static final WoodenObjectType CROSSBOW = requiresPlanksAndSlab("crossbow");
    public static final WoodenObjectType FISHING_ROD = requiresPlanksAndSlab("fishing_rod");
    public static final WoodenObjectType ITEM_FRAME = requiresPlanksAndSlab("item_frame");
    public static final WoodenObjectType LADDER = requiresPlanksAndSlab("ladder");
    public static final WoodenObjectType LECTERN = requiresPlanksAndSlab("lectern");
    public static final WoodenObjectType LOG_PILE = requiresLogAndStrippedLog("log_pile");
    public static final WoodenObjectType PANELS = requiresPlanksAndSlab("panels");
    public static final WoodenObjectType POST = requiresLogAndStrippedLog("post");
    public static final WoodenObjectType SAWMILL = new WoodenObjectType("sawmill", true, true, true, true);
    public static final WoodenObjectType SCAFFOLDING = requiresPlanksAndSlab("scaffolding");
    public static final WoodenObjectType SLAB = requiresPlanksAndSlab("slab");
    public static final WoodenObjectType SOUL_TORCH = requiresPlanksAndSlab("soul_torch");
    public static final WoodenObjectType STAIRS = requiresPlanksAndSlab("stairs");
    public static final WoodenObjectType STICK = requiresPlanksAndSlab("stick");
    public static final WoodenObjectType STRIPPED_POST = requiresLogAndStrippedLog("stripped_post");
    public static final WoodenObjectType TORCH = requiresPlanksAndSlab("torch");
    public static final WoodenObjectType WALL = requiresLogAndStrippedLog("wall");
    public static final WoodenObjectType WALL_TORCH = requiresPlanksAndSlab("wall_torch");
    public static final WoodenObjectType WALL_SOUL_TORCH = requiresPlanksAndSlab("wall_soul_torch");

    private WoodenObjectTypes() {
    }

    private static WoodenObjectType requiresPlanksAndSlab(final String name) {
        return new WoodenObjectType(name, true, true, false, false);
    }

    private static WoodenObjectType requiresLogAndStrippedLog(final String name) {
        return new WoodenObjectType(name, false, false, true, true);
    }

    public static Stream<WoodenObjectType> get() {
        return Stream.of(BARREL,
            BED,
            BOOKSHELF,
            BOW,
            CHEST,
            COMPOSTER,
            CRAFTING_TABLE,
            CROSSBOW,
            FISHING_ROD,
            ITEM_FRAME,
            LADDER,
            LECTERN,
            LOG_PILE,
            PANELS,
            POST,
            SAWMILL,
            SCAFFOLDING,
            SLAB,
            SOUL_TORCH,
            STAIRS,
            STICK,
            STRIPPED_POST,
            TORCH,
            WALL,
            WALL_TORCH,
            WALL_SOUL_TORCH);
    }
}
