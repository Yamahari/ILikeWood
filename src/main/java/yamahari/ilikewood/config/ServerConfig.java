package yamahari.ilikewood.config;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ServerConfig {
    public final Map<String, ForgeConfigSpec.IntValue> HARVEST_LEVEL;
    public final Map<String, ForgeConfigSpec.IntValue> MAX_USES;
    public final Map<String, ForgeConfigSpec.DoubleValue> EFFICIENCY;
    public final Map<String, ForgeConfigSpec.DoubleValue> ATTACK_DAMAGE;
    public final Map<String, ForgeConfigSpec.IntValue> ENCHANTABILITY;
    public final Map<String, ForgeConfigSpec.DoubleValue> ENCHANTING_POWER_BONUS;
    public final Map<String, Map<String, ForgeConfigSpec.IntValue>> BURN_TIME;
    public final Map<String, Map<String, ForgeConfigSpec.IntValue>> TIERED_BURN_TIME;
    public final Map<String, Map<String, ForgeConfigSpec.DoubleValue>> TIERED_ATTACK_DAMAGE;
    public final Map<String, Map<String, ForgeConfigSpec.DoubleValue>> TIERED_ATTACK_SPEED;

    protected ServerConfig(ForgeConfigSpec.Builder builder) {
        builder.push("item_tier");

        builder.push("harvest_level");

        final Map<String, Integer> defaultHarvestLevel = ImmutableMap.of(
                Constants.WOOD, 0,
                Constants.STONE, 1,
                Constants.IRON, 2,
                Constants.DIAMOND, 3,
                Constants.GOLDEN, 0
        );

        HARVEST_LEVEL = buildItemTierConfigValues(
                (path, isWood) -> builder.defineInRange(path, defaultHarvestLevel.get(isWood ? Constants.WOOD : path), 0, Integer.MAX_VALUE));
        builder.pop(); // harvest_level

        builder.push("max_uses");

        final Map<String, Integer> defaultMaxUses = ImmutableMap.of(
                Constants.WOOD, 59,
                Constants.STONE, 131,
                Constants.IRON, 250,
                Constants.DIAMOND, 1561,
                Constants.GOLDEN, 32
        );

        MAX_USES = buildItemTierConfigValues(
                (path, isWood) -> builder.defineInRange(path, defaultMaxUses.get(isWood ? Constants.WOOD : path), 0, Integer.MAX_VALUE));
        builder.pop(); // max_uses

        builder.push("efficiency");

        final Map<String, Double> defaultEfficiency = ImmutableMap.of(
                Constants.WOOD, 2.0D,
                Constants.STONE, 4.0D,
                Constants.IRON, 6.0D,
                Constants.DIAMOND, 8.0D,
                Constants.GOLDEN, 12.0D
        );

        EFFICIENCY = buildItemTierConfigValues(
                (path, isWood) -> builder.defineInRange(path, defaultEfficiency.get(isWood ? Constants.WOOD : path), 0.0D, Float.MAX_VALUE));
        builder.pop(); // efficiency

        builder.push("attack_damage");

        final Map<String, Double> defaultAttackDamage = ImmutableMap.of(
                Constants.WOOD, 0.0D,
                Constants.STONE, 1.0D,
                Constants.IRON, 2.0D,
                Constants.DIAMOND, 3.0D,
                Constants.GOLDEN, 0.0D
        );

        ATTACK_DAMAGE = buildItemTierConfigValues(
                (path, isWood) -> builder.defineInRange(path, defaultAttackDamage.get(isWood ? Constants.WOOD : path), 0.0D, Float.MAX_VALUE));
        builder.pop(); // attack_damage

        builder.push("enchantability");

        final Map<String, Integer> defaultEnchantability = ImmutableMap.of(
                Constants.WOOD, 15,
                Constants.STONE, 5,
                Constants.IRON, 14,
                Constants.DIAMOND, 10,
                Constants.GOLDEN, 22
        );

        ENCHANTABILITY = buildItemTierConfigValues(
                (path, isWood) -> builder.defineInRange(path, defaultEnchantability.get(isWood ? Constants.WOOD : path), 0, Integer.MAX_VALUE));
        builder.pop(); // enchantability

        builder.pop(); // item_tier


        builder.push("block");

        builder.push("enchanting_power_bonus");
        ENCHANTING_POWER_BONUS = buildConfigValues(path -> builder.defineInRange(path, 1.0D, 0.0D, 15.0D));
        builder.pop(); // enchanting_power_bonus

        builder.pop(); // block


        builder.push("item");

        builder.push("burn_time");
        final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.IntValue>> burnTime = new ImmutableMap.Builder<>();
        final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.IntValue>> tieredBurnTime = new ImmutableMap.Builder<>();

        for (final String woodType : Arrays.asList(Constants.ACACIA, Constants.BIRCH, Constants.DARK_OAK, Constants.JUNGLE, Constants.OAK, Constants.SPRUCE)) {
            buildBurnTimes(woodType, builder, burnTime);
            buildTieredBurnTimes(woodType, builder, tieredBurnTime);
        }

        BURN_TIME = burnTime.build();
        TIERED_BURN_TIME = tieredBurnTime.build();

        builder.pop(); // burn_time

        builder.push("tiered");

        builder.push("attack_speed");

        final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.DoubleValue>> attackSpeeds = new ImmutableMap.Builder<>();
        final Map<String, Map<String, Double>> defaultTieredAttackSpeed = ImmutableMap.of(
                Constants.WOOD, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), -3.2D,
                        WoodenTieredObjectType.HOE.toString(), -3.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), -2.8D,
                        WoodenTieredObjectType.SHOVEL.toString(), -3.0D,
                        WoodenTieredObjectType.SWORD.toString(), -2.4D
                ),
                Constants.STONE, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), -3.2D,
                        WoodenTieredObjectType.HOE.toString(), -2.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), -2.8D,
                        WoodenTieredObjectType.SHOVEL.toString(), -3.0D,
                        WoodenTieredObjectType.SWORD.toString(), -2.4D
                ),
                Constants.IRON, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), -3.1D,
                        WoodenTieredObjectType.HOE.toString(), -1.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), -2.8D,
                        WoodenTieredObjectType.SHOVEL.toString(), -3.0D,
                        WoodenTieredObjectType.SWORD.toString(), -2.4D
                ),
                Constants.DIAMOND, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), -3.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), -2.8D,
                        WoodenTieredObjectType.SHOVEL.toString(), -3.0D,
                        WoodenTieredObjectType.SWORD.toString(), -2.4D
                ),
                Constants.GOLDEN, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), -3.0D,
                        WoodenTieredObjectType.HOE.toString(), -3.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), -2.8D,
                        WoodenTieredObjectType.SHOVEL.toString(), -3.0D,
                        WoodenTieredObjectType.SWORD.toString(), -2.4D
                )
        );

        for (final Constants.ItemTiers itemTiers : Constants.ItemTiers.values()) {
            buildTieredConfigValues(itemTiers, builder, attackSpeeds, defaultTieredAttackSpeed);
        }
        TIERED_ATTACK_SPEED = attackSpeeds.build();

        builder.pop(); // attack_speed

        builder.push("attack_damage");

        final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.DoubleValue>> attackDamages = new ImmutableMap.Builder<>();
        final Map<String, Map<String, Double>> defaultTieredAttackDamage = ImmutableMap.of(
                Constants.WOOD, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), 6.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), 1.0D,
                        WoodenTieredObjectType.SHOVEL.toString(), 1.5D,
                        WoodenTieredObjectType.SWORD.toString(), 3.0D
                ),
                Constants.STONE, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), 7.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), 1.0D,
                        WoodenTieredObjectType.SHOVEL.toString(), 1.5D,
                        WoodenTieredObjectType.SWORD.toString(), 3.0D
                ),
                Constants.IRON, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), 6.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), 1.0D,
                        WoodenTieredObjectType.SHOVEL.toString(), 1.5D,
                        WoodenTieredObjectType.SWORD.toString(), 3.0D
                ),
                Constants.DIAMOND, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), 5.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), 1.0D,
                        WoodenTieredObjectType.SHOVEL.toString(), 1.5D,
                        WoodenTieredObjectType.SWORD.toString(), 3.0D
                ),
                Constants.GOLDEN, ImmutableMap.of(
                        WoodenTieredObjectType.AXE.toString(), 6.0D,
                        WoodenTieredObjectType.HOE.toString(), 0.0D,
                        WoodenTieredObjectType.PICKAXE.toString(), 1.0D,
                        WoodenTieredObjectType.SHOVEL.toString(), 1.5D,
                        WoodenTieredObjectType.SWORD.toString(), 3.0D
                )
        );

        for (final Constants.ItemTiers itemTier : Constants.ItemTiers.values()) {
            buildTieredConfigValues(itemTier, builder, attackDamages, defaultTieredAttackDamage);
        }
        TIERED_ATTACK_DAMAGE = attackDamages.build();

        builder.pop(); // attack_damage

        builder.pop(); // tiered

        builder.pop(); // item
    }

    private static <T extends ForgeConfigSpec.ConfigValue> ImmutableMap<String, T> buildItemTierConfigValues(final BiFunction<String, Boolean, T> functor) {
        return Arrays.stream(Constants.ItemTiers.values()).collect(ImmutableMap.toImmutableMap(Objects::toString, itemTier -> functor.apply(itemTier.toString(), Constants.IS_WOOD.contains(itemTier))));
    }

    private static <T extends ForgeConfigSpec.ConfigValue> ImmutableMap<String, T> buildConfigValues(final Function<String, T> functor) {
        return Stream.of(Constants.ACACIA, Constants.BIRCH, Constants.DARK_OAK, Constants.JUNGLE, Constants.OAK, Constants.SPRUCE).collect(ImmutableMap.toImmutableMap(Function.identity(), functor));
    }

    private static void buildBurnTimes(final String woodType, final ForgeConfigSpec.Builder spec, final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.IntValue>> burnTimes) {
        final ImmutableMap.Builder<String, ForgeConfigSpec.IntValue> burnTime = new ImmutableMap.Builder<>();

        spec.push(StringUtils.lowerCase(woodType));

        Stream.of(WoodenObjectType.BARREL, WoodenObjectType.CHEST, WoodenObjectType.LECTERN, WoodenObjectType.PANELS,
                WoodenObjectType.BOOKSHELF, WoodenObjectType.COMPOSTER, WoodenObjectType.WALL, WoodenObjectType.LADDER,
                WoodenObjectType.POST, WoodenObjectType.STRIPPED_POST, WoodenObjectType.CRAFTING_TABLE, WoodenObjectType.STAIRS)
                .map(WoodenObjectType::toString)
                .forEach(path -> burnTime.put(path, spec.defineInRange(path, 300, -1, Integer.MAX_VALUE)));

        Stream.of(WoodenObjectType.STICK, WoodenObjectType.SCAFFOLDING).map(WoodenObjectType::toString)
                .forEach(path -> burnTime.put(path, spec.defineInRange(path, 100, -1, Integer.MAX_VALUE)));

        Stream.of(WoodenObjectType.TORCH, WoodenObjectType.BED, WoodenObjectType.LOG_PILE).map(WoodenObjectType::toString)
                .forEach(path -> burnTime.put(path, spec.defineInRange(path, 400, -1, Integer.MAX_VALUE)));

        Stream.of(WoodenObjectType.SLAB).map(WoodenObjectType::toString)
                .forEach(path -> burnTime.put(path, spec.defineInRange(path, 150, -1, Integer.MAX_VALUE)));

        spec.pop(); // woodType

        burnTimes.put(woodType, burnTime.build());
    }

    private static void buildTieredBurnTimes(final String woodType, final ForgeConfigSpec.Builder spec, final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.IntValue>> tieredBurnTimes) {
        final ImmutableMap.Builder<String, ForgeConfigSpec.IntValue> tieredBurnTime = new ImmutableMap.Builder<>();

        spec.push(StringUtils.lowerCase(woodType));

        Arrays.stream(WoodenTieredObjectType.values()).map(WoodenTieredObjectType::toString)
                .forEach(path -> tieredBurnTime.put(path, spec.defineInRange(path, 200, -1, Integer.MAX_VALUE)));

        spec.pop(); // woodType

        tieredBurnTimes.put(woodType, tieredBurnTime.build());
    }

    private static void buildTieredConfigValues(final Constants.ItemTiers itemTier, final ForgeConfigSpec.Builder spec, final ImmutableMap.Builder<String, Map<String, ForgeConfigSpec.DoubleValue>> builder, final Map<String, Map<String, Double>> defaultConfigs) {
        final ImmutableMap.Builder<String, ForgeConfigSpec.DoubleValue> defaultConfig = new ImmutableMap.Builder<>();
        final String path = itemTier.toString();

        spec.push(StringUtils.lowerCase(path));

        Arrays.stream(WoodenTieredObjectType.values()).map(WoodenTieredObjectType::toString)
                .forEach(type -> defaultConfig.put(type, spec.defineInRange(type.toLowerCase(), defaultConfigs.get(Constants.IS_WOOD.contains(itemTier) ? Constants.WOOD : path).get(type), -Float.MAX_VALUE, Float.MAX_VALUE)));

        spec.pop(); // path

        builder.put(path, defaultConfig.build());
    }
}