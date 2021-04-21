package yamahari.ilikewood.registry.resource;

import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenSlabResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public interface IWoodenResourceRegistry {
    void registerPlanksResource(IWoodType woodType, IWoodenPlanksResource planks);

    void registerLogResource(IWoodType woodType, IWoodenLogResource log);

    void registerStrippedLogResource(IWoodType woodType, IWoodenStrippedLogResource strippedLog);

    void registerSlabResource(IWoodType woodType, IWoodenSlabResource slab);

    void registerBlockResource(IWoodType woodType, WoodenBlockType blockType, IWoodenResource resource);

    void registerItemResource(IWoodType woodType, WoodenItemType itemType, IWoodenResource resource);
}
