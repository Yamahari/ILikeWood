package yamahari.ilikewood.client.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.block.WoodenCampfireBlock;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;

import javax.annotation.Nonnull;


public final class WoodenCampfireBlockEntity
    extends CampfireBlockEntity
{
    public WoodenCampfireBlockEntity(
        final BlockPos blockPos,
        final BlockState blockState
    )
    {
        super(blockPos, blockState);
    }

    public static void particleTick2(
        final Level level,
        final BlockPos pos,
        final BlockState state,
        final WoodenCampfireBlockEntity blockEntity
    )
    {
        final RandomSource randomsource = level.random;
        if (randomsource.nextFloat() < 0.11F)
        {
            for (int i = 0; i < randomsource.nextInt(2) + 2; ++i)
            {
                WoodenCampfireBlock.makeParticles(level, pos, state.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }

        int l = state.getValue(CampfireBlock.FACING).get2DDataValue();

        for (int j = 0; j < blockEntity.items.size(); ++j)
        {
            if (!blockEntity.items.get(j).isEmpty() && randomsource.nextFloat() < 0.2F)
            {
                Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                float f = 0.3125F;
                double d0 =
                    (double) pos.getX() + 0.5D - (double) ((float) direction.getStepX() * 0.3125F) + (double) ((float) direction.getClockWise().getStepX() * 0.3125F);
                double d1 = (double) pos.getY() + 0.5D;
                double d2 =
                    (double) pos.getZ() + 0.5D - (double) ((float) direction.getStepZ() * 0.3125F) + (double) ((float) direction.getClockWise().getStepZ() * 0.3125F);

                for (int k = 0; k < 4; ++k)
                {
                    level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
                }
            }
        }

    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType()
    {
        return WoodenBlockEntityTypes.WOODEN_CAMPFIRE.get();
    }
}
