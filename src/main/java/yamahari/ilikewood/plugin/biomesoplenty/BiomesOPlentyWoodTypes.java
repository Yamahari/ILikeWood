package yamahari.ilikewood.plugin.biomesoplenty;

import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodType;

import java.util.stream.Stream;

public final class BiomesOPlentyWoodTypes {
    public static final IWoodType CHERRY = new WoodType(Constants.BOP_MOD_ID, Constants.CHERRY);
    public static final IWoodType DEAD = new WoodType(Constants.BOP_MOD_ID, Constants.DEAD);
    public static final IWoodType FIR = new WoodType(Constants.BOP_MOD_ID, Constants.FIR);
    public static final IWoodType HELLBARK = new WoodType(Constants.BOP_MOD_ID, Constants.HELLBARK);
    public static final IWoodType JACARANDA = new WoodType(Constants.BOP_MOD_ID, Constants.JACARANDA);
    public static final IWoodType MAGIC = new WoodType(Constants.BOP_MOD_ID, Constants.MAGIC);
    public static final IWoodType MAHOGANY = new WoodType(Constants.BOP_MOD_ID, Constants.MAHOGANY);
    public static final IWoodType PALM = new WoodType(Constants.BOP_MOD_ID, Constants.PALM);
    public static final IWoodType REDWOOD = new WoodType(Constants.BOP_MOD_ID, Constants.REDWOOD);
    public static final IWoodType UMBRAN = new WoodType(Constants.BOP_MOD_ID, Constants.UMBRAN);
    public static final IWoodType WILLOW = new WoodType(Constants.BOP_MOD_ID, Constants.WILLOW);

    public static Stream<IWoodType> get() {
        return Stream.of(BiomesOPlentyWoodTypes.CHERRY, BiomesOPlentyWoodTypes.DEAD, BiomesOPlentyWoodTypes.FIR,
                BiomesOPlentyWoodTypes.HELLBARK, BiomesOPlentyWoodTypes.JACARANDA, BiomesOPlentyWoodTypes.MAGIC,
                BiomesOPlentyWoodTypes.MAHOGANY, BiomesOPlentyWoodTypes.PALM, BiomesOPlentyWoodTypes.REDWOOD,
                BiomesOPlentyWoodTypes.UMBRAN, BiomesOPlentyWoodTypes.WILLOW);
    }

    private BiomesOPlentyWoodTypes() {
    }
}
