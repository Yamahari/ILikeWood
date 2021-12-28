package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class WoodenLecternBlock extends LecternBlock implements IWooden {
    private final IWoodType woodType;

    public WoodenLecternBlock(final IWoodType woodType) {
        super(Block.Properties.copy(Blocks.LECTERN));
        this.woodType = woodType;
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@Nonnull final BlockPos blockPos, @Nonnull final BlockState state) {
        return WoodenTileEntityTypes.WOODEN_LECTERN.get().create(blockPos, state);
    }

    @Nonnull
    @Override
    public InteractionResult use(final BlockState state, @Nonnull final Level world, @Nonnull final BlockPos pos,
                                 @Nonnull final Player player, @Nonnull final InteractionHand hand,
                                 @Nonnull final BlockHitResult rayTraceResult) {
        if (state.getValue(HAS_BOOK)) {
            if (!world.isClientSide) {
                this.openScreen(world, pos, player);
            }
            return InteractionResult.SUCCESS;
        } else {
            final ItemStack held = player.getItemInHand(hand);
            if (!held.isEmpty()) {
                return held.is(ItemTags.LECTERN_BOOKS) && tryPlaceBook(player, world, pos, state, held)
                       ? InteractionResult.SUCCESS
                       : InteractionResult.PASS;
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
