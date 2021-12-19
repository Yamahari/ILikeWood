package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import yamahari.ilikewood.block.WoodenScaffoldingBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nullable;

public final class WoodenScaffoldingItem extends WoodenBlockItem {
    public WoodenScaffoldingItem(final Block block) {
        super(WoodenBlockType.SCAFFOLDING, block, new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
    }

    @Nullable
    @Override
    public BlockItemUseContext updatePlacementContext(final BlockItemUseContext context) {
        final BlockPos pos = context.getClickedPos();
        final World world = context.getLevel();
        BlockState state = world.getBlockState(pos);
        final Block block = this.getBlock();
        if (state.getBlock() != block) {
            return WoodenScaffoldingBlock.getDistance(world, pos) == 7 ? null : context;
        } else {
            final Direction direction;
            if (context.isSecondaryUseActive()) {
                direction = context.isInside() ? context.getClickedFace().getOpposite() : context.getClickedFace();
            } else {
                direction = context.getClickedFace() == Direction.UP ? context.getHorizontalDirection() : Direction.UP;
            }

            int distance = 0;
            BlockPos.Mutable mutable = (new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ())).move(direction);

            while (distance < 7) {
                if (!world.isClientSide && !World.isInWorldBounds(mutable)) {
                    final PlayerEntity player = context.getPlayer();
                    final int height = world.getHeight();
                    if (player instanceof ServerPlayerEntity && mutable.getY() >= height) {
                        final SChatPacket packet =
                            new SChatPacket((new TranslationTextComponent("build.tooHigh", height)).withStyle(
                                TextFormatting.RED), ChatType.GAME_INFO, Util.NIL_UUID);
                        ((ServerPlayerEntity) player).connection.send(packet);
                    }
                    break;
                }
                state = world.getBlockState(mutable);
                if (state.getBlock() != this.getBlock()) {
                    if (state.canBeReplaced(context)) {
                        return BlockItemUseContext.at(context, mutable, direction);
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
