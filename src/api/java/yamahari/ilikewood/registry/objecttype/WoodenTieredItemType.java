package yamahari.ilikewood.registry.objecttype;

import net.minecraft.world.item.Item;

import java.util.stream.Stream;

public final class WoodenTieredItemType extends AbstractWoodenTieredObjectType<Item> {
    public static final WoodenTieredItemType AXE = new WoodenTieredItemType("axe");
    public static final WoodenTieredItemType HOE = new WoodenTieredItemType("hoe");
    public static final WoodenTieredItemType PICKAXE = new WoodenTieredItemType("pickaxe");
    public static final WoodenTieredItemType SHOVEL = new WoodenTieredItemType("shovel");
    public static final WoodenTieredItemType SWORD = new WoodenTieredItemType("sword");

    private WoodenTieredItemType(final String name) {
        super(name);
    }

    public static Stream<WoodenTieredItemType> getAll() {
        return Stream.of(AXE, HOE, PICKAXE, SHOVEL, SWORD);
    }
}
