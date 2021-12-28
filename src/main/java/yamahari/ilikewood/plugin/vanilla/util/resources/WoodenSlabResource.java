package yamahari.ilikewood.plugin.vanilla.util.resources;

import net.minecraft.resources.ResourceLocation;
import yamahari.ilikewood.registry.resource.resources.IWoodenSlabResource;

public final class WoodenSlabResource implements IWoodenSlabResource {
    private final ResourceLocation bottomTexture;
    private final ResourceLocation topTexture;
    private final ResourceLocation sideTexture;
    private final ResourceLocation resource;

    public WoodenSlabResource(final ResourceLocation bottomTexture, final ResourceLocation topTexture,
                              final ResourceLocation sideTexture, final ResourceLocation resource) {
        this.bottomTexture = bottomTexture;
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.resource = resource;
    }

    @Override
    public ResourceLocation getBottomTexture() {
        return this.bottomTexture;
    }

    @Override
    public ResourceLocation getTopTexture() {
        return this.topTexture;
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
