package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;

public final class WoodenCraftingTableBlock extends CraftingTableBlock implements IWooden {
    private final IWoodType woodType;
    private final Component defaultName;

    public WoodenCraftingTableBlock(final IWoodType type) {
        super(Block.Properties.copy(Blocks.CRAFTING_TABLE));
        this.woodType = type;
        this.defaultName = new TranslatableComponent(StringUtils.joinWith(".",
            "container",
            Constants.MOD_ID,
            Util.toRegistryName(this.getWoodType().getName(), WoodenBlockType.CRAFTING_TABLE.getName())));
    }

    @Override
    public MenuProvider getMenuProvider(@Nonnull final BlockState blockState, @Nonnull final Level world,
                                        @Nonnull final BlockPos pos) {
        return new SimpleMenuProvider((windowId, inventory, player) -> new WoodenWorkBenchContainer(windowId,
            inventory,
            ContainerLevelAccess.create(world, pos)), this.defaultName);
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
