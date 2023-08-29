package yamahari.ilikewood.client.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;

public final class WoodenChestBlockEntity
    extends ChestBlockEntity
    implements IWooden
{
    private final IWoodType woodType;

    public WoodenChestBlockEntity(
        final BlockPos blockPos,
        final BlockState blockState
    )
    {
        this(Util.DUMMY_WOOD_TYPE, blockPos, blockState);
    }

    public WoodenChestBlockEntity(
        final IWoodType woodType,
        final BlockPos blockPos,
        final BlockState blockState
    )
    {
        super(blockPos, blockState);
        this.woodType = woodType;
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType()
    {
        return WoodenBlockEntityTypes.WOODEN_CHEST.get();
    }

    @Nonnull
    @Override
    protected Component getDefaultName()
    {
        final Block block = this.getBlockState().getBlock();
        if (block instanceof WoodenChestBlock)
        {
            final String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
            return Component.translatable(StringUtils.joinWith(".", "container", Constants.MOD_ID, path));
        }
        return super.getDefaultName();
    }

    @Override
    public IWoodType getWoodType()
    {
        return this.woodType;
    }
}
