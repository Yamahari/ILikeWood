package yamahari.ilikewood.registry.objecttype;

import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class WoodenBlockType extends AbstractWoodenObjectType {
    public static final WoodenBlockType PANELS = new WoodenBlockType(Constants.PANELS, Constants.PANELS_PLURAL);
    public static final WoodenBlockType PANELS_STAIRS =
        new WoodenBlockType(Constants.PANELS_STAIRS, Constants.PANELS_STAIRS_PLURAL);
    public static final WoodenBlockType PANELS_SLAB =
        new WoodenBlockType(Constants.PANELS_SLAB, Constants.PANELS_SLAB_PLURAL);
    public static final WoodenBlockType BARREL = new WoodenBlockType(Constants.BARREL, Constants.BARREL_PLURAL);
    public static final WoodenBlockType WHITE_BED =
        new WoodenBlockType(Constants.WHITE_BED, Constants.WHITE_BED_PLURAL);
    public static final WoodenBlockType ORANGE_BED =
        new WoodenBlockType(Constants.ORANGE_BED, Constants.ORANGE_BED_PLURAL);
    public static final WoodenBlockType MAGENTA_BED =
        new WoodenBlockType(Constants.MAGENTA_BED, Constants.MAGENTA_BED_PLURAL);
    public static final WoodenBlockType LIGHT_BLUE_BED =
        new WoodenBlockType(Constants.LIGHT_BLUE_BED, Constants.LIGHT_BLUE_BED_PLURAL);
    public static final WoodenBlockType YELLOW_BED =
        new WoodenBlockType(Constants.YELLOW_BED, Constants.YELLOW_BED_PLURAL);
    public static final WoodenBlockType LIME_BED = new WoodenBlockType(Constants.LIME_BED, Constants.LIME_BED_PLURAL);
    public static final WoodenBlockType PINK_BED = new WoodenBlockType(Constants.PINK_BED, Constants.PINK_BED_PLURAL);
    public static final WoodenBlockType GRAY_BED = new WoodenBlockType(Constants.GRAY_BED, Constants.GRAY_BED_PLURAL);
    public static final WoodenBlockType LIGHT_GRAY_BED =
        new WoodenBlockType(Constants.LIGHT_GRAY_BED, Constants.LIGHT_GRAY_BED_PLURAL);
    public static final WoodenBlockType CYAN_BED = new WoodenBlockType(Constants.CYAN_BED, Constants.CYAN_BED_PLURAL);
    public static final WoodenBlockType PURPLE_BED =
        new WoodenBlockType(Constants.PURPLE_BED, Constants.PURPLE_BED_PLURAL);
    public static final WoodenBlockType BLUE_BED = new WoodenBlockType(Constants.BLUE_BED, Constants.BLUE_BED_PLURAL);
    public static final WoodenBlockType BROWN_BED =
        new WoodenBlockType(Constants.BROWN_BED, Constants.BROWN_BED_PLURAL);
    public static final WoodenBlockType GREEN_BED =
        new WoodenBlockType(Constants.GREEN_BED, Constants.GREEN_BED_PLURAL);
    public static final WoodenBlockType RED_BED = new WoodenBlockType(Constants.RED_BED, Constants.RED_BED_PLURAL);
    public static final WoodenBlockType BLACK_BED =
        new WoodenBlockType(Constants.BLACK_BED, Constants.BLACK_BED_PLURAL);
    public static final WoodenBlockType BOOKSHELF =
        new WoodenBlockType(Constants.BOOKSHELF, Constants.BOOKSHELF_PLURAL);
    public static final WoodenBlockType COMPOSTER =
        new WoodenBlockType(Constants.COMPOSTER, Constants.COMPOSTER_PLURAL);
    public static final WoodenBlockType CRAFTING_TABLE =
        new WoodenBlockType(Constants.CRAFTING_TABLE, Constants.CRAFTING_TABLE_PLURAL);
    public static final WoodenBlockType CHEST = new WoodenBlockType(Constants.CHEST, Constants.CHEST_PLURAL);
    public static final WoodenBlockType SAWMILL = new WoodenBlockType(Constants.SAWMILL, Constants.SAWMILL_PLURAL);
    public static final WoodenBlockType LECTERN = new WoodenBlockType(Constants.LECTERN, Constants.LECTERN_PLURAL);
    public static final WoodenBlockType LADDER = new WoodenBlockType(Constants.LADDER, Constants.LADDER_PLURAL);
    public static final WoodenBlockType SCAFFOLDING =
        new WoodenBlockType(Constants.SCAFFOLDING, Constants.SCAFFOLDING_PLURAL);
    public static final WoodenBlockType SOUL_TORCH =
        new WoodenBlockType(Constants.SOUL_TORCH, Constants.SOUL_TORCH_PLURAL);
    public static final WoodenBlockType TORCH = new WoodenBlockType(Constants.TORCH, Constants.TORCH_PLURAL);
    public static final WoodenBlockType WALL_TORCH =
        new WoodenBlockType(Constants.WALL_TORCH, Constants.WALL_TORCH_PLURAL, false);
    public static final WoodenBlockType WALL_SOUL_TORCH =
        new WoodenBlockType(Constants.WALL_SOUL_TORCH, Constants.WALL_SOUL_TORCH_PLURAL, false);
    public static final WoodenBlockType LOG_PILE = new WoodenBlockType(Constants.LOG_PILE, Constants.LOG_PILE_PLURAL);
    public static final WoodenBlockType POST = new WoodenBlockType(Constants.POST, Constants.POST_PLURAL);
    public static final WoodenBlockType STRIPPED_POST =
        new WoodenBlockType(Constants.STRIPPED_POST, Constants.STRIPPED_POST_PLURAL);
    public static final WoodenBlockType WALL = new WoodenBlockType(Constants.WALL, Constants.WALL_PLURAL);
    public static final WoodenBlockType CHAIR = new WoodenBlockType(Constants.CHAIR, Constants.CHAIR_PLURAL);
    public static final WoodenBlockType TABLE = new WoodenBlockType(Constants.TABLE, Constants.TABLE_PLURAL);
    public static final WoodenBlockType STOOL = new WoodenBlockType(Constants.STOOL, Constants.STOOL_PLURAL);
    public static final WoodenBlockType SINGLE_DRESSER =
        new WoodenBlockType(Constants.SINGLE_DRESSER, Constants.SINGLE_DRESSER_PLURAL);

    private final boolean blockItem;

    private WoodenBlockType(final String name, final String namePlural) {
        this(name, namePlural, true);
    }

    private WoodenBlockType(final String name, final String namePlural, final boolean blockItem) {
        super(name, namePlural, true);
        this.blockItem = blockItem;
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor) {
        return visitor.visit(this);
    }

    public static Stream<WoodenBlockType> getAll() {
        return Stream.of(PANELS, PANELS_STAIRS, PANELS_SLAB, BARREL, WHITE_BED, ORANGE_BED, MAGENTA_BED, LIGHT_BLUE_BED,
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
            WALL,
            CHAIR,
            TABLE,
            STOOL,
            SINGLE_DRESSER);
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
