package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenCraftingTableBlock extends CraftingTableBlock implements IWooden {
    private final WoodType woodType;

    public WoodenCraftingTableBlock(final WoodType type) {
        super(Block.Properties.from(Blocks.CRAFTING_TABLE));
        this.woodType = type;
        this.setRegistryName(Util.toRegistryName(this.getWoodType().toString(), WoodenObjectType.CRAFTING_TABLE.toString()));
    }

    @Override
    public INamedContainerProvider getContainer(final BlockState blockState, final World world, final BlockPos pos) {
        // TODO
        return null;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
