package yamahari.ilikewood.client.tileentity.renderer;

import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import yamahari.ilikewood.client.ILikeWoodAtlases;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;

public final class WoodenChestTileEntityRenderer extends ChestTileEntityRenderer {
    public WoodenChestTileEntityRenderer(final TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected Material getMaterial(final TileEntity tileEntity, final ChestType chestType) {
        return ILikeWoodAtlases.getChestMaterial(((WoodenChestTileEntity) tileEntity).getWoodType().toString(), chestType);
    }
}
