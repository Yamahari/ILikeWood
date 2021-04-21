package yamahari.ilikewood.util.objecttype;

import java.util.stream.Stream;

public final class WoodenEntityType extends AbstractWoodenObjectType {
    public static final WoodenEntityType ITEM_FRAME = new WoodenEntityType("item_frame");

    public WoodenEntityType(final String name) {
        super(name, true);
    }

    public Stream<WoodenEntityType> getAll() {
        return Stream.of(ITEM_FRAME);
    }
}
