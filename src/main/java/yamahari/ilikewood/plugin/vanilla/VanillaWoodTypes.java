package yamahari.ilikewood.plugin.vanilla;

import com.mojang.blaze3d.platform.NativeImage;
import yamahari.ilikewood.registry.woodtype.DefaultWoodType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class VanillaWoodTypes {
    private static final IWoodType.Colors ACACIA_COLORS = new IWoodType.Colors(color(128, 64, 33),
        color(136, 71, 40),
        color(143, 76, 42),
        color(153, 80, 43),
        color(160, 86, 48),
        color(173, 93, 50),
        color(186, 99, 55),
        color(194, 109, 63));
    public static final IWoodType ACACIA = new DefaultWoodType(Constants.MOD_ID, Constants.ACACIA, ACACIA_COLORS);

    private static final IWoodType.Colors BIRCH_COLORS = new IWoodType.Colors(color(145, 126, 85),
        color(158, 139, 97),
        color(165, 148, 103),
        color(174, 159, 118),
        color(184, 168, 117),
        color(200, 183, 122),
        color(215, 193, 133),
        color(215, 203, 141));
    public static final IWoodType BIRCH = new DefaultWoodType(Constants.MOD_ID, Constants.BIRCH, BIRCH_COLORS);

    private static final IWoodType.Colors CRIMSON_COLORS = new IWoodType.Colors(color(63, 30, 45),
        color(68, 33, 49),
        color(75, 39, 55),
        color(92, 48, 66),
        color(106, 52, 75),
        color(116, 55, 80),
        color(126, 58, 86),
        color(134, 62, 90));
    public static final IWoodType CRIMSON = new DefaultWoodType(Constants.MOD_ID, Constants.CRIMSON, CRIMSON_COLORS);

    private static final IWoodType.Colors DARK_OAK_COLORS = new IWoodType.Colors(color(39, 24, 9),
        color(41, 26, 12),
        color(48, 30, 14),
        color(58, 36, 17),
        color(62, 41, 18),
        color(73, 47, 23),
        color(79, 50, 24),
        color(83, 56, 26));
    public static final IWoodType DARK_OAK = new DefaultWoodType(Constants.MOD_ID, Constants.DARK_OAK, DARK_OAK_COLORS);

    private static final IWoodType.Colors JUNGLE_COLORS = new IWoodType.Colors(color(96, 63, 40),
        color(104, 70, 47),
        color(120, 84, 55),
        color(151, 106, 68),
        color(159, 113, 74),
        color(170, 121, 84),
        color(184, 135, 100),
        color(191, 142, 107));
    public static final IWoodType JUNGLE = new DefaultWoodType(Constants.MOD_ID, Constants.JUNGLE, JUNGLE_COLORS);

    private static final IWoodType.Colors OAK_COLORS = new IWoodType.Colors(color(95, 73, 37),
        color(103, 80, 44),
        color(126, 98, 55),
        color(150, 116, 65),
        color(159, 132, 77),
        color(175, 143, 85),
        color(184, 148, 95),
        color(194, 157, 98));
    public static final IWoodType OAK = new DefaultWoodType(Constants.MOD_ID, Constants.OAK, OAK_COLORS);

    private static final IWoodType.Colors SPRUCE_COLORS = new IWoodType.Colors(color(85, 58, 31),
        color(90, 68, 36),
        color(97, 75, 46),
        color(112, 82, 46),
        color(120, 88, 54),
        color(122, 90, 52),
        color(130, 97, 58),
        color(136, 101, 57));
    public static final IWoodType SPRUCE = new DefaultWoodType(Constants.MOD_ID, Constants.SPRUCE, SPRUCE_COLORS);

    private static final IWoodType.Colors WARPED_COLORS = new IWoodType.Colors(color(17, 56, 53),
        color(30, 67, 64),
        color(31, 87, 82),
        color(46, 95, 81),
        color(40, 112, 103),
        color(45, 126, 120),
        color(57, 131, 130),
        color(58, 142, 140));
    public static final IWoodType WARPED = new DefaultWoodType(Constants.MOD_ID, Constants.WARPED, WARPED_COLORS);

    private VanillaWoodTypes() {
    }

    private static int color(int r, int g, int b) {
        return NativeImage.combine(0xFF, b, g, r);
    }

    public static Stream<IWoodType> get() {
        return Stream.of(ACACIA, BIRCH, CRIMSON, DARK_OAK, JUNGLE, OAK, SPRUCE, WARPED);
    }
}
