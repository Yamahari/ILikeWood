package yamahari.ilikewood.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.client.tileentity.renderer.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.objectholder.chest.WoodenChestTileEntityTypes;
import yamahari.ilikewood.util.Util;

public final class ClientProxy implements IProxy {
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        Util.getTileEntityTypes(WoodenChestTileEntityTypes.class).forEach(
                type -> ClientRegistry.bindTileEntityRenderer(type, WoodenChestTileEntityRenderer::new));
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
