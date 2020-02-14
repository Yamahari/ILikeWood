package yamahari.ilikewood.proxy;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.client.tileentity.renderer.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ClientProxy implements IProxy {
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.CHEST).forEach(type -> ClientRegistry.bindTileEntityRenderer(type, WoodenChestTileEntityRenderer::new));
        WoodenBlocks.getBlocks(WoodenObjectType.LADDER).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.func_228643_e_()));
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
