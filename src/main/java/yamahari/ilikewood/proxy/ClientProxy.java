package yamahari.ilikewood.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.LecternTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.client.renderer.entity.WoodenItemFrameRenderer;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.*;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ClientProxy implements IProxy {
    @SuppressWarnings("unchecked")
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.CHEST)
                .forEach(type -> ClientRegistry.bindTileEntityRenderer(type, WoodenChestTileEntityRenderer::new));

        WoodenTileEntityTypes.getTileEntityTypes(WoodenObjectType.LECTERN)
                .forEach(type -> ClientRegistry.bindTileEntityRenderer((TileEntityType<? extends LecternTileEntity>) type, LecternTileEntityRenderer::new));

        WoodenEntityTypes.getEntityTypes(WoodenObjectType.ITEM_FRAME)
                .forEach(type -> {

                    RenderingRegistry.registerEntityRenderingHandler((EntityType<WoodenItemFrameEntity>) type,
                            m -> new WoodenItemFrameRenderer(m, Minecraft.getInstance().getItemRenderer()));

                    //manager.register((EntityType<WoodenItemFrameEntity>) type, new WoodenItemFrameRenderer(manager, Minecraft.getInstance().getItemRenderer()));
                });

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

        WoodenItems.getItems(WoodenObjectType.CROSSBOW).forEach(item -> {


            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("pull"), (itemStack, world, entity) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return CrossbowItem.isCharged(itemStack) ? 0.0F : (float) (itemStack.getUseDuration() - entity.getItemInUseCount()) / (float) CrossbowItem.getChargeTime(itemStack);
                }
            });
            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("pulling"),
                    (itemStack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);

            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("charged"),
                    (itemStack, world, entity) -> entity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);

            ItemModelsProperties.func_239418_a_(item, new ResourceLocation("firework"), (
                    itemStack, world, entity) -> entity != null && CrossbowItem.isCharged(itemStack) && CrossbowItem.hasChargedProjectile(itemStack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);

        });
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
