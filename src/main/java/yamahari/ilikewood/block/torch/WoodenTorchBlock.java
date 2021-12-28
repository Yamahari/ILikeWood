package yamahari.ilikewood.block.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Random;

public final class WoodenTorchBlock extends TorchBlock implements IWooden {
    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 11.0D, 10.0D);
    private final IWoodType woodType;

    public WoodenTorchBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME);
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter worldIn,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(@Nonnull final BlockState stateIn, final Level worldIn, BlockPos pos,
                            @Nonnull final Random rand) {
        worldIn.addParticle(ParticleTypes.SMOKE,
            (double) pos.getX() + 0.5D,
            (double) pos.getY() + 0.9D,
            (double) pos.getZ() + 0.5D,
            0.0D,
            0.0D,
            0.0D);
    }
}
