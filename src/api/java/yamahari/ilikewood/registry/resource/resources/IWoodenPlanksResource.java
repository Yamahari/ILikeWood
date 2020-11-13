package yamahari.ilikewood.registry.resource.resources;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.registry.resource.IWoodenResource;

public interface IWoodenPlanksResource extends IWoodenResource {
    ResourceLocation getTexture();

    AbstractBlock.Properties getProperties();
}
