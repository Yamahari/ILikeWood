package yamahari.ilikewood.plugin.vanilla;

import yamahari.ilikewood.registry.woodtype.DefaultWoodType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class VanillaWoodTypes {
    public static final IWoodType ACACIA = new DefaultWoodType(Constants.MOD_ID, Constants.ACACIA);
    public static final IWoodType BIRCH = new DefaultWoodType(Constants.MOD_ID, Constants.BIRCH);
    public static final IWoodType CRIMSON = new DefaultWoodType(Constants.MOD_ID, Constants.CRIMSON);
    public static final IWoodType DARK_OAK = new DefaultWoodType(Constants.MOD_ID, Constants.DARK_OAK);
    public static final IWoodType JUNGLE = new DefaultWoodType(Constants.MOD_ID, Constants.JUNGLE);
    public static final IWoodType OAK = new DefaultWoodType(Constants.MOD_ID, Constants.OAK);
    public static final IWoodType SPRUCE = new DefaultWoodType(Constants.MOD_ID, Constants.SPRUCE);
    public static final IWoodType WARPED = new DefaultWoodType(Constants.MOD_ID, Constants.WARPED);

    private VanillaWoodTypes() {
    }

    public static Stream<IWoodType> get() {
        return Stream.of(ACACIA, BIRCH, CRIMSON, DARK_OAK, JUNGLE, OAK, SPRUCE, WARPED);
    }
}
