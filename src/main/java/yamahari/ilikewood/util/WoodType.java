package yamahari.ilikewood.util;

import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class WoodType implements IWoodType {
    private final String modId;
    private final String name;
    private final Map<WoodenObjectType, Properties> properties;

    public WoodType(final String name) {
        this(Constants.MOD_ID, name);
    }

    public WoodType(final String modId, final String name) {
        this.modId = modId;
        this.name = name;

        final Map<WoodenObjectType, Properties> properties = new EnumMap<>(WoodenObjectType.class);
        // TODO SAWMILL
        Stream.of(WoodenObjectType.BARREL, WoodenObjectType.CHEST, WoodenObjectType.LECTERN, WoodenObjectType.PANELS,
                WoodenObjectType.BOOKSHELF, WoodenObjectType.COMPOSTER, WoodenObjectType.WALL, WoodenObjectType.LADDER,
                WoodenObjectType.POST, WoodenObjectType.STRIPPED_POST, WoodenObjectType.CRAFTING_TABLE, WoodenObjectType.STAIRS,
                WoodenObjectType.BOW, WoodenObjectType.CROSSBOW, WoodenObjectType.SAWMILL)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(300)));

        Stream.of(WoodenObjectType.STICK, WoodenObjectType.SCAFFOLDING)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(100)));

        Stream.of(WoodenObjectType.TORCH, WoodenObjectType.BED, WoodenObjectType.LOG_PILE)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(400)));

        Stream.of(WoodenObjectType.SLAB)
                .forEach(woodenObjectType -> properties.put(woodenObjectType, new Properties(150)));

        this.properties = Collections.unmodifiableMap(properties);
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
