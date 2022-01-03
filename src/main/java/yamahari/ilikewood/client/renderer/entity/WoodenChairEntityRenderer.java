package yamahari.ilikewood.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import yamahari.ilikewood.entity.WoodenChairEntity;

import javax.annotation.Nonnull;

public final class WoodenChairEntityRenderer extends EntityRenderer<WoodenChairEntity> {
    public WoodenChairEntityRenderer(final EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull final WoodenChairEntity entity) {
        return new ResourceLocation("");
    }

    @Override
    public void render(@Nonnull final WoodenChairEntity entity, final float yaw, final float partialTicks,
                       final @Nonnull PoseStack stack, @Nonnull final MultiBufferSource bufferSource,
                       final int packedLight) {
    }

    @Override
    public boolean shouldRender(@Nonnull final WoodenChairEntity entity, @Nonnull final Frustum frustum,
                                final double dx, final double dy, final double dz) {
        return false;
    }
}
