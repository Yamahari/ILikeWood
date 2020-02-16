package yamahari.ilikewood.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.client.tileentity.renderer.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ClientProxy implements IProxy {
    @SuppressWarnings("unchecked")
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.CHEST).forEach(type -> ClientRegistry.bindTileEntityRenderer(type, WoodenChestTileEntityRenderer::new));
        WoodenBlocks.getBlocks(WoodenObjectType.LADDER, WoodenObjectType.TORCH, WoodenObjectType.WALL_TORCH, WoodenObjectType.SCAFFOLDING).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));
        WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE).forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped()));
        WoodenContainerTypes.getContainerTypes(WoodenObjectType.CRAFTING_TABLE).forEach(type -> ScreenManager.registerFactory((ContainerType<? extends WorkbenchContainer>) type, CraftingScreen::new));
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
