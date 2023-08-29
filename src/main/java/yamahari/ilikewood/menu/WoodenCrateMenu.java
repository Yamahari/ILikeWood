package yamahari.ilikewood.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.block.WoodenCrateBlock;
import yamahari.ilikewood.registry.WoodenMenuTypes;

import javax.annotation.Nonnull;

public class WoodenCrateMenu
    extends AbstractContainerMenu
{
    private final Container container;

    public WoodenCrateMenu(
        final int id,
        final Inventory inventory
    )
    {
        this(id, inventory, new SimpleContainer(15));
    }

    public WoodenCrateMenu(
        final int id,
        final Inventory inventory,
        final Container container
    )
    {
        super(WoodenMenuTypes.WOODEN_CRATE.get(), id);
        checkContainerSize(container, 15);
        this.container = container;
        container.startOpen(inventory.player);

        for (int y = 0; y < 3; ++y)
        {
            for (int x = 0; x < 5; ++x)
            {
                this.addSlot(new WoodenCrateMenuSlot(container, x + y * 5, 44 + x * 18, 18 + y * 18));
            }
        }

        for (int y = 0; y < 3; ++y)
        {
            for (int x = 0; x < 9; ++x)
            {
                this.addSlot(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int x = 0; x < 9; ++x)
        {
            this.addSlot(new Slot(inventory, x, 8 + x * 18, 142));
        }

    }

    public boolean stillValid(@Nonnull final Player player)
    {
        return this.container.stillValid(player);
    }

    @Nonnull
    public ItemStack quickMoveStack(
        @Nonnull final Player player,
        final int i
    )
    {
        ItemStack stack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(i);
        if (slot.hasItem())
        {
            final ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (i < this.container.getContainerSize())
            {
                if (!this.moveItemStackTo(stack1, this.container.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(stack1, 0, this.container.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }

        return stack;
    }

    public void removed(@Nonnull final Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public Container getContainer()
    {
        return this.container;
    }

    private static class WoodenCrateMenuSlot
        extends Slot
    {
        private WoodenCrateMenuSlot(
            final Container container,
            final int slot,
            final int x,
            final int y
        )
        {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(final ItemStack stack)
        {
            return !(Block.byItem(stack.getItem()) instanceof WoodenCrateBlock) && stack.getItem().canFitInsideContainerItems();
        }
    }
}
