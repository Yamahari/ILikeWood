package yamahari.ilikewood.provider;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import yamahari.ilikewood.data.loot_table.ILikeWoodBlockLootTables;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ILikeWoodLootTableProvider extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>
        lootTables = ImmutableList.of(Pair.of(ILikeWoodBlockLootTables::new, LootContextParamSets.BLOCK));

    public ILikeWoodLootTableProvider(final DataGenerator generator) {
        super(generator);
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTables;
    }

    @Override
    protected void validate(@Nonnull final Map<ResourceLocation, LootTable> map,
                            @Nonnull final ValidationContext context) {
        // TODO
    }

    @Override
    public String getName() {
        return "I Like Wood - Loot Tables";
    }
}
