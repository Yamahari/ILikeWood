package yamahari.ilikewood.util;

public enum WoodenObjectType {
    BARREL("barrel"),
    BED("bed"),
    BOOKSHELF("bookshelf"),
    BOW("bow"),
    CHEST("chest"),
    COMPOSTER("composter"),
    CRAFTING_TABLE("crafting_table"),
    CROSSBOW("crossbow"),
    ITEM_FRAME("item_frame"),
    LADDER("ladder"),
    LECTERN("lectern"),
    LOG_PILE("log_pile"),
    PANELS("panels"),
    POST("post"),
    SAWMILL("sawmill"),
    SCAFFOLDING("scaffolding"),
    SLAB("slab"),
    STAIRS("stairs"),
    STICK("stick"),
    STRIPPED_POST("stripped_post"),
    TORCH("torch"),
    WALL("wall"),
    WALL_TORCH("wall_torch");

    private final String name;

    WoodenObjectType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
