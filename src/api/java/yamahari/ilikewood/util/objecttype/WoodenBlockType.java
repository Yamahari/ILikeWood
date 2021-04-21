package yamahari.ilikewood.util.objecttype;

import java.util.stream.Stream;

public final class WoodenBlockType extends AbstractWoodenObjectType {
    public static final WoodenBlockType PANELS = new WoodenBlockType("panels");
    public static final WoodenBlockType PANELS_STAIRS = new WoodenBlockType("panels_stairs");
    public static final WoodenBlockType PANELS_SLAB = new WoodenBlockType("panels_slab");
    public static final WoodenBlockType BARREL = new WoodenBlockType("barrel");
    public static final WoodenBlockType WHITE_BED = new WoodenBlockType("white_bed");
    public static final WoodenBlockType ORANGE_BED = new WoodenBlockType("orange_bed");
    public static final WoodenBlockType MAGENTA_BED = new WoodenBlockType("magenta_bed");
    public static final WoodenBlockType LIGHT_BLUE_BED = new WoodenBlockType("light_blue_bed");
    public static final WoodenBlockType YELLOW_BED = new WoodenBlockType("yellow_bed");
    public static final WoodenBlockType LIME_BED = new WoodenBlockType("lime_bed");
    public static final WoodenBlockType PINK_BED = new WoodenBlockType("pink_bed");
    public static final WoodenBlockType GRAY_BED = new WoodenBlockType("gray_bed");
    public static final WoodenBlockType LIGHT_GRAY_BED = new WoodenBlockType("light_gray_bed");
    public static final WoodenBlockType CYAN_BED = new WoodenBlockType("cyan_bed");
    public static final WoodenBlockType PURPLE_BED = new WoodenBlockType("purple_bed");
    public static final WoodenBlockType BLUE_BED = new WoodenBlockType("blue_bed");
    public static final WoodenBlockType BROWN_BED = new WoodenBlockType("brown_bed");
    public static final WoodenBlockType GREEN_BED = new WoodenBlockType("green_bed");
    public static final WoodenBlockType RED_BED = new WoodenBlockType("red_bed");
    public static final WoodenBlockType BLACK_BED = new WoodenBlockType("black_bed");
    public static final WoodenBlockType BOOKSHELF = new WoodenBlockType("bookshelf");
    public static final WoodenBlockType COMPOSTER = new WoodenBlockType("composter");
    public static final WoodenBlockType CRAFTING_TABLE = new WoodenBlockType("crafting_table");
    public static final WoodenBlockType CHEST = new WoodenBlockType("chest");
    public static final WoodenBlockType SAWMILL = new WoodenBlockType("sawmill");
    public static final WoodenBlockType LECTERN = new WoodenBlockType("lectern");
    public static final WoodenBlockType LADDER = new WoodenBlockType("ladder");
    public static final WoodenBlockType SCAFFOLDING = new WoodenBlockType("scaffolding");
    public static final WoodenBlockType SOUL_TORCH = new WoodenBlockType("soul_torch");
    public static final WoodenBlockType TORCH = new WoodenBlockType("torch");
    public static final WoodenBlockType WALL_TORCH = new WoodenBlockType("wall_torch", false);
    public static final WoodenBlockType WALL_SOUL_TORCH = new WoodenBlockType("wall_soul_torch", false);
    public static final WoodenBlockType LOG_PILE = new WoodenBlockType("log_pile");
    public static final WoodenBlockType POST = new WoodenBlockType("post");
    public static final WoodenBlockType STRIPPED_POST = new WoodenBlockType("stripped_post");
    public static final WoodenBlockType WALL = new WoodenBlockType("wall");

    private final boolean blockItem;

    private WoodenBlockType(final String name) {
        this(name, true);
    }

    private WoodenBlockType(final String name, final boolean blockItem) {
        super(name, true);
        this.blockItem = blockItem;
    }

    public static Stream<WoodenBlockType> getAll() {
        return Stream.of(PANELS,
            PANELS_STAIRS,
            PANELS_SLAB,
            BARREL,
            WHITE_BED,
            ORANGE_BED,
            MAGENTA_BED,
            LIGHT_BLUE_BED,
            YELLOW_BED,
            LIME_BED,
            PINK_BED,
            GRAY_BED,
            LIGHT_GRAY_BED,
            CYAN_BED,
            PURPLE_BED,
            BLUE_BED,
            BROWN_BED,
            GREEN_BED,
            RED_BED,
            BLACK_BED,
            BOOKSHELF,
            COMPOSTER,
            CRAFTING_TABLE,
            CHEST,
            SAWMILL,
            LECTERN,
            LADDER,
            SCAFFOLDING,
            SOUL_TORCH,
            TORCH,
            WALL_TORCH,
            WALL_SOUL_TORCH,
            LOG_PILE,
            POST,
            STRIPPED_POST,
            WALL);
    }

    public static Stream<WoodenBlockType> getBeds() {
        return Stream.of(WHITE_BED,
            ORANGE_BED,
            MAGENTA_BED,
            LIGHT_BLUE_BED,
            YELLOW_BED,
            LIME_BED,
            PINK_BED,
            GRAY_BED,
            LIGHT_GRAY_BED,
            CYAN_BED,
            PURPLE_BED,
            BLUE_BED,
            BROWN_BED,
            GREEN_BED,
            RED_BED,
            BLACK_BED);
    }

    public boolean hasBlockItem() {
        return this.blockItem;
    }
}
