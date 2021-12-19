package yamahari.ilikewood.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;

import javax.annotation.Nonnull;

public final class WoodenChestItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
    private WoodenChestTileEntity chestTileEntity = null;

    @Override
    public void renderByItem(final ItemStack itemStack, @Nonnull final ItemCameraTransforms.TransformType transformType,
                             @Nonnull final MatrixStack matrixStack, @Nonnull final IRenderTypeBuffer buffer,
                             final int combinedLight, final int combinedOverlay) {
        final Item item = itemStack.getItem();
        if (item instanceof BlockItem) {
            final Block block = ((BlockItem) item).getBlock();
            if (block instanceof WoodenChestBlock) {
                if (this.chestTileEntity == null) {
                    this.chestTileEntity = new WoodenChestTileEntity(((WoodenChestBlock) block).getWoodType(),
                        WoodenTileEntityTypes.WOODEN_CHEST.get());
                }
                TileEntityRendererDispatcher.instance.renderItem(this.chestTileEntity, matrixStack,
                    buffer,
                    combinedLight,
                    combinedOverlay);
            }
        }
    }
}
