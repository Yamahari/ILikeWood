package yamahari.ilikewood.block.torch;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import java.util.Random;

public final class WoodenWallTorchBlock extends WallTorchBlock implements IWooden {
    private final WoodType woodType;

    public WoodenWallTorchBlock(final WoodType woodType, final Block.Properties properties) {
        super(properties, ParticleTypes.FLAME);
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        final Direction direction = stateIn.get(HORIZONTAL_FACING);
        final Direction opposite = direction.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.5D + 0.27D * (double) opposite.getXOffset(), (double) pos.getY() + 0.92D, (double) pos.getZ() + 0.5D + 0.27D * (double) opposite.getZOffset(), 0.0D, 0.0D, 0.0D);
    }
}
