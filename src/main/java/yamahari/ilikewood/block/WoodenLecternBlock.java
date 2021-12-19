package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LecternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;

public final class WoodenLecternBlock extends LecternBlock implements IWooden {
    private final IWoodType woodType;

    public WoodenLecternBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.LECTERN));
        this.woodType = woodType;
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return WoodenTileEntityTypes.WOODEN_LECTERN.get().create();
    }

    @Nonnull
    @Override
    public ActionResultType use(final BlockState state, @Nonnull final World world, @Nonnull final BlockPos pos,
                                @Nonnull final PlayerEntity player, @Nonnull final Hand hand,
                                @Nonnull final BlockRayTraceResult rayTraceResult) {
        if (state.getValue(HAS_BOOK)) {
            if (!world.isClientSide) {
                this.openScreen(world, pos, player);
            }
            return ActionResultType.SUCCESS;
        } else {
            final ItemStack held = player.getItemInHand(hand);
            if (!held.isEmpty()) {
                return held.getItem().is(ItemTags.LECTERN_BOOKS) && tryPlaceBook(world, pos, state, held)
                       ? ActionResultType.SUCCESS
                       : ActionResultType.PASS;
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
