package yamahari.ilikewood.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import org.apache.commons.lang3.StringUtils;

public final class Util {
    private Util() {
    }

    public static String toRegistryName(final String... elements) {
        return StringUtils.join(elements, "_");
    }

    public static String toPath(final String... elements) {
        return StringUtils.join(elements, "/");
    }

    private static ResourceLocation getResource(final WoodType woodType, final String name) {
        final String path = toPath(ModelProvider.BLOCK_FOLDER, String.format(name, woodType.getName()));
        return woodType.getModId().equals(Constants.MOD_ID) ? new ResourceLocation(path) : new ResourceLocation(woodType.getModId(), path);
    }

    public static ResourceLocation getPlanks(final WoodType woodType) {
        return getResource(woodType, "%s_planks");
    }

    public static ResourceLocation getLog(final WoodType woodType) {
        switch (woodType) {
            case CRIMSON:
            case WARPED:
                return getResource(woodType, "%s_stem");
            default:
                return getResource(woodType, "%s_log");
        }
    }

    public static ResourceLocation getStrippedLog(final WoodType woodType) {
        switch (woodType) {
            case CRIMSON:
            case WARPED:
                return getResource(woodType, "stripped_%s_stem");
            default:
                return getResource(woodType, "stripped_%s_log");
        }
    }
}
