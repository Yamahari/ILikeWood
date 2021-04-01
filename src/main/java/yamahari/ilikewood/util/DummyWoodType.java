package yamahari.ilikewood.util;

import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

import java.util.HashSet;
import java.util.Set;

public class DummyWoodType implements IWoodType {
    private final Properties properties = new Properties(-1);
    private static final Set<WoodenObjectType> OBJECT_TYPES = new HashSet<>();
    private static final Set<WoodenTieredObjectType> TIERED_OBJECT_TYPES = new HashSet<>();

    @Override
    public String getModId() {
        return Constants.MOD_ID;
    }

    @Override
    public String getName() {
        return "dummy";
    }

    @Override
    public Set<WoodenObjectType> getObjectTypes() {
        return OBJECT_TYPES;
    }

    @Override
    public Set<WoodenTieredObjectType> getTieredObjectTypes() {
        return TIERED_OBJECT_TYPES;
    }

    @Override
    public Properties getProperties(final WoodenObjectType woodenObjectType) {
        return this.properties;
    }
}
