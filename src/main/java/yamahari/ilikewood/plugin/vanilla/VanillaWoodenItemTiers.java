package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.IWoodenItemTier;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenItemTier;
import yamahari.ilikewood.util.WoodenObjectType;

public final class VanillaWoodenItemTiers {
    public static final IWoodenItemTier ACACIA = new WoodenItemTier(Constants.ACACIA, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.ACACIA)));
    public static final IWoodenItemTier BIRCH = new WoodenItemTier(Constants.BIRCH, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.BIRCH)));
    public static final IWoodenItemTier CRIMSON = new WoodenItemTier(Constants.CRIMSON, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.CRIMSON)));
    public static final IWoodenItemTier DARK_OAK = new WoodenItemTier(Constants.DARK_OAK, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.DARK_OAK)));
    public static final IWoodenItemTier JUNGLE = new WoodenItemTier(Constants.JUNGLE, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.JUNGLE)));
    public static final IWoodenItemTier OAK = new WoodenItemTier(Constants.OAK, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.OAK)));
    public static final IWoodenItemTier SPRUCE = new WoodenItemTier(Constants.SPRUCE, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.SPRUCE)));
    public static final IWoodenItemTier WARPED = new WoodenItemTier(Constants.WARPED, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.WARPED)));
    public static final IWoodenItemTier STONE = new WoodenItemTier(Constants.STONE, false, () -> Ingredient.fromItems(Items.COBBLESTONE));
    public static final IWoodenItemTier IRON = new WoodenItemTier(Constants.IRON, false, () -> Ingredient.fromItems(Items.IRON_INGOT));
    public static final IWoodenItemTier DIAMOND = new WoodenItemTier(Constants.DIAMOND, false, () -> Ingredient.fromItems(Items.DIAMOND));
    public static final IWoodenItemTier GOLDEN = new WoodenItemTier(Constants.GOLDEN, false, () -> Ingredient.fromItems(Items.GOLD_INGOT));
    public static final IWoodenItemTier NETHERITE = new WoodenItemTier(Constants.NETHERITE, false, () -> Ingredient.fromItems(Items.NETHERITE_INGOT));

    private VanillaWoodenItemTiers() {
    }
}
