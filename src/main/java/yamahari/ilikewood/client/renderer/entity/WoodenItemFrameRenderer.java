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
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenItemFrameRenderer extends EntityRenderer<WoodenItemFrameEntity> {
    private final Minecraft mc = Minecraft.getInstance();
    private final ItemRenderer itemRenderer;

    public WoodenItemFrameRenderer(final EntityRendererManager renderManagerIn, final ItemRenderer itemRendererIn) {
        super(renderManagerIn);
        this.itemRenderer = itemRendererIn;
    }

    @Override
    public void render(final WoodenItemFrameEntity itemFrame, final float entityYaw, final float partialTicks,
                       final MatrixStack matrixStackIn, final IRenderTypeBuffer bufferIn, final int packedLightIn) {
        super.render(itemFrame, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        final Direction direction = itemFrame.getHorizontalFacing();
        final Vector3d renderOffset = this.getRenderOffset(itemFrame, partialTicks);
        matrixStackIn.translate(-renderOffset.getX(), -renderOffset.getY(), -renderOffset.getZ());
        final double d0 = 0.46875D;
        matrixStackIn.translate((double) direction.getXOffset() * d0, (double) direction.getYOffset() * d0, (double) direction.getZOffset() * d0);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(itemFrame.rotationPitch));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - itemFrame.rotationYaw));
        final boolean invisible = itemFrame.isInvisible();
        final ItemStack displayedItem = itemFrame.getDisplayedItem();
        if (!invisible) {
            final BlockRendererDispatcher dispatcher = this.mc.getBlockRendererDispatcher();
            final ModelManager manager = dispatcher.getBlockModelShapes().getModelManager();
            final WoodType woodType = ((IWooden) itemFrame).getWoodType();
            final ResourceLocation location = displayedItem.getItem() instanceof FilledMapItem
                    ? SpecialModels.ITEM_FRAME_MAP_MODELS.get(woodType)
                    : SpecialModels.ITEM_FRAME_MODELS.get(woodType);
            matrixStackIn.push();
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            dispatcher.getBlockModelRenderer().renderModelBrightnessColor(matrixStackIn.getLast(), bufferIn.getBuffer(Atlases.getSolidBlockType()), null, manager.getModel(location), 1.0F, 1.0F, 1.0F, packedLightIn, OverlayTexture.NO_OVERLAY);
            matrixStackIn.pop();
        }

        if (!displayedItem.isEmpty()) {
            final MapData mapdata = FilledMapItem.getMapData(displayedItem, itemFrame.world);
            if (mapdata != null) {
                if (invisible) {
                    matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                } else {
                    matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                }
                matrixStackIn.rotate(Vector3f.ZP.rotationDegrees((float) (itemFrame.getRotation() % 4 * 2) * 360.0F / 8.0F));
                matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.0F));
                final float f = 0.0078125F;
                matrixStackIn.scale(f, f, f);
                matrixStackIn.translate(-64.0D, -64.0D, 0.0D);
                matrixStackIn.translate(0.0D, 0.0D, -1.0D);
                this.mc.gameRenderer.getMapItemRenderer().renderMap(matrixStackIn, bufferIn, mapdata, true, packedLightIn);
            } else {
                if (direction == Direction.DOWN || direction == Direction.UP) {
                    matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-90.0F));
                    matrixStackIn.rotate(Vector3f.YP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                } else {
                    if (invisible) {
                        matrixStackIn.translate(0.0D, 0.0D, 0.5D);
                    } else {
                        matrixStackIn.translate(0.0D, 0.0D, 0.4375D);
                    }
                    matrixStackIn.rotate(Vector3f.ZP.rotationDegrees((float) itemFrame.getRotation() * 360.0F / 8.0F));
                }
                matrixStackIn.scale(0.5F, 0.5F, 0.5F);
                this.itemRenderer.renderItem(displayedItem, ItemCameraTransforms.TransformType.FIXED, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            }
        }
        matrixStackIn.pop();
    }

    @Override
    public Vector3d getRenderOffset(final WoodenItemFrameEntity entityIn, final float partialTicks) {
        return new Vector3d((float) entityIn.getHorizontalFacing().getXOffset() * 0.3F, -0.25D, (float) entityIn.getHorizontalFacing().getZOffset() * 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getEntityTexture(final WoodenItemFrameEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
    }

    @Override
    protected boolean canRenderName(final WoodenItemFrameEntity entity) {
        if (Minecraft.isGuiEnabled() && !entity.getDisplayedItem().isEmpty() && entity.getDisplayedItem().hasDisplayName() && this.renderManager.pointedEntity == entity) {
            double d0 = this.renderManager.squareDistanceTo(entity);
            float f = entity.isDiscrete() ? 32.0F : 64.0F;
            return d0 < (double) (f * f);
        } else {
            return false;
        }
    }

    @Override
    protected void renderName(final WoodenItemFrameEntity entityIn, final ITextComponent displayNameIn,
                              final MatrixStack matrixStackIn, final IRenderTypeBuffer bufferIn, final int packedLightIn) {
        super.renderName(entityIn, entityIn.getDisplayedItem().getDisplayName(), matrixStackIn, bufferIn, packedLightIn);
    }
}
