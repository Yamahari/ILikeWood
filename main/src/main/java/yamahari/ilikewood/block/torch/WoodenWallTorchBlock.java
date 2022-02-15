package yamahari.ilikewood.block.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Random;

public final class WoodenWallTorchBlock extends WallTorchBlock implements IWooden {
    private final IWoodType woodType;

    public WoodenWallTorchBlock(final IWoodType woodType, final Block.Properties properties) {
        super(properties, ParticleTypes.FLAME);
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public void animateTick(final BlockState stateIn, final Level worldIn, final BlockPos pos,
                            @Nonnull final Random rand) {
        final Direction direction = stateIn.getValue(FACING);
        final Direction opposite = direction.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE,
            (double) pos.getX() + 0.5D + 0.27D * (double) opposite.getStepX(),
            (double) pos.getY() + 0.92D,
            (double) pos.getZ() + 0.5D + 0.27D * (double) opposite.getStepZ(),
            0.0D,
            0.0D,
            0.0D);
    }
}
