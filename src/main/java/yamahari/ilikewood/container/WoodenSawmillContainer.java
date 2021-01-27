package yamahari.ilikewood.container;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import yamahari.ilikewood.data.recipe.WoodenSawmillRecipe;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.WoodenRecipeTypes;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.List;

public class WoodenSawmillContainer extends Container {
    final Slot inputInventorySlot;
    final Slot outputInventorySlot;
    private final IWorldPosCallable worldPosCallable;
    private final IntReferenceHolder selectedRecipe = IntReferenceHolder.single();
    private final World world;
    private final CraftResultInventory inventory = new CraftResultInventory();
    private List<WoodenSawmillRecipe> recipes = Lists.newArrayList();

    private ItemStack itemStackInput = ItemStack.EMPTY;
    private long lastOnTake;
    private Runnable inventoryUpdateListener = () -> {
    };
    public final IInventory inputInventory = new Inventory(1) {
        /**
         * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think
         * it hasn't changed and skip it.
         */
        public void markDirty() {
            super.markDirty();
            WoodenSawmillContainer.this.onCraftMatrixChanged(this);
            WoodenSawmillContainer.this.inventoryUpdateListener.run();
        }
    };

    public WoodenSawmillContainer(int windowIdIn, PlayerInventory playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.DUMMY);
    }

    public WoodenSawmillContainer(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) {
        super(WoodenContainerTypes.WOODEN_SAWMILL.get(), windowIdIn);
        this.worldPosCallable = worldPosCallableIn;
        this.world = playerInventoryIn.player.world;
        this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 20, 33));
        this.outputInventorySlot = this.addSlot(new WoodenSawmillContainerOutputSlot(this.inventory, 1, 143, 33, this));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

        this.trackInt(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<WoodenSawmillRecipe> getRecipeList() {
        return this.recipes;
    }

    public int getRecipeListSize() {
        return this.recipes.size();
    }

    public boolean hasItemInInputSlot() {
        return this.inputInventorySlot.getHasStack() && !this.recipes.isEmpty();
    }

    @Override
    public boolean canInteractWith(@SuppressWarnings("NullableProblems") final PlayerEntity player) {
        return this.worldPosCallable.applyOrElse((world, blockPos) -> {
            final BlockState blockState = world.getBlockState(blockPos);
            return WoodenBlocks.getBlocks(WoodenObjectType.SAWMILL).anyMatch(blockState::isIn)
                    && player.getDistanceSq((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D,
                    (double) blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }


    @Override
    public boolean enchantItem(@SuppressWarnings("NullableProblems") PlayerEntity playerIn, int id) {
        if (this.isIdInRange(id)) {
            this.selectedRecipe.set(id);
            this.updateRecipeResultSlot();
        }

        return true;
    }

    private boolean isIdInRange(int id) {
        return id >= 0 && id < this.recipes.size();
    }

    @Override
    public void onCraftMatrixChanged(@SuppressWarnings("NullableProblems") final IInventory inventoryIn) {
        ItemStack itemstack = this.inputInventorySlot.getStack();
        if (itemstack.getItem() != this.itemStackInput.getItem()) {
            this.itemStackInput = itemstack.copy();
            this.updateAvailableRecipes(inventoryIn, itemstack);
        }
    }

    private void updateAvailableRecipes(IInventory inventoryIn, ItemStack stack) {
        this.recipes.clear();
        this.selectedRecipe.set(-1);
        this.outputInventorySlot.putStack(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes = this.world.getRecipeManager().getRecipes(WoodenRecipeTypes.SAWMILLING, inventoryIn, this.world);
        }
    }

    private void updateRecipeResultSlot() {
        if (!this.recipes.isEmpty() && this.isIdInRange(this.selectedRecipe.get())) {
            final WoodenSawmillRecipe recipe = this.recipes.get(this.selectedRecipe.get());
            this.inventory.setRecipeUsed(recipe);
            this.outputInventorySlot.putStack(recipe.getCraftingResult(this.inputInventory));
        } else {
            this.outputInventorySlot.putStack(ItemStack.EMPTY);
        }

        this.detectAndSendChanges();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ContainerType<?> getType() {
        return WoodenContainerTypes.WOODEN_SAWMILL.get();
    }

    public void setInventoryUpdateListener(Runnable listenerIn) {
        this.inventoryUpdateListener = listenerIn;
    }

    @Override
    public boolean canMergeSlot(@SuppressWarnings("NullableProblems") final ItemStack stack, final Slot slotIn) {
        return slotIn.inventory != this.inventory && super.canMergeSlot(stack, slotIn);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity playerIn, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1) {
                item.onCreated(itemstack1, playerIn.world, playerIn);
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index == 0) {
                if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager().getRecipe(WoodenRecipeTypes.SAWMILLING, new Inventory(itemstack1), this.world).isPresent()) {
                if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 29) {
                if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }

            slot.onSlotChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
            this.detectAndSendChanges();
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(@SuppressWarnings("NullableProblems") final PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.removeStackFromSlot(1);
        this.worldPosCallable.consume((world, blockPos) -> this.clearContainer(playerIn, playerIn.world, this.inputInventory));
    }

    private static final class WoodenSawmillContainerOutputSlot extends Slot {
        private final WoodenSawmillContainer container;

        public WoodenSawmillContainerOutputSlot(final IInventory inventoryIn, final int index, final int xPosition,
                                                final int yPosition, final WoodenSawmillContainer container) {
            super(inventoryIn, index, xPosition, yPosition);
            this.container = container;
        }

        public boolean isItemValid(@SuppressWarnings("NullableProblems") final ItemStack stack) {
            return false;
        }

        @SuppressWarnings("NullableProblems")
        public ItemStack onTake(final PlayerEntity player, final ItemStack stack) {
            stack.onCrafting(player.world, player, stack.getCount());
            this.container.inventory.onCrafting(player);
            ItemStack itemstack = this.container.inputInventorySlot.decrStackSize(1);
            if (!itemstack.isEmpty()) {
                container.updateRecipeResultSlot();
            }

            this.container.worldPosCallable.consume((world, blockPos) -> {
                long gameTime = world.getGameTime();
                if (this.container.lastOnTake != gameTime) {
                    world.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                            SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.container.lastOnTake = gameTime;
                }

            });
            return super.onTake(player, stack);
        }
    }
}
