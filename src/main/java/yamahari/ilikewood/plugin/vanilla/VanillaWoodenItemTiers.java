package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodenitemtier.DefaultWoodenItemTier;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.Constants;

public final class VanillaWoodenItemTiers {
    public static final IWoodenItemTier ACACIA = new DefaultWoodenItemTier(VanillaWoodTypes.ACACIA,
        Constants.MOD_ID,
        Constants.ACACIA,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.ACACIA,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier BIRCH = new DefaultWoodenItemTier(VanillaWoodTypes.BIRCH,
        Constants.MOD_ID,
        Constants.BIRCH,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.BIRCH,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier CRIMSON = new DefaultWoodenItemTier(VanillaWoodTypes.CRIMSON,
        Constants.MOD_ID,
        Constants.CRIMSON,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.CRIMSON,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier DARK_OAK = new DefaultWoodenItemTier(VanillaWoodTypes.DARK_OAK,
        Constants.MOD_ID,
        Constants.DARK_OAK,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.DARK_OAK,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier JUNGLE = new DefaultWoodenItemTier(VanillaWoodTypes.JUNGLE,
        Constants.MOD_ID,
        Constants.JUNGLE,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.JUNGLE,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier OAK = new DefaultWoodenItemTier(VanillaWoodTypes.OAK,
        Constants.MOD_ID,
        Constants.OAK,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.OAK,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier SPRUCE = new DefaultWoodenItemTier(VanillaWoodTypes.SPRUCE,
        Constants.MOD_ID,
        Constants.SPRUCE,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.SPRUCE,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier WARPED = new DefaultWoodenItemTier(VanillaWoodTypes.WARPED,
        Constants.MOD_ID,
        Constants.WARPED,
        () -> Ingredient.fromItems(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.WARPED,
            WoodenBlockType.PANELS)));

    public static final IWoodenItemTier STONE = new DefaultWoodenItemTier(VanillaWoodTypes.DUMMY,
        Constants.MOD_ID,
        Constants.STONE,
        false,
        () -> Ingredient.fromItems(Items.COBBLESTONE));

    public static final IWoodenItemTier IRON = new DefaultWoodenItemTier(VanillaWoodTypes.DUMMY,
        Constants.MOD_ID,
        Constants.IRON,
        false,
        () -> Ingredient.fromItems(Items.IRON_INGOT));

    public static final IWoodenItemTier DIAMOND = new DefaultWoodenItemTier(VanillaWoodTypes.DUMMY,
        Constants.MOD_ID,
        Constants.DIAMOND,
        false,
        () -> Ingredient.fromItems(Items.DIAMOND));

    public static final IWoodenItemTier GOLDEN = new DefaultWoodenItemTier(VanillaWoodTypes.DUMMY,
        Constants.MOD_ID,
        Constants.GOLDEN,
        false,
        () -> Ingredient.fromItems(Items.GOLD_INGOT));

    public static final IWoodenItemTier NETHERITE = new DefaultWoodenItemTier(VanillaWoodTypes.DUMMY,
        Constants.MOD_ID,
        Constants.NETHERITE,
        false,
        () -> Ingredient.fromItems(Items.NETHERITE_INGOT));

    private VanillaWoodenItemTiers() {
    }
}
