package yamahari.ilikewood.plugin.biomesoplenty;

import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.IWoodenItemTier;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenItemTier;
import yamahari.ilikewood.util.WoodenObjectType;

public final class BiomesOPlentyWoodenItemTiers {
    public static final IWoodenItemTier CHERRY = new WoodenItemTier(BiomesOPlentyWoodTypes.CHERRY, Constants.BOP_MOD_ID, Constants.CHERRY, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.CHERRY)));
    public static final IWoodenItemTier DEAD = new WoodenItemTier(BiomesOPlentyWoodTypes.DEAD, Constants.BOP_MOD_ID, Constants.DEAD, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.DEAD)));
    public static final IWoodenItemTier FIR = new WoodenItemTier(BiomesOPlentyWoodTypes.FIR, Constants.BOP_MOD_ID, Constants.FIR, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.FIR)));
    public static final IWoodenItemTier HELLBARK = new WoodenItemTier(BiomesOPlentyWoodTypes.HELLBARK, Constants.BOP_MOD_ID, Constants.HELLBARK, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.HELLBARK)));
    public static final IWoodenItemTier JACARANDA = new WoodenItemTier(BiomesOPlentyWoodTypes.JACARANDA, Constants.BOP_MOD_ID, Constants.JACARANDA, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.JACARANDA)));
    public static final IWoodenItemTier MAGIC = new WoodenItemTier(BiomesOPlentyWoodTypes.MAGIC, Constants.BOP_MOD_ID, Constants.MAGIC, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.MAGIC)));
    public static final IWoodenItemTier MAHOGANY = new WoodenItemTier(BiomesOPlentyWoodTypes.MAHOGANY, Constants.BOP_MOD_ID, Constants.MAHOGANY, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.MAHOGANY)));
    public static final IWoodenItemTier PALM = new WoodenItemTier(BiomesOPlentyWoodTypes.PALM, Constants.BOP_MOD_ID, Constants.PALM, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.PALM)));
    public static final IWoodenItemTier REDWOOD = new WoodenItemTier(BiomesOPlentyWoodTypes.REDWOOD, Constants.BOP_MOD_ID, Constants.REDWOOD, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.REDWOOD)));
    public static final IWoodenItemTier UMBRAN = new WoodenItemTier(BiomesOPlentyWoodTypes.UMBRAN, Constants.BOP_MOD_ID, Constants.UMBRAN, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.UMBRAN)));
    public static final IWoodenItemTier WILLOW = new WoodenItemTier(BiomesOPlentyWoodTypes.WILLOW, Constants.BOP_MOD_ID, Constants.WILLOW, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, BiomesOPlentyWoodTypes.WILLOW)));

    private BiomesOPlentyWoodenItemTiers() {
    }
}
