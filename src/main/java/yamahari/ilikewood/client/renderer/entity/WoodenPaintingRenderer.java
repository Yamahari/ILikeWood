package yamahari.ilikewood.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraftforge.common.MinecraftForge;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenPaintingEntity;

import javax.annotation.Nonnull;

public final class WoodenPaintingRenderer
    extends PaintingRenderer
{
    public WoodenPaintingRenderer(final EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(
        @Nonnull final Painting painting,
        final float p_115553_,
        final float partialTick,
        @Nonnull final PoseStack poseStack,
        @Nonnull final MultiBufferSource multiBufferSource,
        final int packedLight
    )
    {
        if (painting instanceof WoodenPaintingEntity woodenPaintingEntity)
        {
            final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodenPaintingEntity.getWoodType());

            if (planks != null)
            {
                poseStack.pushPose();
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - p_115553_));
                final var variant = painting.getVariant().value();
                poseStack.scale(0.0625F, 0.0625F, 0.0625F);
                final var textureManager = Minecraft.getInstance().getPaintingTextures();

                this.renderWoodenPainting(poseStack, painting, multiBufferSource, variant.getWidth(), variant.getHeight(), textureManager.get(variant),
                    Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(planks.getTexture())
                );
                poseStack.popPose();
            }
        }
        final var renderNameTagEvent =
            new net.minecraftforge.client.event.RenderNameTagEvent(painting, painting.getDisplayName(), this, poseStack, multiBufferSource, packedLight, partialTick);
        MinecraftForge.EVENT_BUS.post(renderNameTagEvent);
        if (renderNameTagEvent.getResult() != net.minecraftforge.eventbus.api.Event.Result.DENY && (renderNameTagEvent.getResult()
            == net.minecraftforge.eventbus.api.Event.Result.ALLOW || this.shouldShowName(painting)))
        {
            this.renderNameTag(painting, renderNameTagEvent.getContent(), poseStack, multiBufferSource, packedLight);
        }
    }

    private void renderWoodenPainting(
        final PoseStack poseStack,
        final Painting painting,
        final MultiBufferSource multiBufferSource,
        final int width,
        final int height,
        final TextureAtlasSprite image,
        final TextureAtlasSprite frame
    )
    {
        final var lastPose = poseStack.last();
        final var pose = lastPose.pose();
        final var normal = lastPose.normal();
        final var halfWidth = (float) (-width) / 2.0F;
        final var halfHeight = (float) (-height) / 2.0F;
        final var frameU0 = frame.getU0();
        final var frameU1 = frame.getU1();
        final var frameV0 = frame.getV0();
        final var frameV1 = frame.getV1();

        final var f10 = frame.getV(1.0D);
        final var f12 = frame.getU(1.0D);

        final var stepsX = width / 16;
        final var stepsY = height / 16;
        final var deltaX = 16.0D / (double) stepsX;
        final var deltaY = 16.0D / (double) stepsY;

        for (int stepX = 0; stepX < stepsX; ++stepX)
        {
            for (int stepY = 0; stepY < stepsY; ++stepY)
            {
                final var x0 = halfWidth + (float) ((stepX + 1) * 16);
                final var x1 = halfWidth + (float) (stepX * 16);
                final var y0 = halfHeight + (float) ((stepY + 1) * 16);
                final var y1 = halfHeight + (float) (stepY * 16);
                var lightX = painting.getBlockX();
                final var lightY = Mth.floor(painting.getY() + (double) ((y0 + y1) / 2.0F / 16.0F));
                var lightZ = painting.getBlockZ();
                final var direction = painting.getDirection();
                if (direction == Direction.NORTH)
                {
                    lightX = Mth.floor(painting.getX() + (double) ((x0 + x1) / 2.0F / 16.0F));
                }
                else if (direction == Direction.WEST)
                {
                    lightZ = Mth.floor(painting.getZ() - (double) ((x0 + x1) / 2.0F / 16.0F));
                }
                else if (direction == Direction.SOUTH)
                {
                    lightX = Mth.floor(painting.getX() - (double) ((x0 + x1) / 2.0F / 16.0F));
                }
                else if (direction == Direction.EAST)
                {
                    lightZ = Mth.floor(painting.getZ() + (double) ((x0 + x1) / 2.0F / 16.0F));
                }

                final var skipTop = stepY == (stepsY - 1);
                final var skipLeft = stepX == (stepsX - 1);
                final var skipBottom = stepY == 0;
                final var skipRight = stepX == 0;

                final var xx1 = skipRight ? x1 + 1.0F : x1;
                final var xx0 = skipLeft ? x0 - 1.0F : x0;
                final var yy1 = skipBottom ? y1 + 1.0F : y1;
                final var yy0 = skipTop ? y0 - 1.0F : y0;

                final var lightColor = LevelRenderer.getLightColor(painting.level(), new BlockPos(lightX, lightY, lightZ));

                final var du = (image.getU1() - image.getU0()) / (float) width;
                final var dv = (image.getV1() - image.getV0()) / (float) height;

                var imageU0 = image.getU(deltaX * (double) (stepsX - stepX));
                var imageU1 = image.getU(deltaX * (double) (stepsX - (stepX + 1)));
                var imageV0 = image.getV(deltaY * (double) (stepsY - stepY));
                var imageV1 = image.getV(deltaY * (double) (stepsY - (stepY + 1)));

                if (skipLeft)
                {
                    imageU1 += du;
                }

                if (skipRight)
                {
                    imageU0 -= du;
                }

                if (skipBottom)
                {
                    imageV0 -= dv;
                }

                if (skipTop)
                {
                    imageV1 += dv;
                }

                var consumer = multiBufferSource.getBuffer(RenderType.entitySolid(this.getTextureLocation(painting)));

                this.vertex(pose, normal, consumer, xx0, yy1, imageU1, imageV0, -0.6F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, xx1, yy1, imageU0, imageV0, -0.6F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, xx1, yy0, imageU0, imageV1, -0.6F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, xx0, yy0, imageU1, imageV1, -0.6F, 0, 0, -1, lightColor);

                consumer = multiBufferSource.getBuffer(RenderType.entitySolid(TextureAtlas.LOCATION_BLOCKS));

                this.vertex(pose, normal, consumer, x0, y1, frameU0, frameV1, -0.5F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, frameU1, frameV1, -0.5F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, x1, y0, frameU1, frameV0, -0.5F, 0, 0, -1, lightColor);
                this.vertex(pose, normal, consumer, x0, y0, frameU0, frameV0, -0.5F, 0, 0, -1, lightColor);

                this.vertex(pose, normal, consumer, x0, y0, frameU1, frameV0, 0.5F, 0, 0, 1, lightColor);
                this.vertex(pose, normal, consumer, x1, y0, frameU0, frameV0, 0.5F, 0, 0, 1, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, frameU0, frameV1, 0.5F, 0, 0, 1, lightColor);
                this.vertex(pose, normal, consumer, x0, y1, frameU1, frameV1, 0.5F, 0, 0, 1, lightColor);

                this.vertex(pose, normal, consumer, x0, y0, frameU0, frameV0, -0.5F, 0, 1, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y0, frameU1, frameV0, -0.5F, 0, 1, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y0, frameU1, f10, 0.5F, 0, 1, 0, lightColor);
                this.vertex(pose, normal, consumer, x0, y0, frameU0, f10, 0.5F, 0, 1, 0, lightColor);

                this.vertex(pose, normal, consumer, x0, y1, frameU0, frameV0, 0.5F, 0, -1, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, frameU1, frameV0, 0.5F, 0, -1, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, frameU1, f10, -0.5F, 0, -1, 0, lightColor);
                this.vertex(pose, normal, consumer, x0, y1, frameU0, f10, -0.5F, 0, -1, 0, lightColor);

                this.vertex(pose, normal, consumer, x0, y0, f12, frameV0, 0.5F, -1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x0, y1, f12, frameV1, 0.5F, -1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x0, y1, frameU0, frameV1, -0.5F, -1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x0, y0, frameU0, frameV0, -0.5F, -1, 0, 0, lightColor);

                this.vertex(pose, normal, consumer, x1, y0, f12, frameV0, -0.5F, 1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, f12, frameV1, -0.5F, 1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y1, frameU0, frameV1, 0.5F, 1, 0, 0, lightColor);
                this.vertex(pose, normal, consumer, x1, y0, frameU0, frameV0, 0.5F, 1, 0, 0, lightColor);
            }
        }
    }
}
