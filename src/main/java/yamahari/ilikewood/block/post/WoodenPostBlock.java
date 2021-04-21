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
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;

public class WoodenPostBlock extends WoodenStrippedPostBlock {
    private final LazyValue<Optional<Block>> stripped;

    public WoodenPostBlock(final IWoodType woodType) {
        super(woodType);
        if (woodType.getBlockTypes().contains(WoodenBlockType.STRIPPED_POST)) {
            this.stripped = new LazyValue<>(() -> Optional.of(ILikeWood.BLOCK_REGISTRY
                .getRegistryObject(woodType, WoodenBlockType.STRIPPED_POST)
                .get()));
        } else if (woodType.getBuiltinBlockTypes().contains(WoodenBlockType.STRIPPED_POST)) {
            this.stripped =
                new LazyValue<>(() -> Optional.of(Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getBlockResource(woodType, WoodenBlockType.STRIPPED_POST)
                    .getResource()))));
        } else {
            this.stripped = new LazyValue<>(Optional::empty);
        }
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public ActionResultType onBlockActivated(@Nonnull final BlockState blockState, @Nonnull final World world,
                                             @Nonnull final BlockPos blockPos, @Nonnull final PlayerEntity playerEntity,
                                             @Nonnull final Hand hand, @Nonnull final BlockRayTraceResult hit) {
        final Optional<Block> optionalBlock = stripped.getValue();
        if (optionalBlock.isPresent()) {
            final Item held = playerEntity.getHeldItem(hand).getItem();
            if (hand == Hand.MAIN_HAND && held instanceof AxeItem) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isRemote()) {
                    world.setBlockState(blockPos,
                        optionalBlock
                            .get()
                            .getDefaultState()
                            .with(RotatedPillarBlock.AXIS, blockState.get(RotatedPillarBlock.AXIS))
                            .with(WoodenStrippedPostBlock.WATERLOGGED,
                                blockState.get(WoodenStrippedPostBlock.WATERLOGGED))
                            .with(WoodenStrippedPostBlock.DOWN, blockState.get(WoodenStrippedPostBlock.DOWN))
                            .with(WoodenStrippedPostBlock.UP, blockState.get(WoodenStrippedPostBlock.UP))
                            .with(WoodenStrippedPostBlock.NORTH, blockState.get(WoodenStrippedPostBlock.NORTH))
                            .with(WoodenStrippedPostBlock.EAST, blockState.get(WoodenStrippedPostBlock.EAST))
                            .with(WoodenStrippedPostBlock.SOUTH, blockState.get(WoodenStrippedPostBlock.SOUTH))
                            .with(WoodenStrippedPostBlock.WEST, blockState.get(WoodenStrippedPostBlock.WEST)),
                        Constants.BlockFlags.DEFAULT_AND_RERENDER);
                    playerEntity.getHeldItem(hand).attemptDamageItem(1, world.rand, (ServerPlayerEntity) playerEntity);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
}
