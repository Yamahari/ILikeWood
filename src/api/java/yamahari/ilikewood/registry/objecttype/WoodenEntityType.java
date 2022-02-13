package yamahari.ilikewood.registry.objecttype;

import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class WoodenEntityType extends AbstractWoodenObjectType {
    public static final WoodenEntityType ITEM_FRAME =
        new WoodenEntityType(Constants.ITEM_FRAME, Constants.ITEM_FRAME_PLURAL);
    public static final WoodenEntityType CHAIR = new WoodenEntityType(Constants.CHAIR, Constants.CHAIR_PLURAL, false);

    public WoodenEntityType(final String name, final String namePlural, final boolean variesByWoodType) {
        super(name, namePlural, variesByWoodType);
    }

    public WoodenEntityType(final String name, final String namePlural) {
        this(name, namePlural, true);
    }

    public static Stream<WoodenEntityType> getAll() {
        return Stream.of(ITEM_FRAME, CHAIR);
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor) {
        return visitor.visit(this);
    }
}
