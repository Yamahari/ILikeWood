package yamahari.ilikewood.util;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class Util {
    private Util() {
    }

    public static final Predicate<IWoodType> HAS_PLANKS = ILikeWood.WOODEN_RESOURCE_REGISTRY::hasPlanks;
    public static final Predicate<IWoodType> HAS_LOG = ILikeWood.WOODEN_RESOURCE_REGISTRY::hasLog;
    public static final Predicate<IWoodType> HAS_STRIPPED_LOG = ILikeWood.WOODEN_RESOURCE_REGISTRY::hasStrippedLog;
    public static final Predicate<IWoodType> HAS_SLAB = ILikeWood.WOODEN_RESOURCE_REGISTRY::hasSlab;

    public static Stream<Block> getBlocksWith(final WoodenObjectType objectType, final Predicate<IWoodType> predicate) {
        return WoodenBlocks.getBlocks(objectType).filter(block -> predicate.test(((IWooden) block).getWoodType()));
    }

    public static Stream<Block> getBedBlocksWith(final Predicate<IWoodType> predicate) {
        return WoodenBlocks.getBedBlocks().filter(block -> predicate.test(((IWooden) block).getWoodType()));
    }

    public static Stream<Item> getItemsWith(final WoodenObjectType objectType, final Predicate<IWoodType> predicate) {
        return WoodenItems.getItems(objectType).filter(item -> predicate.test(((IWooden) item).getWoodType()));
    }

    public static Stream<Item> getTieredItemsWith(final WoodenTieredObjectType tieredObjectType, final Predicate<IWoodType> predicate) {
        return WoodenItems.getTieredItems(tieredObjectType).filter(tieredItem -> predicate.test(((IWooden) tieredItem).getWoodType()));
    }

    public static Stream<Item> getTieredItemsWith(final Predicate<IWoodType> predicate) {
        return WoodenItems.getTieredItems(WoodenTieredObjectType.values()).filter(item -> predicate.test(((IWooden) item).getWoodType()));
    }

    public static String toRegistryName(final String... elements) {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements) {
        return StringUtils.join(elements, "/");
    }


    public static IItemProvider getIngredient(final String name, final Class<?> objectHolder) {
        try {
            final Field block = objectHolder.getDeclaredField(name);
            return (IItemProvider) block.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static IItemProvider getIngredient(final IWoodType woodType, final String name) {
        final IItemProvider ingredient;
        switch (woodType.getModId()) {
            case Constants.MOD_ID:
                ingredient = getIngredient(Util.toRegistryName(woodType.toString().toUpperCase(), name.toUpperCase()), Blocks.class);
                break;
            case Constants.BOP_MOD_ID:
                ingredient = getIngredient(Util.toRegistryName(woodType.toString(), name), BOPBlocks.class);
                break;
            default:
                ingredient = null;
                break;
        }
        assert ingredient != null;
        return ingredient;
    }
}
