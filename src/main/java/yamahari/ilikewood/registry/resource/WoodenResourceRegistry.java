package yamahari.ilikewood.registry.resource;

import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenSlabResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.HashMap;
import java.util.Map;

public final class WoodenResourceRegistry implements IWoodenResourceRegistry {
    private final Map<IWoodType, IWoodenPlanksResource> planks = new HashMap<>();
    private final Map<IWoodType, IWoodenLogResource> logs = new HashMap<>();
    private final Map<IWoodType, IWoodenStrippedLogResource> strippedLogs = new HashMap<>();
    private final Map<IWoodType, IWoodenSlabResource> slabs = new HashMap<>();

    @Override
    public void registerPlanksResource(final IWoodType woodType, final IWoodenPlanksResource planks) {
        this.planks.put(woodType, planks);
    }

    @Override
    public void registerLogResource(final IWoodType woodType, final IWoodenLogResource log) {
        this.logs.put(woodType, log);
    }

    @Override
    public void registerStrippedLogResource(final IWoodType woodType, final IWoodenStrippedLogResource strippedLog) {
        this.strippedLogs.put(woodType, strippedLog);
    }

    @Override
    public void registerSlabResource(final IWoodType woodType, final IWoodenSlabResource slab) {
        this.slabs.put(woodType, slab);
    }

    public boolean hasPlanks(final IWoodType woodType) {
        return this.planks.containsKey(woodType);
    }

    public boolean hasLog(final IWoodType woodType) {
        return this.logs.containsKey(woodType);
    }

    public boolean hasStrippedLog(final IWoodType woodType) {
        return this.strippedLogs.containsKey(woodType);
    }

    public boolean hasSlab(final IWoodType woodType) {
        return this.slabs.containsKey(woodType);
    }

    public IWoodenPlanksResource getPlanks(final IWoodType woodType) {
        return this.planks.get(woodType);
    }

    public IWoodenLogResource getLog(final IWoodType woodType) {
        return this.logs.get(woodType);
    }

    public IWoodenStrippedLogResource getStrippedLog(final IWoodType woodType) {
        return this.strippedLogs.get(woodType);
    }

    public IWoodenSlabResource getSlab(final IWoodType woodType) {
        return this.slabs.get(woodType);
    }
}
