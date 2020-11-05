package yamahari.ilikewood.registry.resource.resources;

import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.registry.resource.IWoodenResource;

public interface IWoodenStrippedLogResource extends IWoodenResource {
    ResourceLocation getEndTexture();

    ResourceLocation getSideTexture();
}
