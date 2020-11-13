package yamahari.ilikewood.plugin.vanilla;

import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.DummyWoodType;
import yamahari.ilikewood.util.WoodType;

import java.util.stream.Stream;

public final class VanillaWoodTypes {
    public static final IWoodType DUMMY = new DummyWoodType();
    public static final IWoodType ACACIA = new WoodType(Constants.ACACIA);
    public static final IWoodType BIRCH = new WoodType(Constants.BIRCH);
    public static final IWoodType CRIMSON = new WoodType(Constants.CRIMSON);
    public static final IWoodType DARK_OAK = new WoodType(Constants.DARK_OAK);
    public static final IWoodType JUNGLE = new WoodType(Constants.JUNGLE);
    public static final IWoodType OAK = new WoodType(Constants.OAK);
    public static final IWoodType SPRUCE = new WoodType(Constants.SPRUCE);
    public static final IWoodType WARPED = new WoodType(Constants.WARPED);

    public static Stream<IWoodType> get() {
        return Stream.of(ACACIA, BIRCH, CRIMSON, DARK_OAK, JUNGLE, OAK, SPRUCE, WARPED);
    }

    private VanillaWoodTypes() {
    }
}
