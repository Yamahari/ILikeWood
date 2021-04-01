package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class WoodenItemTier implements IWoodenItemTier {
    private static final Map<String, Integer> DEFAULT_HARVEST_LEVEL = new ImmutableMap.Builder<String, Integer>()
        .put(Constants.WOOD, 0)
        .put(Constants.STONE, 1)
        .put(Constants.IRON, 2)
        .put(Constants.DIAMOND, 3)
        .put(Constants.GOLDEN, 0)
        .put(Constants.NETHERITE, 4)
        .build();

    private static final Map<String, Integer> DEFAULT_MAX_USES = new ImmutableMap.Builder<String, Integer>()
        .put(Constants.WOOD, 59)
        .put(Constants.STONE, 131)
        .put(Constants.IRON, 250)
        .put(Constants.DIAMOND, 1561)
        .put(Constants.GOLDEN, 32)
        .put(Constants.NETHERITE, 2031)
        .build();

    private static final Map<String, Float> DEFAULT_EFFICIENCY = new ImmutableMap.Builder<String, Float>()
        .put(Constants.WOOD, 2.0F)
        .put(Constants.STONE, 4.0F)
        .put(Constants.IRON, 6.0F)
        .put(Constants.DIAMOND, 8.0F)
        .put(Constants.GOLDEN, 12.0F)
        .put(Constants.NETHERITE, 9.0F)
        .build();

    private static final Map<String, Float> DEFAULT_ATTACK_DAMAGE = new ImmutableMap.Builder<String, Float>()
        .put(Constants.WOOD, 0.0F)
        .put(Constants.STONE, 1.0F)
        .put(Constants.IRON, 2.0F)
        .put(Constants.DIAMOND, 3.0F)
        .put(Constants.GOLDEN, 0.0F)
        .put(Constants.NETHERITE, 4.0F)
        .build();

    private static final Map<String, Integer> DEFAULT_ENCHANTABILITY = new ImmutableMap.Builder<String, Integer>()
        .put(Constants.WOOD, 15)
        .put(Constants.STONE, 5)
        .put(Constants.IRON, 14)
        .put(Constants.DIAMOND, 10)
        .put(Constants.GOLDEN, 22)
        .put(Constants.NETHERITE, 15)
        .build();

    private static final Map<String, Map<String, Float>> DEFAULT_TIERED_ATTACK_SPEED =
        new ImmutableMap.Builder<String, Map<String, Float>>()
            .put(Constants.WOOD,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.2F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .put(Constants.STONE,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.2F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -2.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .put(Constants.IRON,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.1F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -1.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .put(Constants.DIAMOND,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    0.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .put(Constants.GOLDEN,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .put(Constants.NETHERITE,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    0.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    -2.8F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    -2.4F))
            .build();

    private static final Map<String, Map<String, Float>> DEFAULT_TIERED_ATTACK_DAMAGE =
        new ImmutableMap.Builder<String, Map<String, Float>>()
            .put(Constants.WOOD,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    6.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    0.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .put(Constants.STONE,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    7.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -1.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .put(Constants.IRON,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    6.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -2.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .put(Constants.DIAMOND,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    5.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -3.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .put(Constants.GOLDEN,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    6.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    0.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .put(Constants.NETHERITE,
                ImmutableMap.of(WoodenTieredObjectTypes.AXE.getName(),
                    5.0F,
                    WoodenTieredObjectTypes.HOE.getName(),
                    -4.0F,
                    WoodenTieredObjectTypes.PICKAXE.getName(),
                    1.0F,
                    WoodenTieredObjectTypes.SHOVEL.getName(),
                    1.5F,
                    WoodenTieredObjectTypes.SWORD.getName(),
                    3.0F))
            .build();

    private final IWoodType woodType;
    private final String modId;
    private final String name;
    private final boolean isWood;
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;
    private final Map<WoodenTieredObjectType, Properties> properties;

    public WoodenItemTier(final IWoodType woodType, final String name, final boolean isWood,
                          final Supplier<Ingredient> repairMaterial) {
        this(woodType, Constants.MOD_ID, name, isWood, repairMaterial);
    }

    // TODO remove config and hardcode values
    public WoodenItemTier(final IWoodType woodType, final String modId, final String name, final boolean isWood,
                          final Supplier<Ingredient> repairMaterial) {
        this(woodType,
            modId,
            name,
            isWood,
            DEFAULT_HARVEST_LEVEL.get(isWood ? Constants.WOOD : name),
            DEFAULT_MAX_USES.get(isWood ? Constants.WOOD : name),
            DEFAULT_EFFICIENCY.get(isWood ? Constants.WOOD : name),
            DEFAULT_ATTACK_DAMAGE.get(isWood ? Constants.WOOD : name),
            DEFAULT_ENCHANTABILITY.get(isWood ? Constants.WOOD : name),
            repairMaterial);
    }

    public WoodenItemTier(final IWoodType woodType, final String modId, final String name, final boolean isWood,
                          final int harvestLevel, final int maxUses, final float efficiency, final float attackDamage,
                          final int enchantability, final Supplier<Ingredient> repairMaterial) {
        this.woodType = woodType;
        this.modId = modId;
        this.name = name;
        this.isWood = isWood;
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);

        final Map<WoodenTieredObjectType, Properties> properties = new HashMap<>();
        WoodenTieredObjectTypes.get().forEach(tieredObjectType -> {
            final String type = tieredObjectType.getName();
            properties.put(tieredObjectType,
                new WoodenItemTier.Properties(DEFAULT_TIERED_ATTACK_SPEED.get(isWood ? Constants.WOOD : name).get(type),
                    DEFAULT_TIERED_ATTACK_DAMAGE.get(isWood ? Constants.WOOD : name).get(type),
                    isWood ? 200 : -1));
        });
        this.properties = Collections.unmodifiableMap(properties);

    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public String getModId() {
        return this.modId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isWood() {
        return isWood;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }

    @Override
    public Properties getProperties(final WoodenTieredObjectType woodenTieredObjectType) {
        assert this.properties.containsKey(woodenTieredObjectType);
        return properties.get(woodenTieredObjectType);
    }
}
