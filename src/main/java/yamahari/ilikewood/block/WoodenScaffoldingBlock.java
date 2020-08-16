package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.EntitySelectionContext;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import yamahari.ilikewood.data.tag.ItemTags;
import yamahari.ilikewood.item.WoodenScaffoldingItem;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import java.util.Random;

public final class WoodenScaffoldingBlock extends ScaffoldingBlock implements IWooden {
    final WoodType woodType;

    public WoodenScaffoldingBlock(final WoodType woodType) {
        super(Block.Properties.from(Blocks.SCAFFOLDING));
        this.woodType = woodType;
    }

    public static int getDistance(final IBlockReader reader, final BlockPos pos) {
        final BlockPos.Mutable mutable = (new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ())).move(Direction.DOWN);
        final BlockState state = reader.getBlockState(mutable);
        int distance = 7;
        if (state.getBlock() instanceof ScaffoldingBlock) {
            distance = state.get(field_220118_a);
        } else if (state.isSolidSide(reader, mutable, Direction.UP)) {
            return 0;
        }

        for (final Direction direction : Direction.Plane.HORIZONTAL) {
            final BlockState translatedState = reader.getBlockState(mutable.setPos(pos).move(direction));
            if (translatedState.getBlock() instanceof ScaffoldingBlock) {
                distance = Math.min(distance, translatedState.get(field_220118_a) + 1);
                if (distance == 1) {
                    break;
                }
            }
        }
        return distance;
    }

    @Override
    public VoxelShape getShape(final BlockState state, final IBlockReader reader, final BlockPos pos, final ISelectionContext context) {
        final boolean flag;
        if (context instanceof EntitySelectionContext) {
            flag = ((EntitySelectionContext) context).item instanceof WoodenScaffoldingItem;
        } else {
            flag = ItemTags.SCAFFOLDINGS.func_230236_b_().stream().anyMatch(context::hasItem);
        }
        if (flag) {
            return VoxelShapes.fullCube();
        }
        return state.get(field_220120_c) ? field_220122_e : field_220121_d;
    }

    @Override
    public boolean isReplaceable(final BlockState state, final BlockItemUseContext context) {
        return context.getItem().getItem() instanceof WoodenScaffoldingItem;
    }

    @Override
    public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random rand) {
        final int distance = getDistance(world, pos);
        final BlockState blockState = state.with(field_220118_a, distance).with(field_220120_c, this.func_220116_a(world, pos, distance));
        if (blockState.get(field_220118_a) == 7) {
            if (state.get(field_220118_a) == 7) {
                world.addEntity(new FallingBlockEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, blockState.with(WATERLOGGED, Boolean.FALSE)));
            } else {
                world.destroyBlock(pos, true);
            }
        } else if (state != blockState) {
            world.setBlockState(pos, blockState, 3);
        }
    }

    @Override
    public boolean isValidPosition(final BlockState state, final IWorldReader world, final BlockPos pos) {
        return getDistance(world, pos) < 7;
    }

    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final BlockPos blockpos = context.getPos();
        final World world = context.getWorld();
        final int distance = getDistance(world, blockpos);
        return this.getDefaultState()
                .with(WATERLOGGED, world.getFluidState(blockpos).getFluid() == Fluids.WATER)
                .with(field_220118_a, distance)
                .with(field_220120_c, this.func_220116_a(world, blockpos, distance));
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
