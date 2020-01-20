package yamahari.ilikewood.util;

import java.util.EnumSet;

public final class Constants {
    public static final String MOD_ID = "ilikewood";
    public static final String ACACIA = "acacia";
    public static final String BIRCH = "birch";
    public static final String DARK_OAK = "dark_oak";
    public static final String JUNGLE = "jungle";
    public static final String OAK = "oak";
    public static final String SPRUCE = "spruce";
    public static final String STONE = "stone";
    public static final String IRON = "iron";
    public static final String DIAMOND = "diamond";
    public static final String GOLDEN = "golden";
    public static final String WOOD = "wood";
    public static final EnumSet<ItemTiers> IS_WOOD = EnumSet.of(ItemTiers.ACACIA, ItemTiers.BIRCH, ItemTiers.DARK_OAK, ItemTiers.JUNGLE, ItemTiers.OAK, ItemTiers.SPRUCE);

    private Constants() {
        // Nothing to do here!
    }

    public enum ItemTiers {
        ACACIA(Constants.ACACIA),
        BIRCH(Constants.BIRCH),
        DARK_OAK(Constants.DARK_OAK),
        JUNGLE(Constants.JUNGLE),
        OAK(Constants.OAK),
        SPRUCE(Constants.SPRUCE),
        STONE(Constants.STONE),
        IRON(Constants.IRON),
        DIAMOND(Constants.DIAMOND),
        GOLDEN(Constants.GOLDEN);
        private final String name;

        ItemTiers(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
