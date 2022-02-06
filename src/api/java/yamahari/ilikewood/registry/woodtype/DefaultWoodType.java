package yamahari.ilikewood.registry.woodtype;

import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class DefaultWoodType implements IWoodType {
    public static final Map<WoodenBlockType, Properties> DEFAULT_BLOCK_PROPERTIES = createDefaultBlockProperties();
    public static final Map<WoodenItemType, Properties> DEFAULT_ITEM_PROPERTIES = createDefaultItemProperties();
    // TODO add log pile once added back
    public static final Set<WoodenBlockType> DEFAULT_BLOCK_TYPES = Collections.unmodifiableSet(WoodenBlockType
        .getAll()
        .filter(blockType -> !blockType.equals(WoodenBlockType.LOG_PILE))
        .collect(Collectors.toSet()));
    public static final Set<WoodenItemType> DEFAULT_ITEM_TYPES =
        Collections.unmodifiableSet(WoodenItemType.getAll().collect(Collectors.toSet()));
    public static final Set<WoodenTieredItemType> DEFAULT_TIERED_ITEM_TYPES =
        Collections.unmodifiableSet(WoodenTieredItemType.getAll().collect(Collectors.toSet()));
    public static final Set<WoodenEntityType> DEFAULT_ENTITY_TYPES =
        Collections.unmodifiableSet(WoodenEntityType.getAll().collect(Collectors.toSet()));
    public static final Set<WoodenBlockType> DEFAULT_BUILTIN_BLOCK_TYPES = Collections.emptySet();
    public static final Set<WoodenItemType> DEFAULT_BUILTIN_ITEM_TYPES = Collections.emptySet();
    private final String modId;
    private final String name;
    private final Colors colors;

    public DefaultWoodType(final String modId, final String name, final Colors colors) {
        this.modId = modId;
        this.name = name;
        this.colors = colors;
    }

    private static Map<WoodenBlockType, Properties> createDefaultBlockProperties() {
        final Map<WoodenBlockType, Properties> properties = new HashMap<>();

        properties.put(WoodenBlockType.PANELS, new Properties(300));
        properties.put(WoodenBlockType.PANELS_STAIRS, new Properties(300));
        properties.put(WoodenBlockType.PANELS_SLAB, new Properties(150));
        properties.put(WoodenBlockType.BARREL, new Properties(300));
        WoodenBlockType.getBeds().forEach(bedBlockType -> properties.put(bedBlockType, new Properties(400)));
        properties.put(WoodenBlockType.BOOKSHELF, new Properties(300));
        properties.put(WoodenBlockType.COMPOSTER, new Properties(300));
        properties.put(WoodenBlockType.CRAFTING_TABLE, new Properties(300));
        properties.put(WoodenBlockType.CHEST, new Properties(300));
        properties.put(WoodenBlockType.SAWMILL, new Properties(300));
        properties.put(WoodenBlockType.LECTERN, new Properties(300));
        properties.put(WoodenBlockType.LADDER, new Properties(300));
        properties.put(WoodenBlockType.SCAFFOLDING, new Properties(100));
        properties.put(WoodenBlockType.SOUL_TORCH, new Properties(400));
        properties.put(WoodenBlockType.TORCH, new Properties(400));
        properties.put(WoodenBlockType.WALL_TORCH, new Properties(400));
        properties.put(WoodenBlockType.WALL_SOUL_TORCH, new Properties(400));
        properties.put(WoodenBlockType.LOG_PILE, new Properties(400));
        properties.put(WoodenBlockType.POST, new Properties(300));
        properties.put(WoodenBlockType.STRIPPED_POST, new Properties(300));
        properties.put(WoodenBlockType.WALL, new Properties(300));
        properties.put(WoodenBlockType.CHAIR, new Properties(300));
        properties.put(WoodenBlockType.TABLE, new Properties(300));
        properties.put(WoodenBlockType.STOOL, new Properties(300));
        properties.put(WoodenBlockType.SINGLE_DRESSER, new Properties(300));

        return properties;
    }

    private static Map<WoodenItemType, Properties> createDefaultItemProperties() {
        final Map<WoodenItemType, Properties> properties = new HashMap<>();

        properties.put(WoodenItemType.STICK, new Properties(100));
        properties.put(WoodenItemType.BOW, new Properties(300));
        properties.put(WoodenItemType.CROSSBOW, new Properties(300));
        properties.put(WoodenItemType.FISHING_ROD, new Properties(300));
        properties.put(WoodenItemType.ITEM_FRAME, new Properties(-1));

        return properties;
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
    public Properties getProperties(final WoodenBlockType blockType) {
        return DEFAULT_BLOCK_PROPERTIES.get(blockType);
    }

    @Override
    public Properties getProperties(final WoodenItemType itemType) {
        return DEFAULT_ITEM_PROPERTIES.get(itemType);
    }

    @Override
    public Set<WoodenBlockType> getBlockTypes() {
        return DEFAULT_BLOCK_TYPES;
    }

    @Override
    public Set<WoodenItemType> getItemTypes() {
        return DEFAULT_ITEM_TYPES;
    }

    @Override
    public Set<WoodenEntityType> getEntityTypes() {
        return DEFAULT_ENTITY_TYPES;
    }

    @Override
    public Set<WoodenTieredItemType> getTieredItemTypes() {
        return DEFAULT_TIERED_ITEM_TYPES;
    }

    @Override
    public Set<WoodenBlockType> getBuiltinBlockTypes() {
        return DEFAULT_BUILTIN_BLOCK_TYPES;
    }

    @Override
    public Set<WoodenItemType> getBuiltinItemTypes() {
        return DEFAULT_BUILTIN_ITEM_TYPES;
    }

    @Override
    public Colors getColors() {
        return this.colors;
    }
}
