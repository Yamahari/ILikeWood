package yamahari.ilikewood.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public final class WoodenChairEntity extends Entity {

    public WoodenChairEntity(final EntityType<?> type, final Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            final Level level = this.level();
            final BlockPos pos = this.blockPosition();
            if (this.getPassengers().isEmpty() || level.isEmptyBlock(pos)) {
                this.kill();
                level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).getBlock());
            }
        }
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(@Nonnull final CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(@Nonnull final CompoundTag tag) {
    }

    @Nonnull
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
