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
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.recipe.AbstractWoodenSawmillRecipe;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.WoodenRecipeTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.List;

public final class WoodenSawmillContainer extends Container {
    final Slot inputInventorySlot;
    final Slot outputInventorySlot;
    private final IWorldPosCallable worldPosCallable;
    private final IntReferenceHolder selectedRecipe = IntReferenceHolder.standalone();
    private final World world;
    private final CraftResultInventory inventory = new CraftResultInventory();
    private List<AbstractWoodenSawmillRecipe> recipes = Lists.newArrayList();

    private ItemStack itemStackInput = ItemStack.EMPTY;
    private long lastOnTake;
    private Runnable inventoryUpdateListener = () -> {
    };
    public final IInventory inputInventory = new Inventory(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            WoodenSawmillContainer.this.slotsChanged(this);
            WoodenSawmillContainer.this.inventoryUpdateListener.run();
        }
    };

    public WoodenSawmillContainer(final int windowId, final PlayerInventory playerInventory) {
        this(windowId, playerInventory, IWorldPosCallable.NULL);
    }

    public WoodenSawmillContainer(final int windowId, final PlayerInventory playerInventory,
                                  final IWorldPosCallable worldPosCallable) {
        super(WoodenContainerTypes.WOODEN_SAWMILL.get(), windowId);
        this.worldPosCallable = worldPosCallable;
        this.world = playerInventory.player.level;
        this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 20, 33));
        this.outputInventorySlot = this.addSlot(new WoodenSawmillContainerOutputSlot(this.inventory, 1, 143, 33, this));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<AbstractWoodenSawmillRecipe> getRecipeList() {
        return this.recipes;
    }

    public int getRecipeListSize() {
        return this.recipes.size();
    }

    public boolean hasItemInInputSlot() {
        return this.inputInventorySlot.hasItem() && !this.recipes.isEmpty();
    }

    @Override
    public boolean stillValid(@Nonnull final PlayerEntity player) {
        return this.worldPosCallable.evaluate((world, blockPos) -> {
            final BlockState blockState = world.getBlockState(blockPos);
            return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.SAWMILL).anyMatch(blockState::is) &&
                   player.distanceToSqr((double) blockPos.getX() + 0.5D,
                       (double) blockPos.getY() + 0.5D,
                       (double) blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    @Override
    public boolean clickMenuButton(@Nonnull final PlayerEntity playerIn, final int id) {
        if (this.isIdInRange(id)) {
            this.selectedRecipe.set(id);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isIdInRange(final int id) {
        return id >= 0 && id < this.recipes.size();
    }

    @Override
    public void slotsChanged(@Nonnull final IInventory inventoryIn) {
        final ItemStack itemstack = this.inputInventorySlot.getItem();
        if (itemstack.getItem() != this.itemStackInput.getItem()) {
            this.itemStackInput = itemstack.copy();
            this.setupRecipeList(inventoryIn, itemstack);
        }
    }

    private void setupRecipeList(final IInventory inventory, final ItemStack stack) {
        this.recipes.clear();
        this.selectedRecipe.set(-1);
        this.outputInventorySlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes =
                this.world.getRecipeManager().getRecipesFor(WoodenRecipeTypes.SAWMILLING, inventory, this.world);
        }
    }

    private void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.isIdInRange(this.selectedRecipe.get())) {
            final AbstractWoodenSawmillRecipe recipe = this.recipes.get(this.selectedRecipe.get());
            this.inventory.setRecipeUsed(recipe);
            this.outputInventorySlot.set(recipe.assemble(this.inputInventory));
        } else {
            this.outputInventorySlot.set(ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    @Nonnull
    @Override
    public ContainerType<?> getType() {
        return WoodenContainerTypes.WOODEN_SAWMILL.get();
    }

    public void setInventoryUpdateListener(final Runnable listener) {
        this.inventoryUpdateListener = listener;
    }

    @Override
    public boolean canTakeItemForPickAll(@Nonnull final ItemStack stack, final Slot slotIn) {
        return slotIn.container != this.inventory && super.canTakeItemForPickAll(stack, slotIn);
    }

    @Nonnull
    @Override
    public ItemStack quickMoveStack(@Nonnull final PlayerEntity playerIn, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            final ItemStack stackInSlot = slot.getItem();
            final Item item = stackInSlot.getItem();
            itemstack = stackInSlot.copy();
            if (index == 1) {
                item.onCraftedBy(stackInSlot, playerIn.level, playerIn);
                if (!this.moveItemStackTo(stackInSlot, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, itemstack);
            } else if (index == 0) {
                if (!this.moveItemStackTo(stackInSlot, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world
                .getRecipeManager()
                .getRecipeFor(WoodenRecipeTypes.SAWMILLING, new Inventory(stackInSlot), this.world)
                .isPresent()) {
                if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 29) {
                if (!this.moveItemStackTo(stackInSlot, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 38 && !this.moveItemStackTo(stackInSlot, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (stackInSlot.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stackInSlot);
            this.broadcastChanges();
        }

        return itemstack;
    }

    @Override
    public void removed(@Nonnull final PlayerEntity playerIn) {
        super.removed(playerIn);
        this.inventory.removeItemNoUpdate(1);
        this.worldPosCallable.execute((world, blockPos) -> this.clearContainer(playerIn,
            playerIn.level,
            this.inputInventory));
    }

    private static final class WoodenSawmillContainerOutputSlot extends Slot {
        private final WoodenSawmillContainer container;

        public WoodenSawmillContainerOutputSlot(final IInventory inventoryIn, final int index, final int xPosition,
                                                final int yPosition, final WoodenSawmillContainer container) {
            super(inventoryIn, index, xPosition, yPosition);
            this.container = container;
        }

        @Override
        public boolean mayPlace(@Nonnull final ItemStack stack) {
            return false;
        }

        @Override
        public ItemStack onTake(@Nonnull final PlayerEntity player, final ItemStack stack) {
            stack.onCraftedBy(player.level, player, stack.getCount());
            this.container.inventory.awardUsedRecipes(player);
            ItemStack itemstack = this.container.inputInventorySlot.remove(1);
            if (!itemstack.isEmpty()) {
                container.setupResultSlot();
            }

            this.container.worldPosCallable.execute((world, blockPos) -> {
                long gameTime = world.getGameTime();
                if (this.container.lastOnTake != gameTime) {
                    world.playSound(null,
                        blockPos,
                        SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.0F);
                    this.container.lastOnTake = gameTime;
                }

            });
            return super.onTake(player, stack);
        }
    }
}
