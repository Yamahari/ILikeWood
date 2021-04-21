package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenCraftingTableBlock extends CraftingTableBlock implements IWooden {
    private final IWoodType woodType;
    private final ITextComponent defaultName;

    public WoodenCraftingTableBlock(final IWoodType type) {
        super(Block.Properties.from(Blocks.CRAFTING_TABLE));
        this.woodType = type;
        this.defaultName = new TranslationTextComponent(StringUtils.joinWith(".",
            "container",
            Constants.MOD_ID,
            Util.toRegistryName(this.getWoodType().getName(), WoodenBlockType.CRAFTING_TABLE.getName())));
    }

    @Override
    public INamedContainerProvider getContainer(@Nonnull final BlockState blockState, @Nonnull final World world,
                                                @Nonnull final BlockPos pos) {
        return new SimpleNamedContainerProvider((windowId, inventory, player) -> new WoodenWorkBenchContainer(windowId,
            inventory,
            IWorldPosCallable.of(world, pos)), this.defaultName);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
