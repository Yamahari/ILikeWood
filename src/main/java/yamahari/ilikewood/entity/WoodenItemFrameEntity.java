package yamahari.ilikewood.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WoodenItemFrameEntity extends ItemFrame implements IWooden, IEntityAdditionalSpawnData {
    private static final EntityDataAccessor<ItemStack> ITEM =
        SynchedEntityData.defineId(WoodenItemFrameEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> ROTATION =
        SynchedEntityData.defineId(WoodenItemFrameEntity.class, EntityDataSerializers.INT);
    private final IWoodType woodType;
    private final LazyLoadedValue<Item> drop;

    public WoodenItemFrameEntity(final IWoodType woodType, final EntityType<? extends ItemFrame> entityType,
                                 final Level world) {
        super(entityType, world);
        this.woodType = woodType;
        this.drop =
            new LazyLoadedValue<>(ILikeWood.ITEM_REGISTRY.getRegistryObject(woodType, WoodenItemType.ITEM_FRAME));
    }

    public WoodenItemFrameEntity(final IWoodType woodType, final EntityType<? extends ItemFrame> entityType,
                                 final Level world, final BlockPos blockPos, final Direction direction) {
        super(entityType, world);
        this.woodType = woodType;
        this.pos = blockPos;
        this.drop =
            new LazyLoadedValue<>(ILikeWood.ITEM_REGISTRY.getRegistryObject(woodType, WoodenItemType.ITEM_FRAME));
        this.setDirection(direction);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(ITEM, ItemStack.EMPTY);
        this.getEntityData().define(ROTATION, 0);
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return this.getEntityData().get(ITEM);
    }

    @Override
    public void setItem(ItemStack stack, final boolean b) {
        if (!stack.isEmpty()) {
            stack = stack.copy();
            stack.setCount(1);
            stack.setEntityRepresentation(this);
        }

        this.getEntityData().set(ITEM, stack);
        if (!stack.isEmpty()) {
            this.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
        }

        if (b && this.pos != null) {
            this.level.updateNeighbourForOutputSignal(this.pos, Blocks.AIR);
        }
    }

    @Override
    public void onSyncedDataUpdated(final EntityDataAccessor<?> key) {
        if (key.equals(ITEM)) {
            ItemStack itemstack = this.getItem();
            if (!itemstack.isEmpty() && itemstack.getFrame() != this) {
                itemstack.setEntityRepresentation(this);
            }
        }
    }

    @Override
    public void dropItem(@Nullable final Entity entityIn, final boolean dropSelf) {
        if (!this.fixed) {
            ItemStack itemstack = this.getItem();
            this.setItem(ItemStack.EMPTY);
            if (!this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                if (entityIn == null) {
                    this.removeFramedMap(itemstack);
                }
            } else {
                if (entityIn instanceof Player) {
                    Player playerentity = (Player) entityIn;
                    if (playerentity.getAbilities().instabuild) {
                        this.removeFramedMap(itemstack);
                        return;
                    }
                }
                if (dropSelf) {
                    this.spawnAtLocation(this.drop.get());

                }
                if (!itemstack.isEmpty()) {
                    itemstack = itemstack.copy();
                    this.removeFramedMap(itemstack);
                    if (this.random.nextFloat() < this.dropChance) {
                        this.spawnAtLocation(itemstack);
                    }
                }
            }
        }
    }

    @Override
    public int getRotation() {
        return this.getEntityData().get(ROTATION);
    }

    @Override
    public void setRotation(int rotationIn, boolean p_174865_2_) {
        this.getEntityData().set(ROTATION, rotationIn % 8);
        if (p_174865_2_ && this.pos != null) {
            this.level.updateNeighbourForOutputSignal(this.pos, Blocks.AIR);
        }
    }

    @Nonnull
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(final FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.pos);
        buffer.writeInt(this.direction.get3DDataValue());
    }

    @Override
    public void readSpawnData(final FriendlyByteBuf additionalData) {
        this.pos = additionalData.readBlockPos();
        this.direction = Direction.from3DDataValue(additionalData.readInt());
        this.recalculateBoundingBox();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
