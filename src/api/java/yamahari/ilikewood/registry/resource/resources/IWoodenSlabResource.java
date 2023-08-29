package yamahari.ilikewood.registry.resource.resources;

import net.minecraft.resources.ResourceLocation;
import yamahari.ilikewood.registry.resource.IWoodenResource;

public interface IWoodenSlabResource extends IWoodenResource {
    ResourceLocation getBottomTexture();

    ResourceLocation getTopTexture();

    ResourceLocation getSideTexture();
}
