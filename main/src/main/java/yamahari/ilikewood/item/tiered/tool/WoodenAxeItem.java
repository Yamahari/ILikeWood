package yamahari.ilikewood.item.tiered.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenAxeItem extends AxeItem implements IWooden, IWoodenTieredItem {
    private final IWoodType woodType;
    private final IWoodenItemTier woodenItemTier;

    public WoodenAxeItem(final IWoodType woodType, final IWoodenItemTier woodenItemTier) {
        super(Tiers.WOOD,
            0.0F,
            0.0F,
            woodenItemTier.equals(VanillaWoodenItemTiers.NETHERITE)
            ? (new Item.Properties()
                   .tab(CreativeModeTab.TAB_TOOLS)
                   .fireResistant())
            : (new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
        this.woodType = woodType;
        this.woodenItemTier = woodenItemTier;
    }

    @Nonnull
    @Override
    public Tier getTier() {
        return this.getWoodenItemTier();
    }

    @Override
    public float getDestroySpeed(@Nonnull final ItemStack stack, final BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_AXE) ? this.getWoodenItemTier().getSpeed() : 1.0F;
    }

    @Override
    public int getEnchantmentValue() {
        return this.getWoodenItemTier().getEnchantmentValue();
    }

    @Override
    public boolean canBeDepleted() {
        return this.getMaxDamage(null) > 0;
    }

    @Override
    public int getMaxDamage(final ItemStack stack) {
        return this.getWoodenItemTier().getUses();
    }

    @Override
    public boolean isValidRepairItem(@Nonnull final ItemStack toRepair, @Nonnull final ItemStack repair) {
        return this.getWoodenItemTier().getRepairIngredient().test(repair);
    }

    @Override
    public int getBurnTime(final ItemStack itemStack, final @Nullable RecipeType<?> recipeType) {
        return this.getWoodenItemTier().getProperties(this.getTieredItemType()).getBurnTime();
    }

    @Override
    public float getAttackDamage() {
        return this.getWoodenItemTier().getAttackDamageBonus() +
               this.getWoodenItemTier().getProperties(this.getTieredItemType()).getAttackDamage();
    }

    public float getAttackSpeed() {
        return this.getWoodenItemTier().getProperties(this.getTieredItemType()).getAttackSpeed();
    }

    // TODO deprecated, see super method
    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(
        @Nonnull final EquipmentSlot equipmentSlot) {
        final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            attributeModifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_UUID,
                    "Tool modifier",
                    this.getAttackDamage(),
                    AttributeModifier.Operation.ADDITION));
            attributeModifiers.put(Attributes.ATTACK_SPEED,
                new AttributeModifier(BASE_ATTACK_SPEED_UUID,
                    "Tool modifier",
                    this.getAttackSpeed(),
                    AttributeModifier.Operation.ADDITION));
        }
        return attributeModifiers;
    }

    @Override
    public IWoodenItemTier getWoodenItemTier() {
        return this.woodenItemTier;
    }

    @Override
    public WoodenTieredItemType getTieredItemType() {
        return WoodenTieredItemType.AXE;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
