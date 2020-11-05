package yamahari.ilikewood.plugin.biomesoplenty;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodType;

import java.util.stream.Stream;

public final class BiomesOPlentyWoodTypes {
    public static final IWoodType CHERRY = new WoodType(Constants.BOP_MOD_ID, Constants.CHERRY,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType DEAD = new WoodType(Constants.BOP_MOD_ID, Constants.DEAD,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType FIR = new WoodType(Constants.BOP_MOD_ID, Constants.FIR,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType HELLBARK = new WoodType(Constants.BOP_MOD_ID, Constants.HELLBARK,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType JACARANDA = new WoodType(Constants.BOP_MOD_ID, Constants.JACARANDA,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType MAGIC = new WoodType(Constants.BOP_MOD_ID, Constants.MAGIC,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType MAHOGANY = new WoodType(Constants.BOP_MOD_ID, Constants.MAHOGANY,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType PALM = new WoodType(Constants.BOP_MOD_ID, Constants.PALM,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType REDWOOD = new WoodType(Constants.BOP_MOD_ID, Constants.REDWOOD,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType UMBRAN = new WoodType(Constants.BOP_MOD_ID, Constants.UMBRAN,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType WILLOW = new WoodType(Constants.BOP_MOD_ID, Constants.WILLOW,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));

    public static Stream<IWoodType> get() {
        return Stream.of(BiomesOPlentyWoodTypes.CHERRY, BiomesOPlentyWoodTypes.DEAD, BiomesOPlentyWoodTypes.FIR,
                BiomesOPlentyWoodTypes.HELLBARK, BiomesOPlentyWoodTypes.JACARANDA, BiomesOPlentyWoodTypes.MAGIC,
                BiomesOPlentyWoodTypes.MAHOGANY, BiomesOPlentyWoodTypes.PALM, BiomesOPlentyWoodTypes.REDWOOD,
                BiomesOPlentyWoodTypes.UMBRAN, BiomesOPlentyWoodTypes.WILLOW);
    }

    private BiomesOPlentyWoodTypes() {
    }
}
