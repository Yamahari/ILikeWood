package yamahari.ilikewood.registry.objecttype;

import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.util.Constants;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class WoodenEntityType extends AbstractWoodenObjectType {
    public static final WoodenEntityType ITEM_FRAME = new WoodenEntityType(
        Constants.ITEM_FRAME,
        Constants.ITEM_FRAME_PLURAL,
        ILikeWoodConfig.ITEM_FRAMES_CONFIG.flag()
    );
    public static final WoodenEntityType CHAIR = new WoodenEntityType(
        Constants.CHAIR,
        Constants.CHAIR_PLURAL,
        false,
        () -> ILikeWoodConfig.CHAIRS_CONFIG.isEnabled() || ILikeWoodConfig.STOOLS_CONFIG.isEnabled()
    );

    public WoodenEntityType(
        final String name,
        final String namePlural,
        final boolean variesByWoodType,
        final Supplier<Boolean> supplier
    ) {
        super(name, namePlural, variesByWoodType, supplier);
    }

    public WoodenEntityType(final String name, final String namePlural, final Supplier<Boolean> supplier) {
        this(name, namePlural, true, supplier);
    }

    public static Stream<WoodenEntityType> getAll() {
        return Stream.of(ITEM_FRAME, CHAIR);
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor) {
        return visitor.visit(this);
    }
}