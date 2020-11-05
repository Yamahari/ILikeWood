package yamahari.ilikewood.util.resources;

import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;

public final class WoodenLogResource implements IWoodenLogResource {
    private final ResourceLocation endTexture;
    private final ResourceLocation sideTexture;
    private final ResourceLocation resource;

    public WoodenLogResource(final ResourceLocation endTexture, final ResourceLocation sideTexture, final ResourceLocation resource) {
        this.endTexture = endTexture;
        this.sideTexture = sideTexture;
        this.resource = resource;
    }

    @Override
    public ResourceLocation getEndTexture() {
        return this.endTexture;
    }

    @Override
    public ResourceLocation getSideTexture() {
        return this.sideTexture;
    }

    @Override
    public ResourceLocation getResource() {
        return this.resource;
    }
}
