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
        EntityDataManager.defineId(WoodenItemFrameEntity.class, DataSerializers.ITEM_STACK);
    private static final DataParameter<Integer> ROTATION =
        EntityDataManager.defineId(WoodenItemFrameEntity.class, DataSerializers.INT);
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
        this.pos = blockPos;
        this.drop = new LazyValue<>(ILikeWood.ITEM_REGISTRY.getRegistryObject(woodType, WoodenItemType.ITEM_FRAME));
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
    public void onSyncedDataUpdated(final DataParameter<?> key) {
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
                if (entityIn instanceof PlayerEntity) {
                    PlayerEntity playerentity = (PlayerEntity) entityIn;
                    if (playerentity.abilities.instabuild) {
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
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(final PacketBuffer buffer) {
        buffer.writeBlockPos(this.pos);
        buffer.writeInt(this.direction.get3DDataValue());
    }

    @Override
    public void readSpawnData(final PacketBuffer additionalData) {
        this.pos = additionalData.readBlockPos();
        this.direction = Direction.from3DDataValue(additionalData.readInt());
        this.recalculateBoundingBox();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
