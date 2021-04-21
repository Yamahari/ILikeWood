package yamahari.ilikewood.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WoodenItemFrameEntity extends ItemFrameEntity implements IWooden, IEntityAdditionalSpawnData {
    private static final DataParameter<ItemStack> ITEM =
        EntityDataManager.createKey(WoodenItemFrameEntity.class, DataSerializers.ITEMSTACK);
    private static final DataParameter<Integer> ROTATION =
        EntityDataManager.createKey(WoodenItemFrameEntity.class, DataSerializers.VARINT);
    private final IWoodType woodType;
    private final LazyValue<Item> drop;

    public WoodenItemFrameEntity(final IWoodType woodType, final EntityType<? extends ItemFrameEntity> entityType,
                                 final World world) {
        super(entityType, world);
        this.woodType = woodType;
        this.drop = new LazyValue<>(ILikeWood.ITEM_REGISTRY.getRegistryObject(woodType, WoodenItemType.ITEM_FRAME));
    }

    public WoodenItemFrameEntity(final IWoodType woodType, final EntityType<? extends ItemFrameEntity> entityType,
                                 final World world, final BlockPos blockPos, final Direction direction) {
        super(entityType, world);
        this.woodType = woodType;
        this.hangingPosition = blockPos;
        this.drop = new LazyValue<>(ILikeWood.ITEM_REGISTRY.getRegistryObject(woodType, WoodenItemType.ITEM_FRAME));
        this.updateFacingWithBoundingBox(direction);
    }

    @Override
    protected void registerData() {
        this.getDataManager().register(ITEM, ItemStack.EMPTY);
        this.getDataManager().register(ROTATION, 0);
    }

    @Nonnull
    @Override
    public ItemStack getDisplayedItem() {
        return this.getDataManager().get(ITEM);
    }

    @Override
    public void setDisplayedItemWithUpdate(ItemStack stack, final boolean b) {
        if (!stack.isEmpty()) {
            stack = stack.copy();
            stack.setCount(1);
            stack.setAttachedEntity(this);
        }

        this.getDataManager().set(ITEM, stack);
        if (!stack.isEmpty()) {
            this.playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
        }

        if (b && this.hangingPosition != null) {
            this.world.updateComparatorOutputLevel(this.hangingPosition, Blocks.AIR);
        }
    }

    @Override
    public void notifyDataManagerChange(final DataParameter<?> key) {
        if (key.equals(ITEM)) {
            ItemStack itemstack = this.getDisplayedItem();
            if (!itemstack.isEmpty() && itemstack.getItemFrame() != this) {
                itemstack.setAttachedEntity(this);
            }
        }
    }

    @Override
    public void dropItemOrSelf(@Nullable final Entity entityIn, final boolean dropSelf) {
        if (!this.fixed) {
            ItemStack itemstack = this.getDisplayedItem();
            this.setDisplayedItem(ItemStack.EMPTY);
            if (!this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                if (entityIn == null) {
                    this.removeItem(itemstack);
                }
            } else {
                if (entityIn instanceof PlayerEntity) {
                    PlayerEntity playerentity = (PlayerEntity) entityIn;
                    if (playerentity.abilities.isCreativeMode) {
                        this.removeItem(itemstack);
                        return;
                    }
                }
                if (dropSelf) {
                    this.entityDropItem(this.drop.getValue());

                }
                if (!itemstack.isEmpty()) {
                    itemstack = itemstack.copy();
                    this.removeItem(itemstack);
                    if (this.rand.nextFloat() < this.itemDropChance) {
                        this.entityDropItem(itemstack);
                    }
                }
            }
        }
    }

    @Override
    public int getRotation() {
        return this.getDataManager().get(ROTATION);
    }

    @Override
    public void setRotation(int rotationIn, boolean p_174865_2_) {
        this.getDataManager().set(ROTATION, rotationIn % 8);
        if (p_174865_2_ && this.hangingPosition != null) {
            this.world.updateComparatorOutputLevel(this.hangingPosition, Blocks.AIR);
        }
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(final PacketBuffer buffer) {
        buffer.writeBlockPos(this.getHangingPosition());
        buffer.writeInt(this.facingDirection.getIndex());
    }

    @Override
    public void readSpawnData(final PacketBuffer additionalData) {
        this.hangingPosition = additionalData.readBlockPos();
        this.facingDirection = Direction.byIndex(additionalData.readInt());
        this.updateBoundingBox();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
