package yamahari.ilikewood.plugin.vanilla.util.resources;

import net.minecraft.resources.ResourceLocation;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;

public final class WoodenLogResource implements IWoodenLogResource {
    private final ResourceLocation endTexture;
    private final ResourceLocation sideTexture;
    private final ResourceLocation resource;
    private final SideTextureProperties properties;

    public WoodenLogResource(final ResourceLocation endTexture, final ResourceLocation sideTexture,
                             final ResourceLocation resource) {
        this(endTexture, sideTexture, resource, new SideTextureProperties(false, false, 1));
    }

    public WoodenLogResource(final ResourceLocation endTexture, final ResourceLocation sideTexture,
                             final ResourceLocation resource, final SideTextureProperties properties) {
        this.endTexture = endTexture;
        this.sideTexture = sideTexture;
        this.resource = resource;
        this.properties = properties;
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

    @Override
    public SideTextureProperties getSideTextureProperties() {
        return this.properties;
    }
}
