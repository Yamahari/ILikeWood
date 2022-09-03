package yamahari.ilikewood.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraft.client.renderer.blockentity.LecternRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.blockentity.WoodenChestBlockEntity;
import yamahari.ilikewood.client.blockentity.WoodenLecternBlockEntity;
import yamahari.ilikewood.client.renderer.entity.WoodenChairEntityRenderer;
import yamahari.ilikewood.client.renderer.entity.WoodenItemFrameRenderer;
import yamahari.ilikewood.client.renderer.entity.WoodenPaintingRenderer;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestBlockEntityRenderer;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.entity.WoodenChairEntity;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.WoodenBlockEntityTypes;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterRenderersEventHandler
{
    private RegisterRenderersEventHandler()
    {
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterRenderers(final EntityRenderersEvent.RegisterRenderers event)
    {
        if (ILikeWoodConfig.CHESTS_CONFIG.isEnabled())
        {
            event.registerBlockEntityRenderer((BlockEntityType<WoodenChestBlockEntity>) WoodenBlockEntityTypes.WOODEN_CHEST.get(), WoodenChestBlockEntityRenderer::new);
        }

        if (ILikeWoodConfig.LECTERNS_CONFIG.isEnabled())
        {
            event.registerBlockEntityRenderer((BlockEntityType<WoodenLecternBlockEntity>) WoodenBlockEntityTypes.WOODEN_LECTERN.get(), LecternRenderer::new);
        }

        if (ILikeWoodConfig.ITEM_FRAMES_CONFIG.isEnabled())
        {
            ILikeWood.ENTITY_TYPE_REGISTRY
                .getObjects(WoodenEntityType.ITEM_FRAME)
                .forEach(type -> event.registerEntityRenderer((EntityType<WoodenItemFrameEntity>) type,
                    context -> new WoodenItemFrameRenderer(context, Minecraft.getInstance().getItemRenderer())
                ));
        }

        if (ILikeWoodConfig.CHAIRS_CONFIG.isEnabled() || ILikeWoodConfig.STOOLS_CONFIG.isEnabled())
        {
            ILikeWood.ENTITY_TYPE_REGISTRY
                .getObjects(WoodenEntityType.CHAIR)
                .forEach(type -> event.registerEntityRenderer((EntityType<WoodenChairEntity>) type, WoodenChairEntityRenderer::new));
        }

        if (ILikeWoodConfig.CAMPFIRE_CONFIG.isEnabled())
        {
            event.registerBlockEntityRenderer((BlockEntityType<? extends CampfireBlockEntity>) WoodenBlockEntityTypes.WOODEN_CAMPFIRE.get(), CampfireRenderer::new);
        }

        if (ILikeWoodConfig.PAINTING_CONFIG.isEnabled())
        {
            ILikeWood.ENTITY_TYPE_REGISTRY
                .getObjects(WoodenEntityType.PAINTING)
                .forEach(type -> event.registerEntityRenderer((EntityType<? extends Painting>) type, WoodenPaintingRenderer::new));
        }
    }
}