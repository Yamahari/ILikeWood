package yamahari.ilikewood.util.objecttype;

import java.util.stream.Stream;

public final class WoodenItemType extends AbstractWoodenObjectType {
    public static final WoodenItemType STICK = new WoodenItemType("stick");
    public static final WoodenItemType BOW = new WoodenItemType("bow");
    public static final WoodenItemType CROSSBOW = new WoodenItemType("crossbow");
    public static final WoodenItemType FISHING_ROD = new WoodenItemType("fishing_rod");
    public static final WoodenItemType ITEM_FRAME = new WoodenItemType("item_frame");

    private WoodenItemType(final String name) {
        super(name, true);
    }

    public static Stream<WoodenItemType> getAll() {
        return Stream.of(STICK, BOW, CROSSBOW, FISHING_ROD, ITEM_FRAME);
    }
}
