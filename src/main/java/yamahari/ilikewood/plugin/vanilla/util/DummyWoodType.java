package yamahari.ilikewood.plugin.vanilla.util;

import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import java.util.Set;

public final class DummyWoodType implements IWoodType {
    @Override
    public String getModId() {
        return Constants.MOD_ID;
    }

    @Override
    public String getName() {
        return "dummy";
    }

    @Override
    public Properties getProperties(final WoodenBlockType blockType) {
        throw new RuntimeException("");
    }

    @Override
    public Properties getProperties(final WoodenItemType itemType) {
        throw new RuntimeException("");
    }

    @Override
    public Set<WoodenBlockType> getBlockTypes() {
        throw new RuntimeException("");
    }

    @Override
    public Set<WoodenItemType> getItemTypes() {
        throw new RuntimeException("");
    }

    @Override
    public Set<WoodenTieredItemType> getTieredItemTypes() {
        throw new RuntimeException("");
    }

    @Override
    public Set<WoodenBlockType> getBuiltinBlockTypes() {
        throw new RuntimeException("");
    }

    @Override
    public Set<WoodenItemType> getBuiltinItemTypes() {
        throw new RuntimeException("");
    }
}
