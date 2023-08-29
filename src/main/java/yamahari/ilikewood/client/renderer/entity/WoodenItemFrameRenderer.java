package yamahari.ilikewood.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import yamahari.ilikewood.client.SpecialModels;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;

public class WoodenItemFrameRenderer extends EntityRenderer<WoodenItemFrameEntity> {
    private final Minecraft mc = Minecraft.getInstance();
    private final ItemRenderer itemRenderer;

    public WoodenItemFrameRenderer(final EntityRendererProvider.Context context, final ItemRenderer itemRendererIn) {
        super(context);
        this.itemRenderer = itemRendererIn;
    }

    @Override
    public void render(@Nonnull final WoodenItemFrameEntity itemFrame, final float entityYaw, final float partialTicks, @Nonnull final PoseStack matrixStackIn, @Nonnull final MultiBufferSource bufferIn, final int packedLightIn) {
        super.render(itemFrame, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        final Direction direction = itemFrame.getDirection();
        final Vec3 renderOffset = this.getRenderOffset(itemFrame, partialTicks);
        matrixStackIn.translate(-renderOffset.x(), -renderOffset.y(), -renderOffset.z());
        final double d0 = 0.46875D;
        matrixStackIn.translate((double) direction.getStepX() * d0, (double) direction.getStepY() * d0, (double) direction.getStepZ() * d0);
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(itemFrame.getXRot()));
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - itemFrame.getYRot()));
        final boolean invisible = itemFrame.isInvisible();
        final ItemStack displayedItem = itemFrame.getItem();
        if (!invisible) {
            final BlockRenderDispatcher dispatcher = this.mc.getBlockRenderer();
            final ModelManager manager = dispatcher.getBlockModelShaper().getModelManager();
            final IWoodType woodType = ((IWooden) itemFrame).getWoodType();
            final ResourceLocation location = displayedItem.getItem() instanceof MapItem ? SpecialModels.ITEM_FRAME_MAP_MODELS.get(woodType) : SpecialModels.ITEM_FRAME_MODELS.get(
                woodType);
            matrixStackIn.pushPose();
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            dispatcher.getModelRenderer()
                .renderModel(matrixStackIn.last(),
                    bufferIn.getBuffer(Sheets.solidBlockSheet()),
                    null,
                    manager.getModel(location),
                    1.0F,
                    1.0F,
                    1.0F,
                    packedLightIn,
                    OverlayTexture.NO_OVERLAY);
            matrixStackIn.popPose();
        }

        if (!displayedItem.isEmpty()) {
            final MapItemSavedData mapdata = MapItem.getSavedData(displayedItem, itemFrame.level());
            if (mapdata != null) {
                if (invisible) {
                    matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                }
                else {
                    matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                }
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees((float) (itemFrame.getRotation() % 4 * 2) * 360.0F / 8.0F));
                matrixStackIn.mulPose(Axis.ZP.rotationDegrees(180.0F));
                final float f = 0.0078125F;
                matrixStackIn.scale(f, f, f);
                matrixStackIn.translate(-64.0D, -64.0D, 0.0D);
                final Integer mapId = MapItem.getMapId(displayedItem);
                matrixStackIn.translate(0.0D, 0.0D, -1.0D);
                this.mc.gameRenderer.getMapRenderer().render(matrixStackIn, bufferIn, mapId, mapdata, true, packedLightIn);
            }
            else {
                if (direction == Direction.DOWN || direction == Direction.UP) {
                    matrixStackIn.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    matrixStackIn.mulPose(Axis.YP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                }
                else {
                    if (invisible) {
                        matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                    }
                    else {
                        matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                    }
                    matrixStackIn.mulPose(Axis.ZP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                }
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
                this.itemRenderer.renderStatic(displayedItem, ItemDisplayContext.FIXED,
                    packedLightIn,
                    OverlayTexture.NO_OVERLAY,
                    matrixStackIn, bufferIn, itemFrame.level(),
                    itemFrame.getId());
            }
        }
        matrixStackIn.popPose();
    }

    @Nonnull
    @Override
    public Vec3 getRenderOffset(final WoodenItemFrameEntity entityIn, final float partialTicks) {
        return new Vec3((float) entityIn.getDirection().getStepX() * 0.3F, -0.25D, (float) entityIn.getDirection().getStepZ() * 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull final WoodenItemFrameEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    protected boolean shouldShowName(@Nonnull final WoodenItemFrameEntity entity) {
        if (Minecraft.renderNames() && !entity.getItem().isEmpty() && entity.getItem().hasCustomHoverName() && this.entityRenderDispatcher.crosshairPickEntity == entity) {
            double d0 = this.entityRenderDispatcher.distanceToSqr(entity);
            float f = entity.isDiscrete() ? 32.0F : 64.0F;
            return d0 < (double) (f * f);
        }
        else {
            return false;
        }
    }

    @Override
    protected void renderNameTag(@Nonnull final WoodenItemFrameEntity entityIn, @Nonnull final Component displayNameIn, @Nonnull final PoseStack matrixStackIn, @Nonnull final MultiBufferSource bufferIn, final int packedLightIn) {
        super.renderNameTag(entityIn, entityIn.getItem().getDisplayName(), matrixStackIn, bufferIn, packedLightIn);
    }
}
