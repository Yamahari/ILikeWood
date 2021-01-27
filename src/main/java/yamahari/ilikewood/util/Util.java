package yamahari.ilikewood.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
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

    // https://github.com/XFactHD/RainbowSixSiegeMC/blob/1.16.x/src/main/java/xfacthd/r6mod/common/util/Utils.java#L15-L34
    public static VoxelShape rotateShape(final Direction from, final Direction to, final VoxelShape shape) {
        if (from.getAxis() == Direction.Axis.Y || to.getAxis() == Direction.Axis.Y) {
            throw new IllegalArgumentException("Invalid Direction!");
        }
        if (from == to) {
            return shape;
        }

        final VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        final int times = (to.getHorizontalIndex() - from.getHorizontalIndex() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) ->
                    buffer[1] = VoxelShapes.or(buffer[1], VoxelShapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }
}
