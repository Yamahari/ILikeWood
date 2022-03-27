package yamahari.ilikewood.registry.objecttype;

import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class WoodenItemType extends AbstractWoodenObjectType {
    public static final WoodenItemType STICK = new WoodenItemType(
        Constants.STICK,
        Constants.STICK_PLURAL,
        ILikeWoodConfig.STICKS_CONFIG
    );
    public static final WoodenItemType BOW = new WoodenItemType(
        Constants.BOW,
        Constants.BOW_PLURAL,
        ILikeWoodConfig.BOWS_CONFIG
    );
    public static final WoodenItemType CROSSBOW = new WoodenItemType(
        Constants.CROSSBOW,
        Constants.CROSSBOW_PLURAL,
        ILikeWoodConfig.CROSSBOWS_CONFIG
    );
    public static final WoodenItemType FISHING_ROD = new WoodenItemType(
        Constants.FISHING_ROD,
        Constants.FISHING_ROD_PLURAL,
        ILikeWoodConfig.FISHING_RODS_CONFIG
    );
    public static final WoodenItemType ITEM_FRAME = new WoodenItemType(
        Constants.ITEM_FRAME,
        Constants.ITEM_FRAME_PLURAL,
        ILikeWoodConfig.ITEM_FRAMES_CONFIG
    );

    private WoodenItemType(final String name, final String namePlural, final ILikeWoodConfig config) {
        super(name, namePlural, true, config.flag());
    }

    public static Stream<WoodenItemType> getAll() {
        return Stream.of(STICK, BOW, CROSSBOW, FISHING_ROD, ITEM_FRAME);
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor) {
        return visitor.visit(this);
    }
}
