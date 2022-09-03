package yamahari.ilikewood.client.renderer.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.client.blockentity.WoodenChestBlockEntity;

import javax.annotation.Nonnull;

public final class WoodenChestItemStackBlockEntityRenderer
    extends BlockEntityWithoutLevelRenderer
{
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private WoodenChestBlockEntity chestTileEntity = null;

    public WoodenChestItemStackBlockEntityRenderer()
    {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
    }

    @Override
    public void onResourceManagerReload(@Nonnull final ResourceManager resourceManager)
    {
        // Do nothing
    }

    @Override
    public void renderByItem(
        final ItemStack itemStack,
        @Nonnull final ItemTransforms.TransformType transformType,
        @Nonnull final PoseStack matrixStack,
        @Nonnull final MultiBufferSource buffer,
        final int combinedLight,
        final int combinedOverlay
    )
    {
        final Item item = itemStack.getItem();
        if (item instanceof BlockItem)
        {
            final Block block = ((BlockItem) item).getBlock();
            if (block instanceof WoodenChestBlock)
            {
                if (this.chestTileEntity == null)
                {
                    this.chestTileEntity = new WoodenChestBlockEntity(((WoodenChestBlock) block).getWoodType(), BlockPos.ZERO, block.defaultBlockState());
                }
                this.blockEntityRenderDispatcher.renderItem(this.chestTileEntity, matrixStack, buffer, combinedLight, combinedOverlay);
            }
        }
    }
}
