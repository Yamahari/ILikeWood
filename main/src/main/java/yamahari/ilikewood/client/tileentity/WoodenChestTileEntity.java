package yamahari.ilikewood.client.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class WoodenChestTileEntity extends ChestBlockEntity implements IWooden {
    private final IWoodType woodType;

    public WoodenChestTileEntity(final BlockPos blockPos, final BlockState blockState) {
        this(Util.DUMMY_WOOD_TYPE, blockPos, blockState);
    }

    public WoodenChestTileEntity(final IWoodType woodType, final BlockPos blockPos, final BlockState blockState) {
        super(blockPos, blockState);
        this.woodType = woodType;
    }

    @Override
    public BlockEntityType<?> getType() {
        return WoodenTileEntityTypes.WOODEN_CHEST.get();
    }

    @Override
    protected Component getDefaultName() {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenChestBlock) {
            final String path = block.getRegistryName().getPath();
            return new TranslatableComponent(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }
        return super.getDefaultName();
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
