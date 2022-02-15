package yamahari.ilikewood.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenBlockItem extends BlockItem implements IWooden {
    private final WoodenBlockType blockType;

    public WoodenBlockItem(final WoodenBlockType blockType, final Block block, final Item.Properties properties) {
        super(block, properties);
        this.blockType = blockType;
    }

    @Override
    public IWoodType getWoodType() {
        assert this.getBlock() instanceof IWooden;
        return ((IWooden) this.getBlock()).getWoodType();
    }

    @Override
    public int getBurnTime(final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return this.getWoodType().getProperties(this.blockType).burnTime();
    }
}
