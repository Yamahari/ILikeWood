package yamahari.ilikewood.plugin.vanilla;

import yamahari.ilikewood.registry.woodtype.DefaultWoodType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class VanillaWoodTypes {
    private static final int[] ACACIA_COLORS = new int[]{
        color(128, 64, 33),
        color(136, 71, 40),
        color(143, 76, 42),
        color(153, 80, 43),
        color(160, 86, 48),
        color(173, 93, 50),
        color(186, 99, 55), color(194, 109, 63)};
    public static final IWoodType ACACIA = new DefaultWoodType(Constants.MOD_ID, Constants.ACACIA, new IWoodType.Colors(ACACIA_COLORS));

    private static final int[] BAMBOO_COLORS = new int[]{
        color(144, 126, 58), color(153, 135, 65), color(187, 168, 76), color(183, 171, 73), color(211, 187, 80), color(217, 194, 94), color(227, 204, 106), color(239, 217, 126)};

    public static final IWoodType BAMBOO = new DefaultWoodType(Constants.MOD_ID, Constants.BAMBOO, new IWoodType.Colors(BAMBOO_COLORS));

    private static final int[] BIRCH_COLORS = new int[]{
        color(145, 126, 85),
        color(158, 139, 97),
        color(165, 148, 103),
        color(174, 159, 118),
        color(184, 168, 117),
        color(200, 183, 122),
        color(215, 193, 133), color(215, 203, 141)};
    public static final IWoodType BIRCH = new DefaultWoodType(Constants.MOD_ID, Constants.BIRCH, new IWoodType.Colors(BIRCH_COLORS));

    private static final int[] CRIMSON_COLORS = new int[]{
        color(63, 30, 45),
        color(68, 33, 49),
        color(75, 39, 55),
        color(92, 48, 66),
        color(106, 52, 75),
        color(116, 55, 80),
        color(126, 58, 86), color(134, 62, 90)};
    public static final IWoodType CRIMSON = new DefaultWoodType(Constants.MOD_ID, Constants.CRIMSON, new IWoodType.Colors(CRIMSON_COLORS));

    private static final int[] DARK_OAK_COLORS = new int[]{
        color(39, 24, 9),
        color(41, 26, 12),
        color(48, 30, 14),
        color(58, 36, 17),
        color(62, 41, 18),
        color(73, 47, 23),
        color(79, 50, 24), color(83, 56, 26)};
    public static final IWoodType DARK_OAK = new DefaultWoodType(Constants.MOD_ID, Constants.DARK_OAK, new IWoodType.Colors(DARK_OAK_COLORS));

    private static final int[] JUNGLE_COLORS = new int[]{
        color(96, 63, 40),
        color(104, 70, 47),
        color(120, 84, 55),
        color(151, 106, 68),
        color(159, 113, 74),
        color(170, 121, 84),
        color(184, 135, 100), color(191, 142, 107)};
    public static final IWoodType JUNGLE = new DefaultWoodType(Constants.MOD_ID, Constants.JUNGLE, new IWoodType.Colors(JUNGLE_COLORS));

    private static final int[] MANGROVE_COLORS = new int[]{
        color(93, 28, 30),
        color(100, 36, 35),
        color(111, 42, 45),
        color(117, 49, 54),
        color(118, 53, 50),
        color(119, 57, 52),
        color(127, 66, 52), color(139, 77, 58)};

    public static final IWoodType MANGROVE = new DefaultWoodType(Constants.MOD_ID, Constants.MANGROVE, new IWoodType.Colors(MANGROVE_COLORS));

    private static final int[] OAK_COLORS = new int[]{
        color(95, 73, 37),
        color(103, 80, 44),
        color(126, 98, 55),
        color(150, 116, 65),
        color(159, 132, 77),
        color(175, 143, 85),
        color(184, 148, 95), color(194, 157, 98)};
    public static final IWoodType OAK = new DefaultWoodType(Constants.MOD_ID, Constants.OAK, new IWoodType.Colors(OAK_COLORS));

    private static final int[] SPRUCE_COLORS = new int[]{
        color(85, 58, 31),
        color(90, 68, 36),
        color(97, 75, 46),
        color(112, 82, 46),
        color(120, 88, 54),
        color(122, 90, 52),
        color(130, 97, 58), color(136, 101, 57)};
    public static final IWoodType SPRUCE = new DefaultWoodType(Constants.MOD_ID, Constants.SPRUCE, new IWoodType.Colors(SPRUCE_COLORS));

    private static final int[] WARPED_COLORS = new int[]{
        color(17, 56, 53),
        color(30, 67, 64),
        color(31, 87, 82),
        color(46, 95, 81),
        color(40, 112, 103),
        color(45, 126, 120),
        color(57, 131, 130), color(58, 142, 140)};
    public static final IWoodType WARPED = new DefaultWoodType(Constants.MOD_ID, Constants.WARPED, new IWoodType.Colors(WARPED_COLORS));

    private VanillaWoodTypes() {
    }

    private static int color(final int r, final int g, final int b) {
        return 255 << 24 | (b & 255) << 16 | (g & 255) << 8 | (r & 255);
    }

    public static Stream<IWoodType> get() {
        return Stream.of(ACACIA, BAMBOO, BIRCH, CRIMSON, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED);
    }
}
