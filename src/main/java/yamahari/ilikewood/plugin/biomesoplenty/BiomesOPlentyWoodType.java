package yamahari.ilikewood.plugin.biomesoplenty;

import net.minecraft.block.AbstractBlock;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public final class BiomesOPlentyWoodType implements IWoodType {
    private final String name;
    private final Map<WoodenObjectType, Properties> properties;
    private final AbstractBlock.Properties panelProperties;

    public BiomesOPlentyWoodType(final String name, final AbstractBlock.Properties panelProperties) {
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
        return Constants.MOD_ID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Properties getProperties(final WoodenObjectType woodenObjectType) {
        return this.properties.get(woodenObjectType);
    }

    @Override
    public float getEnchantingPowerBonus() {
        return this.getName().equals(Constants.MAGIC) ? 15.F : 1.F;
    }
}
