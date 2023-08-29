package yamahari.ilikewood.data.loot;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootTable;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public final class BedLoot extends VanillaBlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).collect(Collectors.toList());
    }

    @Override
    public void generate(@Nonnull final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).forEach(block -> this.add(block, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD)));
    }
}