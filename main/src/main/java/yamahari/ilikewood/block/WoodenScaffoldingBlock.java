package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.WoodenScaffoldingItem;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Random;

public final class WoodenScaffoldingBlock extends ScaffoldingBlock implements IWooden {
    final IWoodType woodType;

    public WoodenScaffoldingBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.SCAFFOLDING));
        this.woodType = woodType;
    }

    public static int getDistance(final BlockGetter reader, final BlockPos pos) {
        final BlockPos.MutableBlockPos mutable =
            (new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ())).move(Direction.DOWN);
        final BlockState state = reader.getBlockState(mutable);
        int distance = 7;
        if (state.getBlock() instanceof ScaffoldingBlock) {
            distance = state.getValue(DISTANCE);
        } else if (state.isFaceSturdy(reader, mutable, Direction.UP)) {
            return 0;
        }

        for (final Direction direction : Direction.Plane.HORIZONTAL) {
            final BlockState translatedState = reader.getBlockState(mutable.set(pos).move(direction));
            if (translatedState.getBlock() instanceof ScaffoldingBlock) {
                distance = Math.min(distance, translatedState.getValue(DISTANCE) + 1);
                if (distance == 1) {
                    break;
                }
            }
        }
        return distance;
    }

    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull final BlockState state, @Nonnull final BlockGetter reader,
                               @Nonnull final BlockPos pos, @Nonnull final CollisionContext context) {
        if (ILikeWoodItemTags.SCAFFOLDINGS.getValues().stream().anyMatch(context::isHoldingItem)) {
            return Shapes.block();
        }

        return state.getValue(BOTTOM) ? UNSTABLE_SHAPE : STABLE_SHAPE;
    }

    @Override
    public void tick(final BlockState state, @Nonnull final ServerLevel world, @Nonnull final BlockPos pos,
                     @Nonnull final Random rand) {
        final int distance = getDistance(world, pos);
        final BlockState blockState =
            state.setValue(DISTANCE, distance).setValue(BOTTOM, this.isBottom(world, pos, distance));
        if (blockState.getValue(DISTANCE) == 7) {
            if (state.getValue(DISTANCE) == 7) {
                world.addFreshEntity(new FallingBlockEntity(world,
                    (double) pos.getX() + 0.5D,
                    pos.getY(),
                    (double) pos.getZ() + 0.5D,
                    blockState.setValue(WATERLOGGED, Boolean.FALSE)));
            } else {
                world.destroyBlock(pos, true);
            }
        } else if (state != blockState) {
            world.setBlock(pos, blockState, 3);
        }
    }

    @Override
    public boolean canSurvive(@Nonnull final BlockState state, @Nonnull final LevelReader world,
                              @Nonnull final BlockPos pos) {
        return getDistance(world, pos) < 7;
    }

    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        final BlockPos blockpos = context.getClickedPos();
        final Level world = context.getLevel();
        final int distance = getDistance(world, blockpos);
        return this
            .defaultBlockState()
            .setValue(WATERLOGGED, world.getFluidState(blockpos).getType() == Fluids.WATER)
            .setValue(DISTANCE, distance).setValue(BOTTOM, this.isBottom(world, blockpos, distance));
    }

    @Override
    public boolean isScaffolding(final BlockState state, final LevelReader world, final BlockPos pos,
                                 final LivingEntity entity) {
        return true;
    }

    @Override
    protected boolean isBottom(@Nonnull final BlockGetter getter, @Nonnull final BlockPos pos, final int distance) {
        return distance > 0 && !(getter.getBlockState(pos.below()).getBlock() instanceof WoodenScaffoldingBlock);
    }

    @Override
    public boolean canBeReplaced(@Nonnull final BlockState blockState, @Nonnull final BlockPlaceContext context) {
        return context.getItemInHand().getItem() instanceof WoodenScaffoldingItem;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
