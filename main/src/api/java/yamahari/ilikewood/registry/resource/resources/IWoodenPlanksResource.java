package yamahari.ilikewood.registry.resource.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import yamahari.ilikewood.registry.resource.IWoodenResource;

public interface IWoodenPlanksResource extends IWoodenResource {
    ResourceLocation getTexture();

    BlockBehaviour.Properties getProperties();
}
