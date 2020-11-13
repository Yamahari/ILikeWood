package yamahari.ilikewood.util;

import yamahari.ilikewood.registry.woodtype.IWoodType;

public class DummyWoodType implements IWoodType {
    private final Properties properties = new Properties(-1);

    @Override
    public String getModId() {
        return Constants.MOD_ID;
    }

    @Override
    public String getName() {
        return "dummy";
    }

    @Override
    public Properties getProperties(final WoodenObjectType woodenObjectType) {
        return this.properties;
    }
}
