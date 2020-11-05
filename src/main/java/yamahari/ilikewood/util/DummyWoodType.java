package yamahari.ilikewood.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
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
    public Properties getProperties(final WoodenObjectType objectType) {
        return this.properties;
    }

    @Override
    public AbstractBlock.Properties getPanelProperties() {
        return AbstractBlock.Properties.from(Blocks.AIR);
    }
}
