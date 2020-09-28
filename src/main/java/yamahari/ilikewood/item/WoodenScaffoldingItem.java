package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import yamahari.ilikewood.block.WoodenScaffoldingBlock;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nullable;

public final class WoodenScaffoldingItem extends WoodenBlockItem {
    public WoodenScaffoldingItem(final WoodenObjectType objectType, final Block block, final Item.Properties properties) {
        super(objectType, block, properties);
    }

    @Nullable
    @Override
    public BlockItemUseContext getBlockItemUseContext(final BlockItemUseContext context) {
        final BlockPos pos = context.getPos();
        final World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        final Block block = this.getBlock();
        if (state.getBlock() != block) {
            return WoodenScaffoldingBlock.getDistance(world, pos) == 7 ? null : context;
        } else {
            Direction direction;
            if (context.hasSecondaryUseForPlayer()) {
                direction = context.isInside() ? context.getFace().getOpposite() : context.getFace();
            } else {
                direction = context.getFace() == Direction.UP ? context.getPlacementHorizontalFacing() : Direction.UP;
            }

            int distance = 0;
            BlockPos.Mutable mutable = (new BlockPos.Mutable(pos.getX(), pos.getY(), pos.getZ())).move(direction);

            while (distance < 7) {
                if (!world.isRemote && !World.isValid(mutable)) {
                    PlayerEntity player = context.getPlayer();
                    int height = world.getHeight();
                    if (player instanceof ServerPlayerEntity && mutable.getY() >= height) {
                        SChatPacket packet = new SChatPacket((new TranslationTextComponent("build.tooHigh", height)).mergeStyle(TextFormatting.RED), ChatType.GAME_INFO, Util.DUMMY_UUID);
                        ((ServerPlayerEntity) player).connection.sendPacket(packet);
                    }
                    break;
                }
                state = world.getBlockState(mutable);
                if (state.getBlock() != this.getBlock()) {
                    if (state.isReplaceable(context)) {
                        return BlockItemUseContext.func_221536_a(context, mutable, direction);
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
    protected boolean checkPosition() {
        return false;
    }
}
