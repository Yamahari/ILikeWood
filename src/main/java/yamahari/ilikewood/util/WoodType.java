package yamahari.ilikewood.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import yamahari.ilikewood.IWoodType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class WoodType implements IWoodType {
    private final String name;
    private final AbstractBlock.Properties panelProperties;
    private final Map<WoodenObjectType, Properties> properties;
    private final Supplier<Block> planks;
    private final Supplier<Block> log;
    private final Supplier<Block> strippedLog;
    private final Supplier<Block> fence;
    private final Supplier<Block> slab;

    public WoodType(final String name, final AbstractBlock.Properties panelProperties,
                    final Supplier<Block> planks,
                    final Supplier<Block> log,
                    final Supplier<Block> strippedLog,
                    final Supplier<Block> fence,
                    final Supplier<Block> slab) {
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
        this.planks = planks;
        this.log = log;
        this.strippedLog = strippedLog;
        this.fence = fence;
        this.slab = slab;
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
    public Optional<Supplier<Block>> getPlanks() {
        return Optional.of(this.planks);
    }

    @Override
    public Optional<Supplier<Block>> getLog() {
        return Optional.of(this.log);
    }

    @Override
    public Optional<Supplier<Block>> getStrippedLog() {
        return Optional.of(this.strippedLog);
    }

    @Override
    public Optional<Supplier<Block>> getFence() {
        return Optional.of(this.fence);
    }

    @Override
    public Optional<Supplier<Block>> getSlab() {
        return Optional.of(this.slab);
    }
}
