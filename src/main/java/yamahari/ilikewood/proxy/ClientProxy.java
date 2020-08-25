package yamahari.ilikewood.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.LecternTileEntityRenderer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.client.tileentity.renderer.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ClientProxy implements IProxy {
    @SuppressWarnings("unchecked")
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.CHEST)
                .forEach(type -> ClientRegistry.bindTileEntityRenderer(type, WoodenChestTileEntityRenderer::new));

        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.LECTERN)
                .forEach(type -> ClientRegistry.bindTileEntityRenderer((TileEntityType<? extends LecternTileEntity>) type, LecternTileEntityRenderer::new));

        WoodenBlocks.getBlocks(WoodenObjectType.POST, WoodenObjectType.STRIPPED_POST)
                .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getSolid()));

        WoodenBlocks.getBlocks(WoodenObjectType.LADDER, WoodenObjectType.TORCH, WoodenObjectType.WALL_TORCH, WoodenObjectType.SCAFFOLDING)
                .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));

        WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE)
                .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped()));

        WoodenContainerTypes.getContainerTypes(WoodenObjectType.CRAFTING_TABLE)
                .forEach(type -> ScreenManager.registerFactory((ContainerType<? extends WorkbenchContainer>) type, CraftingScreen::new));

        WoodenItems.getItems(WoodenObjectType.BOW).forEach(item -> {
            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("pull"), (itemStack, world, entity) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return entity.getActiveItemStack() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
                }
            });
            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("pulling"),
                    (itemStack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
        });
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
