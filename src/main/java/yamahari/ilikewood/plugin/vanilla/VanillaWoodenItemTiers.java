package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodenitemtier.DefaultWoodenItemTier;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.stream.Stream;

public final class VanillaWoodenItemTiers {
    public static final IWoodenItemTier ACACIA = new DefaultWoodenItemTier(VanillaWoodTypes.ACACIA,
        Constants.MOD_ID, Constants.ACACIA, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.ACACIA, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier BAMBOO = new DefaultWoodenItemTier(VanillaWoodTypes.BAMBOO,
        Constants.MOD_ID,
        Constants.BAMBOO,
        () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.BAMBOO, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier BIRCH = new DefaultWoodenItemTier(VanillaWoodTypes.BIRCH,
        Constants.MOD_ID, Constants.BIRCH, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.BIRCH, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier CHERRY = new DefaultWoodenItemTier(VanillaWoodTypes.CHERRY,
        Constants.MOD_ID,
        Constants.CHERRY,
        () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.CHERRY, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier CRIMSON = new DefaultWoodenItemTier(VanillaWoodTypes.CRIMSON,
        Constants.MOD_ID, Constants.CRIMSON, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.CRIMSON, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier DARK_OAK = new DefaultWoodenItemTier(VanillaWoodTypes.DARK_OAK,
        Constants.MOD_ID, Constants.DARK_OAK, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.DARK_OAK, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier JUNGLE = new DefaultWoodenItemTier(VanillaWoodTypes.JUNGLE,
        Constants.MOD_ID, Constants.JUNGLE, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.JUNGLE, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier MANGROVE = new DefaultWoodenItemTier(VanillaWoodTypes.MANGROVE,
        Constants.MOD_ID, Constants.MANGROVE, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.MANGROVE, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier OAK = new DefaultWoodenItemTier(VanillaWoodTypes.OAK,
        Constants.MOD_ID, Constants.OAK, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.OAK, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier SPRUCE = new DefaultWoodenItemTier(VanillaWoodTypes.SPRUCE,
        Constants.MOD_ID, Constants.SPRUCE, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.SPRUCE, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier WARPED = new DefaultWoodenItemTier(VanillaWoodTypes.WARPED,
        Constants.MOD_ID, Constants.WARPED, () -> Ingredient.of(VanillaPlugin.BLOCK_REGISTRY.getObject(VanillaWoodTypes.WARPED, WoodenBlockType.PANELS)));

    public static final IWoodenItemTier STONE = new DefaultWoodenItemTier(Util.DUMMY_WOOD_TYPE, Constants.MOD_ID, Constants.STONE, false, () -> Ingredient.of(Items.COBBLESTONE));

    public static final IWoodenItemTier IRON = new DefaultWoodenItemTier(Util.DUMMY_WOOD_TYPE, Constants.MOD_ID, Constants.IRON, false, () -> Ingredient.of(Items.IRON_INGOT));

    public static final IWoodenItemTier DIAMOND = new DefaultWoodenItemTier(Util.DUMMY_WOOD_TYPE, Constants.MOD_ID, Constants.DIAMOND, false, () -> Ingredient.of(Items.DIAMOND));

    public static final IWoodenItemTier GOLDEN = new DefaultWoodenItemTier(Util.DUMMY_WOOD_TYPE, Constants.MOD_ID, Constants.GOLDEN, false, () -> Ingredient.of(Items.GOLD_INGOT));

    public static final IWoodenItemTier NETHERITE = new DefaultWoodenItemTier(Util.DUMMY_WOOD_TYPE,
        Constants.MOD_ID,
        Constants.NETHERITE,
        false,
        () -> Ingredient.of(Items.NETHERITE_INGOT));


    public static Stream<IWoodenItemTier> get() {
        return Stream.of(ACACIA, BAMBOO, BIRCH, CHERRY, CRIMSON, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED, STONE, IRON, GOLDEN, DIAMOND, NETHERITE);
    }

    private VanillaWoodenItemTiers() {
    }
}
