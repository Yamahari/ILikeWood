package yamahari.ilikewood.client.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenCrateBlock;
import yamahari.ilikewood.menu.WoodenCrateMenu;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class WoodenCrateBlockEntity
    extends RandomizableContainerBlockEntity
    implements WorldlyContainer
{
    private static final int[] SLOTS = IntStream.range(0, 15).toArray();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
    {
        @Override
        protected void onOpen(
            @Nonnull final Level level,
            @Nonnull final BlockPos pos,
            @Nonnull final BlockState state
        )
        {
            WoodenCrateBlockEntity.this.playSound(state, SoundEvents.BARREL_OPEN);
        }

        @Override
        protected void onClose(
            @Nonnull final Level level,
            @Nonnull final BlockPos pos,
            @Nonnull final BlockState state
        )
        {
            WoodenCrateBlockEntity.this.playSound(state, SoundEvents.BARREL_CLOSE);
        }

        @Override
        protected void openerCountChanged(
            @Nonnull final Level level,
            @Nonnull final BlockPos blockPos,
            @Nonnull final BlockState state,
            final int count,
            final int openCount
        )
        {
        }

        @Override
        protected boolean isOwnContainer(@Nonnull final Player player)
        {
            if (player.containerMenu instanceof WoodenCrateMenu)
            {
                Container container = ((WoodenCrateMenu) player.containerMenu).getContainer();
                return container == WoodenCrateBlockEntity.this;
            }
            else
            {
                return false;
            }
        }
    };
    private NonNullList<ItemStack> stacks = NonNullList.withSize(15, ItemStack.EMPTY);

    public WoodenCrateBlockEntity(
        final BlockPos pos,
        final BlockState state
    )
    {
        super(WoodenBlockEntityTypes.WOODEN_CRATE.get(), pos, state);
    }

    @Override
    public int getContainerSize()
    {
        return this.stacks.size();
    }

    @Nonnull
    @Override
    protected Component getDefaultName()
    {
        final var block = this.getBlockState().getBlock();
        final String path;
        if (block instanceof WoodenCrateBlock)
        {
            path = ForgeRegistries.BLOCKS.getKey(block).getPath();
        }
        else
        {
            path = ForgeRegistries.BLOCKS.getKey(ILikeWood.BLOCK_REGISTRY.getObject(VanillaWoodTypes.OAK, WoodenBlockType.CRATE)).getPath();
        }

        return Component.translatable(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
    }

    @Override
    public void load(@Nonnull final CompoundTag compoundTag)
    {
        super.load(compoundTag);
        this.loadFromTag(compoundTag);
    }

    @Override
    protected void saveAdditional(@Nonnull final CompoundTag compoundTag)
    {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag))
        {
            ContainerHelper.saveAllItems(compoundTag, this.stacks, false);
        }
    }

    public void loadFromTag(final CompoundTag compoundTag)
    {
        this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag) && compoundTag.contains("Items", 9))
        {
            ContainerHelper.loadAllItems(compoundTag, this.stacks);
        }
    }

    @Nonnull
    @Override
    protected NonNullList<ItemStack> getItems()
    {
        return this.stacks;
    }

    @Override
    protected void setItems(@Nonnull final NonNullList<ItemStack> stacks)
    {
        this.stacks = stacks;
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull final Direction direction)
    {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(
        final int slot,
        final ItemStack stack,
        @Nullable final Direction direction
    )
    {
        return !(Block.byItem(stack.getItem()) instanceof WoodenCrateBlock) && stack.getItem().canFitInsideContainerItems();
    }

    @Override
    public boolean canTakeItemThroughFace(
        final int slot,
        @Nonnull final ItemStack stack,
        @Nonnull final Direction direction
    )
    {
        return true;
    }

    @Nonnull
    @Override
    protected AbstractContainerMenu createMenu(
        final int id,
        @Nonnull final Inventory inventory
    )
    {
        return new WoodenCrateMenu(id, inventory, this);
    }

    @Override
    public void startOpen(@Nonnull final Player player)
    {
        if (!this.remove && !player.isSpectator())
        {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@Nonnull final Player player)
    {
        if (!this.remove && !player.isSpectator())
        {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Nonnull
    @Override
    protected net.minecraftforge.items.IItemHandler createUnSidedHandler()
    {
        return new net.minecraftforge.items.wrapper.SidedInvWrapper(this, Direction.UP);
    }

    private void playSound(
        final BlockState state,
        final SoundEvent soundEvent
    )
    {
        Vec3i vec3i = state.getValue(BarrelBlock.FACING).getNormal();
        double d0 = (double) this.worldPosition.getX() + 0.5D + (double) vec3i.getX() / 2.0D;
        double d1 = (double) this.worldPosition.getY() + 0.5D + (double) vec3i.getY() / 2.0D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D + (double) vec3i.getZ() / 2.0D;
        this.level.playSound(null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
