package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenItemTier;
import yamahari.ilikewood.util.WoodenObjectType;

public final class VanillaWoodenItemTiers {
    public static final IWoodenItemTier ACACIA = new WoodenItemTier(VanillaWoodTypes.ACACIA, Constants.ACACIA, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.ACACIA)));
    public static final IWoodenItemTier BIRCH = new WoodenItemTier(VanillaWoodTypes.BIRCH, Constants.BIRCH, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.BIRCH)));
    public static final IWoodenItemTier CRIMSON = new WoodenItemTier(VanillaWoodTypes.CRIMSON, Constants.CRIMSON, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.CRIMSON)));
    public static final IWoodenItemTier DARK_OAK = new WoodenItemTier(VanillaWoodTypes.DARK_OAK, Constants.DARK_OAK, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.DARK_OAK)));
    public static final IWoodenItemTier JUNGLE = new WoodenItemTier(VanillaWoodTypes.JUNGLE, Constants.JUNGLE, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.JUNGLE)));
    public static final IWoodenItemTier OAK = new WoodenItemTier(VanillaWoodTypes.OAK, Constants.OAK, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.OAK)));
    public static final IWoodenItemTier SPRUCE = new WoodenItemTier(VanillaWoodTypes.SPRUCE, Constants.SPRUCE, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.SPRUCE)));
    public static final IWoodenItemTier WARPED = new WoodenItemTier(VanillaWoodTypes.WARPED, Constants.WARPED, true, () -> Ingredient.fromItems(WoodenBlocks.getBlock(WoodenObjectType.PANELS, VanillaWoodTypes.WARPED)));
    public static final IWoodenItemTier STONE = new WoodenItemTier(VanillaWoodTypes.DUMMY, Constants.STONE, false, () -> Ingredient.fromItems(Items.COBBLESTONE));
    public static final IWoodenItemTier IRON = new WoodenItemTier(VanillaWoodTypes.DUMMY, Constants.IRON, false, () -> Ingredient.fromItems(Items.IRON_INGOT));
    public static final IWoodenItemTier DIAMOND = new WoodenItemTier(VanillaWoodTypes.DUMMY, Constants.DIAMOND, false, () -> Ingredient.fromItems(Items.DIAMOND));
    public static final IWoodenItemTier GOLDEN = new WoodenItemTier(VanillaWoodTypes.DUMMY, Constants.GOLDEN, false, () -> Ingredient.fromItems(Items.GOLD_INGOT));
    public static final IWoodenItemTier NETHERITE = new WoodenItemTier(VanillaWoodTypes.DUMMY, Constants.NETHERITE, false, () -> Ingredient.fromItems(Items.NETHERITE_INGOT));

    private VanillaWoodenItemTiers() {
    }
}
