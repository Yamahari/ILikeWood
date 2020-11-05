package yamahari.ilikewood.util.resources;

import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;

public final class WoodenPlanksResource implements IWoodenPlanksResource {
    private final ResourceLocation texture;
    private final ResourceLocation resource;

    public WoodenPlanksResource(final ResourceLocation texture, final ResourceLocation resource) {
        this.texture = texture;
        this.resource = resource;
    }

    @Override
    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public ResourceLocation getResource() {
        return this.resource;
    }
}
