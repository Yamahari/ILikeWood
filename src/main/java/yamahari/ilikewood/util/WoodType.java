package yamahari.ilikewood.util;

import net.minecraft.block.AbstractBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class WoodType implements IWoodType {
    private final String modId;
    private final String name;
    private final AbstractBlock.Properties panelProperties;
    private final Map<WoodenObjectType, Properties> properties;

    public WoodType(final String name, final AbstractBlock.Properties panelProperties) {
        this(Constants.MOD_ID, name, panelProperties);
    }

    public WoodType(final String modId, final String name, final AbstractBlock.Properties panelProperties) {
        this.modId = modId;
        this.name = name;

        final Map<WoodenObjectType, Properties> properties = new EnumMap<>(WoodenObjectType.class);

        Stream.of(WoodenObjectType.BARREL, WoodenObjectType.CHEST, WoodenObjectType.LECTERN, WoodenObjectType.PANELS,
                WoodenObjectType.BOOKSHELF, WoodenObjectType.COMPOSTER, WoodenObjectType.WALL, WoodenObjectType.LADDER,
                WoodenObjectType.POST, WoodenObjectType.STRIPPED_POST, WoodenObjectType.CRAFTING_TABLE, WoodenObjectType.STAIRS,
                WoodenObjectType.BOW, WoodenObjectType.CROSSBOW)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(300)));

        Stream.of(WoodenObjectType.STICK, WoodenObjectType.SCAFFOLDING)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(100)));

        Stream.of(WoodenObjectType.TORCH, WoodenObjectType.BED, WoodenObjectType.LOG_PILE)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(400)));

        Stream.of(WoodenObjectType.SLAB)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(150)));

        this.properties = Collections.unmodifiableMap(properties);
        this.panelProperties = panelProperties;
    }

    @Override
    public AbstractBlock.Properties getPanelProperties() {
        return this.panelProperties;
    }

    @Override
    public String getModId() {
        return this.modId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Properties getProperties(final WoodenObjectType woodenObjectType) {
        return this.properties.get(woodenObjectType);
    }
}
