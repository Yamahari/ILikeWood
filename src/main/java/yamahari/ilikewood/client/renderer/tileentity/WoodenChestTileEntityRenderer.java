package yamahari.ilikewood.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import yamahari.ilikewood.block.WoodenChestBlock;
import yamahari.ilikewood.client.Atlases;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;

public final class WoodenChestTileEntityRenderer extends ChestTileEntityRenderer<WoodenChestTileEntity> {
    public WoodenChestTileEntityRenderer(final TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected RenderMaterial getMaterial(final WoodenChestTileEntity woodenChestTileEntity, final ChestType chestType) {
        final IWoodType woodType;
        if (woodenChestTileEntity.hasWorld()) {
            final Block block = woodenChestTileEntity.getBlockState().getBlock();
            woodType = block instanceof WoodenChestBlock ? ((WoodenChestBlock) block).getWoodType() : VanillaWoodTypes.DUMMY;
        } else {
            woodType = woodenChestTileEntity.getWoodType();
        }
        return Atlases.getChestMaterials(!woodType.equals(VanillaWoodTypes.DUMMY) ? woodType : VanillaWoodTypes.OAK).get(chestType);
    }
}
