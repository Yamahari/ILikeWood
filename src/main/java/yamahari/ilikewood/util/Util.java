package yamahari.ilikewood.util;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.registry.WoodenBlocks;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class Util {
    private Util() {
    }

    public static Stream<Block> getBlocksWith(final WoodenObjectType objectType, final Predicate<IWoodType> predicate) {
        return WoodenBlocks.getBlocks(objectType).filter(block -> predicate.test(((IWooden) block).getWoodType()));
    }

    public static Stream<Block> getBlocksWithPlanks(final WoodenObjectType objectType) {
        return getBlocksWith(objectType, woodType -> woodType.getPlanks().isPresent());
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
