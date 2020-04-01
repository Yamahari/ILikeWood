package yamahari.ilikewood.block.post;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenPostBlock extends WoodenStrippedPostBlock {
    private final LazyValue<Block> STRIPPED;

    public WoodenPostBlock(final WoodType woodType) {
        super(woodType);
        this.STRIPPED = new LazyValue<>(WoodenBlocks.getRegistryObject(WoodenObjectType.STRIPPED_POST, woodType));
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult p_220051_6_) {
        Item held = playerEntity.getHeldItem(hand).getItem();
        if (hand == Hand.MAIN_HAND && held instanceof AxeItem) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote()) {
                world.setBlockState(blockPos, STRIPPED.getValue().getDefaultState().with(RotatedPillarBlock.AXIS, blockState.get(RotatedPillarBlock.AXIS)).with(WoodenStrippedPostBlock.WATERLOGGED, blockState.get(WoodenStrippedPostBlock.WATERLOGGED)).with(WoodenStrippedPostBlock.DOWN, blockState.get(WoodenStrippedPostBlock.DOWN)).with(WoodenStrippedPostBlock.UP, blockState.get(WoodenStrippedPostBlock.UP)).with(WoodenStrippedPostBlock.NORTH, blockState.get(WoodenStrippedPostBlock.NORTH)).with(WoodenStrippedPostBlock.EAST, blockState.get(WoodenStrippedPostBlock.EAST)).with(WoodenStrippedPostBlock.SOUTH, blockState.get(WoodenStrippedPostBlock.SOUTH)).with(WoodenStrippedPostBlock.WEST, blockState.get(WoodenStrippedPostBlock.WEST)), 11);
                playerEntity.getHeldItem(hand).attemptDamageItem(1, world.rand, (ServerPlayerEntity) playerEntity);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
