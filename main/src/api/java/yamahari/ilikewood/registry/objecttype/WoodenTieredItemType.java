package yamahari.ilikewood.registry.objecttype;

import net.minecraft.world.item.Item;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class WoodenTieredItemType extends AbstractWoodenTieredObjectType<Item> {
    public static final WoodenTieredItemType AXE = new WoodenTieredItemType(Constants.AXE, Constants.AXE_PLURAL);
    public static final WoodenTieredItemType HOE = new WoodenTieredItemType(Constants.HOE, Constants.HOE_PLURAL);
    public static final WoodenTieredItemType PICKAXE =
        new WoodenTieredItemType(Constants.PICKAXE, Constants.PICKAXE_PLURAL);
    public static final WoodenTieredItemType SHOVEL =
        new WoodenTieredItemType(Constants.SHOVEL, Constants.SHOVEL_PLURAL);
    public static final WoodenTieredItemType SWORD = new WoodenTieredItemType(Constants.SWORD, Constants.SWORD_PLURAL);

    private WoodenTieredItemType(final String name, final String namePlural) {
        super(name, namePlural);
    }

    public static Stream<WoodenTieredItemType> getAll() {
        return Stream.of(AXE, HOE, PICKAXE, SHOVEL, SWORD);
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor) {
        return visitor.visit(this);
    }
}
