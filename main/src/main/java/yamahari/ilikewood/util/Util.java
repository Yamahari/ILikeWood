package yamahari.ilikewood.util;

import com.google.common.base.Preconditions;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Util
{
    public static final IWoodType DUMMY_WOOD_TYPE = new DummyWoodType();

    private Util()
    {
    }

    @Nullable
    public static String getDataGenModId()
    {
        String dataGenModId = null;
        try
        {
            dataGenModId = System.getProperty("ilikewood.datagen.modid");

        }
        catch (NullPointerException | SecurityException | IllegalArgumentException e)
        {
            ILikeWood.LOGGER.error(e.getMessage());
        }

        return dataGenModId;
    }

    public static String toRegistryName(final String... elements)
    {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements)
    {
        return StringUtils.join(elements, "/");
    }

    public static ItemLike getIngredient(
        final String name,
        final Class<?> objectHolder
    )
    {
        try
        {
            final Field block = objectHolder.getDeclaredField(name);
            return (ItemLike) block.get(null);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return null;
    }

    // https://github.com/XFactHD/RainbowSixSiegeMC/blob/1.16.x/src/main/java/xfacthd/r6mod/common/util/Utils.java#L15-L34
    public static VoxelShape rotateShape(
        final Direction from,
        final Direction to,
        final VoxelShape shape
    )
    {
        if (from.getAxis() == Direction.Axis.Y || to.getAxis() == Direction.Axis.Y)
        {
            throw new IllegalArgumentException("Invalid Direction!");
        }
        if (from == to)
        {
            return shape;
        }

        final VoxelShape[] buffer = new VoxelShape[]{
            shape,
            Shapes.empty()
        };

        final int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++)
        {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }

    private static String toTranslationNameImpl(final String registryName)
    {
        return Arrays.stream(StringUtils.split(registryName, '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static String toTranslationName(final String registryName)
    {
        for (final IModPlugin plugin : ILikeWood.PLUGINS)
        {
            if (registryName.startsWith(plugin.getModId()))
            {
                return toTranslationNameImpl(registryName.substring(plugin.getModId().length() + 1));
            }
        }
        return toTranslationNameImpl(registryName);
    }

    public static RGBColor HSLColorToRGBColor(final HSLColor color)
    {
        final var c = (1.0F - Math.abs(2.0F * color.l() - 1.0F)) * color.s();
        final var hh = color.h() / 60.0F;
        final var x = c * (1.0F - Math.abs(hh % 2.0F - 1.0F));

        final float r, g, b;
        if (0.0F <= hh && hh < 1.0F)
        {
            r = c;
            g = x;
            b = 0.0F;
        }
        else if (1.0F <= hh && hh < 2.0F)
        {
            r = x;
            g = c;
            b = 0.0F;
        }
        else if (2.0F <= hh && hh < 3.0F)
        {
            r = 0.0F;
            g = c;
            b = x;
        }
        else if (3.0F <= hh && hh < 4.0F)
        {
            r = 0.0F;
            g = x;
            b = c;
        }
        else if (4.0F <= hh && hh < 5.0F)
        {
            r = x;
            g = 0.0F;
            b = c;
        }
        else
        {
            r = c;
            g = 0.0F;
            b = x;
        }

        final var m = color.l() - c / 2.0F;

        return new RGBColor(r + m, g + m, b + m);
    }

    public static HSLColor RGBColorToHSLColor(final RGBColor color)
    {
        final var epsilon = 10e-9F;

        final var min = Math.min(Math.min(color.r(), color.g()), color.b());
        final var max = Math.max(Math.max(color.r(), color.g()), color.b());

        final var c = max - min;
        final var l = (min + max) / 2.0F;

        float h;

        if (Math.abs(c) < epsilon)
        {
            h = 0.0F;
        }
        else if (max == color.r())
        {
            h = 60.0F * (0.0F + (color.g() - color.b()) / c);
        }
        else if (max == color.g())
        {
            h = 60.0F * (2.0F + (color.b() - color.r()) / c);
        }
        else if (max == color.b())
        {
            h = 60.0F * (4.0F + (color.r() - color.g()) / c);
        }
        else
        {
            throw new RuntimeException("");
        }

        h = ((h % 360.0F) + 360.0F) % 360.0F;

        final float s;
        if (Math.abs(l) < epsilon || Math.abs(1.0 - l) < epsilon)
        {
            s = 0.0F;
        }
        else
        {
            s = c / (1.0F - Math.abs(2.0F * max - c - 1.0F));
        }

        return new HSLColor(h, s, l);
    }

    public static int RGBAtoABGR(final int rgba)
    {
        return (rgba & 0xFF) << 24 | ((rgba >> 8) & 0xFF) << 16 | ((rgba >> 16) & 0xFF) << 8 | (rgba >> 24) & 0xFF;
    }

    public static int[] createColorShades(
        final RGBColor rgb,
        final int n,
        final float min,
        final float max
    )
    {
        Preconditions.checkArgument(0.0F <= min && min <= 1.0F);
        Preconditions.checkArgument(0.0F <= max && max <= 1.0F);
        Preconditions.checkArgument(min < max);

        final var diff = max - min;

        final var shades = new int[n];

        final var hsl = RGBColorToHSLColor(rgb);

        final var increment = diff / (float) n;
        for (int i = 0; i < n; ++i)
        {
            final var shade = HSLColorToRGBColor(new HSLColor(hsl.h(), hsl.s(), min + increment / 2.0F + (float) i * increment));
            shades[i] = 0xFF << 24 | (Math.round(shade.b() * 255.0F) & 0xFF) << 16 | (Math.round(shade.g() * 255.0F) & 0xFF) << 8 | Math.round(shade.r() * 255.0F) & 0xFF;
        }

        return shades;
    }

    public static Map<Integer, Integer> createShadeColorMap(
        final DyeColor dyeColor,
        final int[] colors
    )
    {
        final Map<Integer, Integer> colorMap = new HashMap<>();

        final var textureDiffuseColors = dyeColor.getTextureDiffuseColors();
        final var baseColor = new Util.RGBColor(textureDiffuseColors[0], textureDiffuseColors[1], textureDiffuseColors[2]);


        final int[] shades = switch (dyeColor)
            {
                case WHITE -> Util.createColorShades(new Util.RGBColor(0.94901960784F, 0.94901960784F, 0.94901960784F), colors.length + 1, 0.5F, 1.0F);
                case BLACK -> Util.createColorShades(baseColor, colors.length + 1, 0.0F, 0.5F);
                default -> Util.createColorShades(baseColor, colors.length + 1, 0.3F, 0.8F);
            };

        for (int i = 0; i < colors.length; ++i)
        {
            colorMap.put(RGBAtoABGR(colors[i] << 8 | 0xFF), shades[i + 1]);
        }

        return Collections.unmodifiableMap(colorMap);
    }

    public static final record RGBColor(
        float r,
        float g,
        float b
    )
    {
        public RGBColor
        {
            Preconditions.checkArgument(0.0F <= r && r <= 1.0F);
            Preconditions.checkArgument(0.0F <= g && g <= 1.0F);
            Preconditions.checkArgument(0.0F <= b && b <= 1.0F);
        }
    }

    public static final record HSLColor(
        float h,
        float s,
        float l
    )
    {
        public HSLColor
        {
            Preconditions.checkArgument(0.0F <= h && h <= 360.0F);
            Preconditions.checkArgument(0.0F <= s && s <= 1.0F);
            Preconditions.checkArgument(0.0F <= l && l <= 1.0F);
        }
    }
}
