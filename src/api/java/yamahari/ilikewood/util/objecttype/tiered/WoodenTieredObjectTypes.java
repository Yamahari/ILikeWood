package yamahari.ilikewood.util.objecttype.tiered;

import java.util.stream.Stream;

public final class WoodenTieredObjectTypes {
    public static final WoodenTieredObjectType AXE = new WoodenTieredObjectType("axe");
    public static final WoodenTieredObjectType HOE = new WoodenTieredObjectType("hoe");
    public static final WoodenTieredObjectType PICKAXE = new WoodenTieredObjectType("pickaxe");
    public static final WoodenTieredObjectType SHOVEL = new WoodenTieredObjectType("shovel");
    public static final WoodenTieredObjectType SWORD = new WoodenTieredObjectType("sword");

    private WoodenTieredObjectTypes() {
    }

    public static Stream<WoodenTieredObjectType> get() {
        return Stream.of(AXE, HOE, PICKAXE, SHOVEL, SWORD);
    }
}
