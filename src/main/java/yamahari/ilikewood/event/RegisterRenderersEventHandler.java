package yamahari.ilikewood.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.LecternRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.renderer.entity.WoodenItemFrameRenderer;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterRenderersEventHandler {
    private RegisterRenderersEventHandler() {
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<WoodenChestTileEntity>) WoodenTileEntityTypes.WOODEN_CHEST.get(),
            WoodenChestTileEntityRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<WoodenLecternTileEntity>) WoodenTileEntityTypes.WOODEN_LECTERN
            .get(), LecternRenderer::new);

        ILikeWood.ENTITY_TYPE_REGISTRY
            .getObjects(WoodenEntityType.ITEM_FRAME)
            .forEach(type -> event.registerEntityRenderer((EntityType<WoodenItemFrameEntity>) type,
                m -> new WoodenItemFrameRenderer(m, Minecraft.getInstance().getItemRenderer())));

    }
}
