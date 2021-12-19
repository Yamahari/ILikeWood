package yamahari.ilikewood.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.storage.MapData;
import yamahari.ilikewood.client.SpecialModels;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;

public class WoodenItemFrameRenderer extends EntityRenderer<WoodenItemFrameEntity> {
    private final Minecraft mc = Minecraft.getInstance();
    private final ItemRenderer itemRenderer;

    public WoodenItemFrameRenderer(final EntityRendererManager renderManagerIn, final ItemRenderer itemRendererIn) {
        super(renderManagerIn);
        this.itemRenderer = itemRendererIn;
    }

    @Override
    public void render(@Nonnull final WoodenItemFrameEntity itemFrame, final float entityYaw, final float partialTicks,
                       @Nonnull final MatrixStack matrixStackIn, @Nonnull final IRenderTypeBuffer bufferIn,
                       final int packedLightIn) {
        super.render(itemFrame, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pushPose();
        final Direction direction = itemFrame.getDirection();
        final Vector3d renderOffset = this.getRenderOffset(itemFrame, partialTicks);
        matrixStackIn.translate(-renderOffset.x(), -renderOffset.y(), -renderOffset.z());
        final double d0 = 0.46875D;
        matrixStackIn.translate((double) direction.getStepX() * d0,
            (double) direction.getStepY() * d0,
            (double) direction.getStepZ() * d0);
        matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(itemFrame.xRot));
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - itemFrame.yRot));
        final boolean invisible = itemFrame.isInvisible();
        final ItemStack displayedItem = itemFrame.getItem();
        if (!invisible) {
            final BlockRendererDispatcher dispatcher = this.mc.getBlockRenderer();
            final ModelManager manager = dispatcher.getBlockModelShaper().getModelManager();
            final IWoodType woodType = ((IWooden) itemFrame).getWoodType();
            final ResourceLocation location = displayedItem.getItem() instanceof FilledMapItem
                                              ? SpecialModels.ITEM_FRAME_MAP_MODELS.get(woodType)
                                              : SpecialModels.ITEM_FRAME_MODELS.get(woodType);
            matrixStackIn.pushPose();
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            dispatcher
                .getModelRenderer()
                .renderModel(matrixStackIn.last(),
                    bufferIn.getBuffer(Atlases.solidBlockSheet()),
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
            final MapData mapdata = FilledMapItem.getOrCreateSavedData(displayedItem, itemFrame.level);
            if (mapdata != null) {
                if (invisible) {
                    matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                } else {
                    matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                }
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(
                    (float) (itemFrame.getRotation() % 4 * 2) * 360.0F / 8.0F));
                matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
                final float f = 0.0078125F;
                matrixStackIn.scale(f, f, f);
                matrixStackIn.translate(-64.0D, -64.0D, 0.0D);
                matrixStackIn.translate(0.0D, 0.0D, -1.0D);
                this.mc.gameRenderer.getMapRenderer().render(matrixStackIn, bufferIn, mapdata, true, packedLightIn);
            } else {
                if (direction == Direction.DOWN || direction == Direction.UP) {
                    matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
                    matrixStackIn.mulPose(Vector3f.YP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                } else {
                    if (invisible) {
                        matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                    } else {
                        matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                    }
                    matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                }
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
                this.itemRenderer.renderStatic(displayedItem,
                    ItemCameraTransforms.TransformType.FIXED,
                    packedLightIn,
                    OverlayTexture.NO_OVERLAY,
                    matrixStackIn,
                    bufferIn);
            }
        }
        matrixStackIn.popPose();
    }

    @Nonnull
    @Override
    public Vector3d getRenderOffset(final WoodenItemFrameEntity entityIn, final float partialTicks) {
        return new Vector3d((float) entityIn.getDirection().getStepX() * 0.3F,
            -0.25D,
            (float) entityIn.getDirection().getStepZ() * 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull final WoodenItemFrameEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }

    @Override
    protected boolean shouldShowName(@Nonnull final WoodenItemFrameEntity entity) {
        if (Minecraft.renderNames() && !entity.getItem().isEmpty() && entity.getItem().hasCustomHoverName() &&
            this.entityRenderDispatcher.crosshairPickEntity == entity) {
            double d0 = this.entityRenderDispatcher.distanceToSqr(entity);
            float f = entity.isDiscrete() ? 32.0F : 64.0F;
            return d0 < (double) (f * f);
        } else {
            return false;
        }
    }

    @Override
    protected void renderNameTag(@Nonnull final WoodenItemFrameEntity entityIn,
                                 @Nonnull final ITextComponent displayNameIn, @Nonnull final MatrixStack matrixStackIn,
                                 @Nonnull final IRenderTypeBuffer bufferIn, final int packedLightIn) {
        super.renderNameTag(entityIn, entityIn.getItem().getDisplayName(), matrixStackIn, bufferIn, packedLightIn);
    }
}
