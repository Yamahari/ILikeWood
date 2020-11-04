package yamahari.ilikewood.plugin.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodType;

public final class BiomesOPlentyWoodTypes {
    public static final IWoodType CHERRY = new WoodType(Constants.CHERRY,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.cherry_planks,
            () -> BOPBlocks.cherry_log,
            () -> BOPBlocks.stripped_cherry_log,
            () -> BOPBlocks.cherry_fence,
            () -> BOPBlocks.cherry_slab);
    public static final IWoodType DEAD = new WoodType(Constants.DEAD,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.dead_planks,
            () -> BOPBlocks.dead_log,
            () -> BOPBlocks.stripped_dead_log,
            () -> BOPBlocks.dead_fence,
            () -> BOPBlocks.dead_slab);
    public static final IWoodType FIR = new WoodType(Constants.FIR,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.fir_planks,
            () -> BOPBlocks.fir_log,
            () -> BOPBlocks.stripped_fir_log,
            () -> BOPBlocks.fir_fence,
            () -> BOPBlocks.fir_slab);
    public static final IWoodType HELLBARK = new WoodType(Constants.HELLBARK,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.hellbark_planks,
            () -> BOPBlocks.hellbark_log,
            () -> BOPBlocks.stripped_hellbark_log,
            () -> BOPBlocks.hellbark_fence,
            () -> BOPBlocks.hellbark_slab);
    public static final IWoodType JACARANDA = new WoodType(Constants.JACARANDA,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.jacaranda_planks,
            () -> BOPBlocks.jacaranda_log,
            () -> BOPBlocks.stripped_jacaranda_log,
            () -> BOPBlocks.jacaranda_fence,
            () -> BOPBlocks.jacaranda_slab);
    public static final IWoodType MAGIC = new WoodType(Constants.MAGIC,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.magic_planks,
            () -> BOPBlocks.magic_log,
            () -> BOPBlocks.stripped_magic_log,
            () -> BOPBlocks.magic_fence,
            () -> BOPBlocks.magic_slab);
    public static final IWoodType MAHOGANY = new WoodType(Constants.MAHOGANY,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.mahogany_planks,
            () -> BOPBlocks.mahogany_log,
            () -> BOPBlocks.stripped_mahogany_log,
            () -> BOPBlocks.mahogany_fence,
            () -> BOPBlocks.mahogany_slab);
    public static final IWoodType PALM = new WoodType(Constants.PALM,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.palm_planks,
            () -> BOPBlocks.palm_log,
            () -> BOPBlocks.stripped_palm_log,
            () -> BOPBlocks.palm_fence,
            () -> BOPBlocks.palm_slab);
    public static final IWoodType REDWOOD = new WoodType(Constants.REDWOOD,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.redwood_planks,
            () -> BOPBlocks.redwood_log,
            () -> BOPBlocks.stripped_redwood_log,
            () -> BOPBlocks.redwood_fence,
            () -> BOPBlocks.redwood_slab);
    public static final IWoodType UMBRAN = new WoodType(Constants.UMBRAN,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.umbran_planks,
            () -> BOPBlocks.umbran_log,
            () -> BOPBlocks.stripped_umbran_log,
            () -> BOPBlocks.umbran_fence,
            () -> BOPBlocks.umbran_slab);
    public static final IWoodType WILLOW = new WoodType(Constants.WILLOW,
            AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
            () -> BOPBlocks.willow_planks,
            () -> BOPBlocks.willow_log,
            () -> BOPBlocks.stripped_willow_log,
            () -> BOPBlocks.willow_fence,
            () -> BOPBlocks.willow_slab);

    private BiomesOPlentyWoodTypes() {
    }
}
