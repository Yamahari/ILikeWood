package yamahari.ilikewood.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.block.WoodenScaffoldingBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nullable;

public final class WoodenScaffoldingItem extends WoodenBlockItem {
    public WoodenScaffoldingItem(final Block block) {
        super(WoodenBlockType.SCAFFOLDING, block, new Item.Properties());
    }

    @Nullable
    @Override
    public BlockPlaceContext updatePlacementContext(final BlockPlaceContext context) {
        final BlockPos pos = context.getClickedPos();
        final Level world = context.getLevel();
        BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof WoodenScaffoldingBlock)) {
            return WoodenScaffoldingBlock.getDistance(world, pos) == 7 ? null : context;
        }
        else {
            final Direction direction;
            if (context.isSecondaryUseActive()) {
                direction = context.isInside() ? context.getClickedFace().getOpposite() : context.getClickedFace();
            }
            else {
                direction = context.getClickedFace() == Direction.UP ? context.getHorizontalDirection() : Direction.UP;
            }

            int distance = 0;
            BlockPos.MutableBlockPos mutable = (new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ())).move(direction);

            while (distance < 7) {
                if (!world.isClientSide && !world.isInWorldBounds(mutable)) {
                    final Player player = context.getPlayer();
                    final int height = world.getHeight();
                    if (player instanceof ServerPlayer && mutable.getY() >= height) {
                        ((ServerPlayer) player).sendSystemMessage(Component.translatable("build.tooHigh", height - 1).withStyle(ChatFormatting.RED), true);
                    }
                    break;
                }
                state = world.getBlockState(mutable);
                if (!(state.getBlock() instanceof WoodenScaffoldingBlock)) {
                    if (state.canBeReplaced(context)) {
                        return BlockPlaceContext.at(context, mutable, direction);
                    }
                    break;
                }
                mutable.move(direction);
                if (direction.getAxis().isHorizontal()) {
                    ++distance;
                }
            }
            return null;
        }
    }

    @Override
    protected boolean mustSurvive() {
        return false;
    }
}
