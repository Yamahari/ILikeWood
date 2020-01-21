package yamahari.ilikewood.util;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class Util {
    private Util() {
        // Nothing to do here!
    }

    public static List<Block> getBlocks(final Class<?> objectHolder) {
        final List<Block> blocks = new ArrayList<>();
        for (final Field field : objectHolder.getFields()) {
            final int modifier = field.getModifiers();
            if (field.isAnnotationPresent(ObjectHolder.class) && !Modifier.isFinal(modifier) && Modifier.isPublic(modifier) && Modifier.isStatic(modifier)) {
                try {
                    final Block block = (Block) field.get(null);
                    blocks.add(block);
                } catch (IllegalAccessException e) {
                    ILikeWood.LOGGER.error(e.getMessage());
                }
            }
        }
        return blocks;
    }

    public static String toRegistryName(final String... elements) {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements) {
        return StringUtils.join(elements, "/");
    }
}
