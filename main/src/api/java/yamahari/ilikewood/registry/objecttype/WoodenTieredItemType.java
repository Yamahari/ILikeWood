package yamahari.ilikewood.registry.objecttype;

import net.minecraft.world.item.Item;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class WoodenTieredItemType
    extends AbstractWoodenTieredObjectType<Item>
{
    public static final WoodenTieredItemType AXE = new WoodenTieredItemType(Constants.AXE, Constants.AXE_PLURAL, ILikeWoodConfig.AXES_CONFIG);
    public static final WoodenTieredItemType HOE = new WoodenTieredItemType(Constants.HOE, Constants.HOE_PLURAL, ILikeWoodConfig.HOES_CONFIG);
    public static final WoodenTieredItemType PICKAXE = new WoodenTieredItemType(Constants.PICKAXE, Constants.PICKAXE_PLURAL, ILikeWoodConfig.PICKAXES_CONFIG);
    public static final WoodenTieredItemType SHOVEL = new WoodenTieredItemType(Constants.SHOVEL, Constants.SHOVEL_PLURAL, ILikeWoodConfig.SHOVELS_CONFIG);
    public static final WoodenTieredItemType SWORD = new WoodenTieredItemType(Constants.SWORD, Constants.SWORD_PLURAL, ILikeWoodConfig.SWORDS_CONFIG);

    private WoodenTieredItemType(
        final String name,
        final String namePlural,
        final ILikeWoodConfig config
    )
    {
        super(name, namePlural, config);
    }

    public static Stream<WoodenTieredItemType> getAll()
    {
        return Stream.of(AXE, HOE, PICKAXE, SHOVEL, SWORD);
    }

    @Override
    public boolean acceptVisitor(final IObjectTypeVisitor visitor)
    {
        return visitor.visit(this);
    }
}
