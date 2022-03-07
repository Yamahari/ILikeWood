package yamahari.ilikewood.provider.loot;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class DefaultBlockLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>
        lootTables;
    private final String name;

    public DefaultBlockLootTableProvider(final DataGenerator generator,
                                         final Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>> supplier,
                                         final String name) {
        super(generator);
        this.lootTables = ImmutableList.of(Pair.of(supplier, LootContextParamSets.BLOCK));
        this.name = name;
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTables;
    }

    @Override
    protected void validate(@Nonnull final Map<ResourceLocation, LootTable> map,
                            @Nonnull final ValidationContext context) {
        map.forEach((location, lootTable) -> LootTables.validate(context, location, lootTable));
    }

    @Override
    public String getName() {
        return String.format("%s - loot tables - %s", Constants.MOD_ID, this.name);
    }
}
