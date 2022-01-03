package yamahari.ilikewood.registry.objecttype;

import java.util.stream.Stream;

public final class WoodenEntityType extends AbstractWoodenObjectType {
    public static final WoodenEntityType ITEM_FRAME = new WoodenEntityType("item_frame");
    public static final WoodenEntityType CHAIR = new WoodenEntityType("chair", false);

    public WoodenEntityType(final String name, final boolean variesByWoodType) {
        super(name, variesByWoodType);
    }

    public WoodenEntityType(final String name) {
        this(name, true);
    }

    public static Stream<WoodenEntityType> getAll() {
        return Stream.of(ITEM_FRAME, CHAIR);
    }
}
