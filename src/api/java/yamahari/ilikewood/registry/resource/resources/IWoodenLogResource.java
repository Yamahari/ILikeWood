package yamahari.ilikewood.registry.resource.resources;

import net.minecraft.resources.ResourceLocation;
import yamahari.ilikewood.registry.resource.IWoodenResource;

public interface IWoodenLogResource extends IWoodenResource {
    ResourceLocation getEndTexture();

    ResourceLocation getSideTexture();

    SideTextureProperties getSideTextureProperties();

    record SideTextureProperties(boolean animated, boolean interpolate, int frameTime) {
    }
}
