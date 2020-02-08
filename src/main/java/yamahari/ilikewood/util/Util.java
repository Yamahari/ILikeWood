package yamahari.ilikewood.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public final class Util {
    private Util() {
    }

    private static ForgeRegistryEntry getRegistryEntry(final String name, final Class<?> objectHolder) {
        try {
            final Field field = objectHolder.getDeclaredField(name);
            final int modifier = field.getModifiers();
            if (field.isAnnotationPresent(ObjectHolder.class) && !Modifier.isFinal(modifier) && Modifier.isPublic(modifier) && Modifier.isStatic(modifier)) {
                try {
                    return (ForgeRegistryEntry) field.get(null);
                } catch (IllegalAccessException e) {
                    ILikeWood.LOGGER.error(e.getMessage());
                }
            }
        } catch (NoSuchFieldException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Block getBlock(final String name, final Class<?> objectHolder) {
        return (Block) getRegistryEntry(name, objectHolder);
    }

    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> TileEntityType<T> getTileEntityType(final String name, final Class<?> objectHolder) {
        return (TileEntityType<T>) getRegistryEntry(name, objectHolder);
    }

    @SuppressWarnings("unchecked")
    private static <T extends ForgeRegistryEntry> List<T> getRegistryEntries(final Class<?> objectHolder) {
        final List<T> registryEntries = new ArrayList<>();
        for (final Field field : objectHolder.getFields()) {
            final int modifier = field.getModifiers();
            if (field.isAnnotationPresent(ObjectHolder.class) && !Modifier.isFinal(modifier) && Modifier.isPublic(modifier) && Modifier.isStatic(modifier)) {
                try {
                    final T registryEntry = (T) field.get(null);
                    registryEntries.add(registryEntry);
                } catch (IllegalAccessException e) {
                    ILikeWood.LOGGER.error(e.getMessage());
                }
            }
        }
        return registryEntries;
    }

    public static List<Block> getBlocks(final Class<?> objectHolder) {
        return getRegistryEntries(objectHolder);
    }

    public static <T extends TileEntity> List<TileEntityType<T>> getTileEntityTypes(final Class<?> objectHolder) {
        return getRegistryEntries(objectHolder);
    }

    public static String toRegistryName(final String... elements) {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements) {
        return StringUtils.join(elements, "/");
    }
}
