package yamahari.ilikewood.plugin.biomesoplenty;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenSlabResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.resources.WoodenLogResource;
import yamahari.ilikewood.util.resources.WoodenPlanksResource;
import yamahari.ilikewood.util.resources.WoodenSlabResource;
import yamahari.ilikewood.util.resources.WoodenStrippedLogResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class BiomesOPlentyWoodenResources {
    public static final Map<IWoodType, IWoodenPlanksResource> PLANKS;
    public static final Map<IWoodType, IWoodenLogResource> LOGS;
    public static final Map<IWoodType, IWoodenStrippedLogResource> STRIPPED_LOGS;
    public static final Map<IWoodType, IWoodenSlabResource> SLABS;

    static {
        final Map<IWoodType, IWoodenPlanksResource> planks = new HashMap<>();
        final Map<IWoodType, IWoodenLogResource> logs = new HashMap<>();
        final Map<IWoodType, IWoodenStrippedLogResource> strippedLogs = new HashMap<>();
        final Map<IWoodType, IWoodenSlabResource> slabs = new HashMap<>();

        BiomesOPlentyWoodTypes.get().forEach(woodType -> {
            final ResourceLocation planksResource = new ResourceLocation(Constants.BOP_MOD_ID, Util.toRegistryName(woodType.getName(), "planks"));
            final ResourceLocation logResource = new ResourceLocation(Constants.BOP_MOD_ID, Util.toRegistryName(woodType.getName(), "log"));
            final ResourceLocation strippedLogResource = new ResourceLocation(Constants.BOP_MOD_ID, Util.toRegistryName("stripped", woodType.getName(), "log"));
            final ResourceLocation slabResource = new ResourceLocation(Constants.BOP_MOD_ID, Util.toRegistryName(woodType.getName(), "slab"));

            final ResourceLocation planksTexture = new ResourceLocation(planksResource.getNamespace(), Util.toPath(ModelProvider.BLOCK_FOLDER, planksResource.getPath()));

            planks.put(woodType, new WoodenPlanksResource(planksTexture, planksResource));

            logs.put(woodType, new WoodenLogResource(
                    new ResourceLocation(logResource.getNamespace(), Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName(logResource.getPath(), "top"))),
                    new ResourceLocation(logResource.getNamespace(), Util.toPath(ModelProvider.BLOCK_FOLDER, logResource.getPath())),
                    logResource
            ));

            strippedLogs.put(woodType, new WoodenStrippedLogResource(
                    new ResourceLocation(strippedLogResource.getNamespace(), Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName(strippedLogResource.getPath(), "top"))),
                    new ResourceLocation(strippedLogResource.getNamespace(), Util.toPath(ModelProvider.BLOCK_FOLDER, strippedLogResource.getPath())),
                    strippedLogResource
            ));

            slabs.put(woodType, new WoodenSlabResource(planksTexture, planksTexture, planksTexture, slabResource));
        });

        PLANKS = Collections.unmodifiableMap(planks);
        LOGS = Collections.unmodifiableMap(logs);
        STRIPPED_LOGS = Collections.unmodifiableMap(strippedLogs);
        SLABS = Collections.unmodifiableMap(slabs);
    }

    private BiomesOPlentyWoodenResources() {
    }
}
