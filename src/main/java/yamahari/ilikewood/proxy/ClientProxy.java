package yamahari.ilikewood.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.LecternTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.gui.screen.WoodenSawmillScreen;
import yamahari.ilikewood.client.renderer.entity.WoodenItemFrameRenderer;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.container.WoodenSawmillContainer;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;

import java.util.stream.Stream;

public final class ClientProxy implements IProxy {
    @SuppressWarnings("unchecked")
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer((TileEntityType<WoodenChestTileEntity>) WoodenTileEntityTypes.WOODEN_CHEST
            .get(), WoodenChestTileEntityRenderer::new);

        ClientRegistry.bindTileEntityRenderer((TileEntityType<? extends LecternTileEntity>) WoodenTileEntityTypes.WOODEN_LECTERN
            .get(), LecternTileEntityRenderer::new);

        ILikeWood.ENTITY_TYPE_REGISTRY
            .getObjects(WoodenEntityType.ITEM_FRAME)
            .forEach(type -> RenderingRegistry.registerEntityRenderingHandler((EntityType<WoodenItemFrameEntity>) type,
                m -> new WoodenItemFrameRenderer(m, Minecraft.getInstance().getItemRenderer())));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.POST, WoodenBlockType.STRIPPED_POST))
            .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getSolid()));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.LADDER,
                WoodenBlockType.TORCH,
                WoodenBlockType.WALL_TORCH,
                WoodenBlockType.SCAFFOLDING,
                WoodenBlockType.SOUL_TORCH,
                WoodenBlockType.WALL_SOUL_TORCH))
            .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutout()));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.CRAFTING_TABLE, WoodenBlockType.SAWMILL))
            .forEach(block -> RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped()));

        ScreenManager.registerFactory((ContainerType<? extends WorkbenchContainer>) WoodenContainerTypes.WOODEN_WORK_BENCH
            .get(), CraftingScreen::new);

        ScreenManager.registerFactory((ContainerType<? extends WoodenSawmillContainer>) WoodenContainerTypes.WOODEN_SAWMILL
            .get(), WoodenSawmillScreen::new);

        ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.BOW).forEach(item -> {
            ItemModelsProperties.registerProperty(item, new ResourceLocation("pull"), (itemStack, world, entity) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return entity.getActiveItemStack() != itemStack
                           ? 0.0F
                           : (float) (itemStack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
                }
            });
            ItemModelsProperties.registerProperty(item,
                new ResourceLocation("pulling"),
                (itemStack, world, entity) ->
                    entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
        });

        ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.CROSSBOW).forEach(item -> {
            ItemModelsProperties.registerProperty(item, new ResourceLocation("pull"), (itemStack, world, entity) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return CrossbowItem.isCharged(itemStack)
                           ? 0.0F
                           : (float) (itemStack.getUseDuration() - entity.getItemInUseCount()) /
                             (float) CrossbowItem.getChargeTime(itemStack);
                }
            });
            ItemModelsProperties.registerProperty(item,
                new ResourceLocation("pulling"),
                (itemStack, world, entity) ->
                    entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemStack &&
                    !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);

            ItemModelsProperties.registerProperty(item,
                new ResourceLocation("charged"),
                (itemStack, world, entity) -> entity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);

            ItemModelsProperties.registerProperty(item,
                new ResourceLocation("firework"),
                (itemStack, world, entity) -> entity != null && CrossbowItem.isCharged(itemStack) &&
                                              CrossbowItem.hasChargedProjectile(itemStack, Items.FIREWORK_ROCKET)
                                              ? 1.0F
                                              : 0.0F);

        });

        ILikeWood.ITEM_REGISTRY
            .getObjects(WoodenItemType.FISHING_ROD)
            .forEach(item -> ItemModelsProperties.registerProperty(item,
                new ResourceLocation("cast"),
                (itemStack, world, entity) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        boolean flag = entity.getHeldItemMainhand() == itemStack;
                        boolean flag1 = entity.getHeldItemOffhand() == itemStack;
                        if (entity.getHeldItemMainhand().getItem() instanceof FishingRodItem) {
                            flag1 = false;
                        }

                        return (flag || flag1) && entity instanceof PlayerEntity &&
                               ((PlayerEntity) entity).fishingBobber != null ? 1.0F : 0.0F;
                    }
                }));
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
