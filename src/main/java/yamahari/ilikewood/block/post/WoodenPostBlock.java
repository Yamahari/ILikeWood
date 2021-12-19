package yamahari.ilikewood.block.post;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
import java.util.function.Supplier;

public class WoodenPostBlock extends WoodenStrippedPostBlock {
    // private final LazyValue<Optional<Block>> stripped;
    private final Supplier<Optional<Block>> stripped;

    public WoodenPostBlock(final IWoodType woodType) {
        super(woodType);
        if (woodType.getBlockTypes().contains(WoodenBlockType.STRIPPED_POST)) {
            this.stripped = Suppliers.memoize(() -> Optional.of(ILikeWood.BLOCK_REGISTRY
                .getRegistryObject(woodType, WoodenBlockType.STRIPPED_POST)
                .get()));
        } else if (woodType.getBuiltinBlockTypes().contains(WoodenBlockType.STRIPPED_POST)) {
            this.stripped =
                Suppliers.memoize(() -> Optional.of(Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getBlockResource(woodType, WoodenBlockType.STRIPPED_POST)
                    .getResource()))));
        } else {
            this.stripped = Suppliers.memoize(Optional::empty);
        }
    }

    @Nonnull
    @SuppressWarnings({"deprecation"})
    @Override
    public ActionResultType use(@Nonnull final BlockState blockState, @Nonnull final World world,
                                @Nonnull final BlockPos blockPos, @Nonnull final PlayerEntity playerEntity,
                                @Nonnull final Hand hand, @Nonnull final BlockRayTraceResult hit) {
        final Optional<Block> optionalBlock = stripped.get();
        if (optionalBlock.isPresent()) {
            final Item held = playerEntity.getItemInHand(hand).getItem();
            if (hand == Hand.MAIN_HAND && held instanceof AxeItem) {
                world.playSound(playerEntity, blockPos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClientSide) {
                    world.setBlock(blockPos,
                        optionalBlock
                            .get()
                            .defaultBlockState()
                            .setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS))
                            .setValue(WoodenStrippedPostBlock.WATERLOGGED,
                                blockState.getValue(WoodenStrippedPostBlock.WATERLOGGED))
                            .setValue(WoodenStrippedPostBlock.DOWN, blockState.getValue(WoodenStrippedPostBlock.DOWN))
                            .setValue(WoodenStrippedPostBlock.UP, blockState.getValue(WoodenStrippedPostBlock.UP))
                            .setValue(WoodenStrippedPostBlock.NORTH, blockState.getValue(WoodenStrippedPostBlock.NORTH))
                            .setValue(WoodenStrippedPostBlock.EAST, blockState.getValue(WoodenStrippedPostBlock.EAST))
                            .setValue(WoodenStrippedPostBlock.SOUTH, blockState.getValue(WoodenStrippedPostBlock.SOUTH))
                            .setValue(WoodenStrippedPostBlock.WEST, blockState.getValue(WoodenStrippedPostBlock.WEST)),
                        Constants.BlockFlags.DEFAULT_AND_RERENDER);
                    playerEntity.getItemInHand(hand).hurt(1, world.random, (ServerPlayerEntity) playerEntity);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
}
