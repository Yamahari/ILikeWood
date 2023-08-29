package yamahari.ilikewood.provider.loot;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public final class DefaultBlockLootTableProvider extends LootTableProvider {
    private final String name;

    public DefaultBlockLootTableProvider(final DataGenerator generator, final Supplier<LootTableSubProvider> supplier, final String name) {
        // TODO check constructor
        super(generator.getPackOutput(), Collections.emptySet(), List.of(new SubProviderEntry(supplier, LootContextParamSets.BLOCK)));
        this.name = name;
    }

    @Override
    @Nonnull
    public String getName() {
        return String.format("%s - loot tables - %s", Constants.MOD_ID, this.name);
    }
}
