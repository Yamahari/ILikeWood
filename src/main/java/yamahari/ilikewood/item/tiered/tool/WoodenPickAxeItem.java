package yamahari.ilikewood.item.tiered.tool;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenPickAxeItem extends PickaxeItem implements IWooden, IWoodenTieredItem {
    private final IWoodType woodType;
    private final IWoodenItemTier woodenItemTier;

    public WoodenPickAxeItem(final IWoodType woodType, final IWoodenItemTier woodenItemTier) {
        super(ItemTier.WOOD,
            0,
            0.f,
            woodenItemTier.equals(VanillaWoodenItemTiers.NETHERITE)
            ? (new Item.Properties()
                   .group(ItemGroup.TOOLS)
                   .isImmuneToFire())
            : (new Item.Properties().group(ItemGroup.TOOLS)));
        this.woodType = woodType;
        this.woodenItemTier = woodenItemTier;
    }

    @Nonnull
    @Override
    public IItemTier getTier() {
        return this.getWoodenItemTier();
    }

    @Override
    public int getHarvestLevel(@Nonnull final ItemStack stack, @Nonnull final ToolType tool,
                               @Nullable final PlayerEntity player, @Nullable final BlockState blockState) {
        return this.getWoodenItemTier().getHarvestLevel();
    }

    @Override
    public float getDestroySpeed(@Nonnull final ItemStack stack, final BlockState state) {
        final Material material = state.getMaterial();
        final boolean flag = material != Material.IRON && material != Material.ANVIL && material != Material.ROCK;
        return this.getToolTypes(stack).stream().anyMatch(state::isToolEffective) || !flag ? this
            .getWoodenItemTier()
            .getEfficiency() : 1.f;
    }

    @Override
    public int getItemEnchantability() {
        return this.getWoodenItemTier().getEnchantability();
    }

    @Override
    public boolean isDamageable() {
        return this.getMaxDamage(null) > 0;
    }

    @Override
    public int getMaxDamage(final ItemStack stack) {
        return this.getWoodenItemTier().getMaxUses();
    }

    @Override
    public boolean getIsRepairable(@Nonnull final ItemStack toRepair, @Nonnull final ItemStack repair) {
        return this.getWoodenItemTier().getRepairMaterial().test(repair);
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodenItemTier().getProperties(this.getTieredItemType()).getBurnTime();
    }

    public float getAttackDamage() {
        return this.getWoodenItemTier().getAttackDamage() +
               this.getWoodenItemTier().getProperties(this.getTieredItemType()).getAttackDamage();
    }

    public float getAttackSpeed() {
        return this.getWoodenItemTier().getProperties(this.getTieredItemType()).getAttackSpeed();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(
        @Nonnull final EquipmentSlotType equipmentSlot) {
        final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            attributeModifiers.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
                    "Tool modifier",
                    this.getAttackDamage(),
                    AttributeModifier.Operation.ADDITION));
            attributeModifiers.put(Attributes.ATTACK_SPEED,
                new AttributeModifier(ATTACK_SPEED_MODIFIER,
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
        return WoodenTieredItemType.PICKAXE;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
