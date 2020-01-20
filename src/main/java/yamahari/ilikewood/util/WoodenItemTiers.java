package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.config.Config;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class WoodenItemTiers {
    private static final Supplier<Integer> defaultBurnTime = () -> -1;

    public static final WoodenItemTier ACACIA = createWoodenItemTier(Constants.ItemTiers.ACACIA, () -> Ingredient.fromItems(Items.ACACIA_PLANKS));
    public static final WoodenItemTier BIRCH = createWoodenItemTier(Constants.ItemTiers.BIRCH, () -> Ingredient.fromItems(Items.BIRCH_PLANKS));
    public static final WoodenItemTier DARK_OAK = createWoodenItemTier(Constants.ItemTiers.DARK_OAK, () -> Ingredient.fromItems(Items.DARK_OAK_PLANKS));
    public static final WoodenItemTier JUNGLE = createWoodenItemTier(Constants.ItemTiers.JUNGLE, () -> Ingredient.fromItems(Items.JUNGLE_PLANKS));
    public static final WoodenItemTier OAK = createWoodenItemTier(Constants.ItemTiers.OAK, () -> Ingredient.fromItems(Items.OAK_PLANKS));
    public static final WoodenItemTier SPRUCE = createWoodenItemTier(Constants.ItemTiers.SPRUCE, () -> Ingredient.fromItems(Items.SPRUCE_PLANKS));
    public static final WoodenItemTier STONE = createWoodenItemTier(Constants.ItemTiers.STONE, () -> Ingredient.fromItems(Items.COBBLESTONE));
    public static final WoodenItemTier IRON = createWoodenItemTier(Constants.ItemTiers.IRON, () -> Ingredient.fromItems(Items.IRON_INGOT));
    public static final WoodenItemTier DIAMOND = createWoodenItemTier(Constants.ItemTiers.DIAMOND, () -> Ingredient.fromItems(Items.DIAMOND));
    public static final WoodenItemTier GOLDEN = createWoodenItemTier(Constants.ItemTiers.GOLDEN, () -> Ingredient.fromItems(Items.GOLD_INGOT));

    private WoodenItemTiers() {
        // Nothing to do here!
    }

    public static Stream<WoodenItemTier> get() {
        return Stream.of(ACACIA, BIRCH, DARK_OAK, JUNGLE, OAK, SPRUCE, STONE, IRON, DIAMOND, GOLDEN);
    }

    private static WoodenItemTier createWoodenItemTier(final Constants.ItemTiers itemTier, final Supplier<Ingredient> repairMaterial) {
        final boolean isWood = Constants.IS_WOOD.contains(itemTier);
        final String path = itemTier.toString();
        return new WoodenItemTier(
                path,
                isWood,
                Config.SERVER_CONFIG.HARVEST_LEVEL.get(path)::get,
                Config.SERVER_CONFIG.MAX_USES.get(path)::get,
                Config.SERVER_CONFIG.EFFICIENCY.get(path)::get,
                Config.SERVER_CONFIG.ATTACK_DAMAGE.get(path)::get,
                Config.SERVER_CONFIG.ENCHANTABILITY.get(path)::get,
                repairMaterial,
                Arrays.stream(WoodenTieredObjectType.values()).map(WoodenTieredObjectType::toString)
                        .collect(ImmutableMap.toImmutableMap(Function.identity(), tieredItemType -> new WoodenItemTier.Properties(
                                Config.SERVER_CONFIG.TIERED_ATTACK_SPEED.get(path).get(tieredItemType)::get,
                                Config.SERVER_CONFIG.TIERED_ATTACK_DAMAGE.get(path).get(tieredItemType)::get,
                                isWood ? Config.SERVER_CONFIG.TIERED_BURN_TIME.get(path).get(tieredItemType)::get : defaultBurnTime
                        )))
        );
    }
}
