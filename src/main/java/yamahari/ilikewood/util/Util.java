package yamahari.ilikewood.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.lang.reflect.Field;

public final class Util {
    public static final IWoodType DUMMY_WOOD_TYPE = new DummyWoodType();

    private Util() {
    }

    public static String toRegistryName(final String... elements) {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements) {
        return StringUtils.join(elements, "/");
    }

    public static ItemLike getIngredient(final String name, final Class<?> objectHolder) {
        try {
            final Field block = objectHolder.getDeclaredField(name);
            return (ItemLike) block.get(null);
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

        final VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        final int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] =
                Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }
}
