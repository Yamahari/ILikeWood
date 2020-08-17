package yamahari.ilikewood.block.torch;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import java.util.Random;

public final class WoodenTorchBlock extends TorchBlock implements IWooden {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 11.0D, 10.0D);
    private final WoodType woodType;

    public WoodenTorchBlock(final WoodType woodType) {
        super(Block.Properties.from(Blocks.TORCH), ParticleTypes.FLAME);
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        worldIn.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.9D, (double) pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
    }
}
