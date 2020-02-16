package yamahari.ilikewood.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LecternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.LazyValue;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nullable;

public final class WoodenLecternBlock extends LecternBlock implements IWooden {
    private final WoodType woodType;
    private final LazyValue<TileEntityType<?>> tileEntityType;

    public WoodenLecternBlock(final WoodType woodType) {
        super(Block.Properties.from(Blocks.LECTERN));
        this.woodType = woodType;
        this.tileEntityType = new LazyValue<>(WoodenTileEntityTypes.getRegistryObject(WoodenObjectType.LECTERN, woodType));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(final IBlockReader reader) {
        return this.tileEntityType.getValue().create();
    }

    @Override
    public ActionResultType onBlockActivated(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult rayTraceResult) {
        if (state.get(HAS_BOOK)) {
            if (!world.isRemote) {
                this.openContainer(world, pos, player);
            }
            return ActionResultType.SUCCESS;
        } else {
            final ItemStack held = player.getHeldItem(hand);
            if (held.isEmpty() || held.getItem().isIn(ItemTags.field_226160_P_)) {
                tryPlaceBook(world, pos, state, held);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
