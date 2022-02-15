package yamahari.ilikewood.block.post;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class WoodenPostBlock extends WoodenStrippedPostBlock {
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

    @Nullable
    @Override
    public BlockState getToolModifiedState(final BlockState blockState, final Level level, final BlockPos pos,
                                           final Player player, final ItemStack stack, final ToolAction toolAction) {
        final Optional<Block> optionalBlock = this.stripped.get();

        if (optionalBlock.isEmpty() || !stack.canPerformAction(toolAction) ||
            !ToolActions.AXE_STRIP.equals(toolAction)) {
            return null;
        }

        return optionalBlock
            .get()
            .defaultBlockState()
            .setValue(RotatedPillarBlock.AXIS, blockState.getValue(RotatedPillarBlock.AXIS))
            .setValue(WoodenStrippedPostBlock.WATERLOGGED, blockState.getValue(WoodenStrippedPostBlock.WATERLOGGED))
            .setValue(WoodenStrippedPostBlock.DOWN, blockState.getValue(WoodenStrippedPostBlock.DOWN))
            .setValue(WoodenStrippedPostBlock.UP, blockState.getValue(WoodenStrippedPostBlock.UP))
            .setValue(WoodenStrippedPostBlock.NORTH, blockState.getValue(WoodenStrippedPostBlock.NORTH))
            .setValue(WoodenStrippedPostBlock.EAST, blockState.getValue(WoodenStrippedPostBlock.EAST))
            .setValue(WoodenStrippedPostBlock.SOUTH, blockState.getValue(WoodenStrippedPostBlock.SOUTH))
            .setValue(WoodenStrippedPostBlock.WEST, blockState.getValue(WoodenStrippedPostBlock.WEST));
    }
}
