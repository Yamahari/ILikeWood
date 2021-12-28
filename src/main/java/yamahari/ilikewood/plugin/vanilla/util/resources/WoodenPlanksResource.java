package yamahari.ilikewood.plugin.vanilla.util.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;

public final class WoodenPlanksResource implements IWoodenPlanksResource {
    private final ResourceLocation texture;
    private final ResourceLocation resource;
    private final BlockBehaviour.Properties properties;

    public WoodenPlanksResource(final ResourceLocation texture, final ResourceLocation resource,
                                final BlockBehaviour.Properties properties) {
        this.texture = texture;
        this.resource = resource;
        this.properties = properties;
    }

    @Override
    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public ResourceLocation getResource() {
        return this.resource;
    }

    @Override
    public BlockBehaviour.Properties getProperties() {
        return this.properties;
    }
}
