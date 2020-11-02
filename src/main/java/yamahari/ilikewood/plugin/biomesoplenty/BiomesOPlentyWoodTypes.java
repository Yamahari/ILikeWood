package yamahari.ilikewood.plugin.biomesoplenty;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.Constants;

public final class BiomesOPlentyWoodTypes {
    public static final IWoodType CHERRY = new BiomesOPlentyWoodType(Constants.CHERRY, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType DEAD = new BiomesOPlentyWoodType(Constants.DEAD, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType FIR = new BiomesOPlentyWoodType(Constants.FIR, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType HELLBARK = new BiomesOPlentyWoodType(Constants.HELLBARK, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType JACARANDA = new BiomesOPlentyWoodType(Constants.JACARANDA, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType MAGIC = new BiomesOPlentyWoodType(Constants.MAGIC, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType MAHOGANY = new BiomesOPlentyWoodType(Constants.MAHOGANY, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType PALM = new BiomesOPlentyWoodType(Constants.PALM, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType REDWOOD = new BiomesOPlentyWoodType(Constants.REDWOOD, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType UMBRAN = new BiomesOPlentyWoodType(Constants.UMBRAN, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final IWoodType WILLOW = new BiomesOPlentyWoodType(Constants.WILLOW, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));

    private BiomesOPlentyWoodTypes() {
    }
}
