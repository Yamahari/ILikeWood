package yamahari.ilikewood.util;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IWoodType;

import java.lang.reflect.Field;

public final class Util {
    private Util() {
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
