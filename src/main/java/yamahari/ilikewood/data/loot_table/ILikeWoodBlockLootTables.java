package yamahari.ilikewood.data.loot_table;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.state.properties.BedPart;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.ILikeWoodBlockRegistry;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public final class ILikeWoodBlockLootTables extends BlockLootTables {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWoodBlockRegistry.REGISTRY.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        WoodenBlocks.getBlocks(WoodenObjectTypes.SLAB)
            .forEach(block -> this.registerLootTable(block, BlockLootTables::droppingSlab));

        WoodenBlocks
            .getBlocks(WoodenObjectTypes.PANELS,
                WoodenObjectTypes.STAIRS,
                WoodenObjectTypes.WALL,
                WoodenObjectTypes.LADDER,
                WoodenObjectTypes.TORCH,
                WoodenObjectTypes.CRAFTING_TABLE,
                WoodenObjectTypes.SCAFFOLDING,
                WoodenObjectTypes.POST,
                WoodenObjectTypes.STRIPPED_POST,
                WoodenObjectTypes.SOUL_TORCH)
            .forEach(this::registerDropSelfLootTable);

        WoodenBlocks.getBlocks(WoodenObjectTypes.BARREL, WoodenObjectTypes.CHEST, WoodenObjectTypes.LECTERN)
            .forEach(block -> this.registerLootTable(block, BlockLootTables::droppingWithName));

        WoodenBlocks.getBlocks(WoodenObjectTypes.BOOKSHELF)
            .forEach(block -> this.registerLootTable(block,
                b -> droppingWithSilkTouchOrRandomly(b, Items.BOOK, ConstantRange.of(3))));

        WoodenBlocks.getBlocks(WoodenObjectTypes.COMPOSTER)
            .forEach(block -> this.registerLootTable(block,
                b -> LootTable
                    .builder()
                    .addLootPool(LootPool
                        .builder()
                        .addEntry(ItemLootEntry.builder(block).acceptFunction(ExplosionDecay.builder())))
                    .addLootPool(LootPool
                        .builder()
                        .addEntry(ItemLootEntry.builder(Items.BONE_MEAL))
                        .acceptCondition(BlockStateProperty
                            .builder(b)
                            .fromProperties(StatePropertiesPredicate.Builder
                                .newBuilder()
                                .withIntProp(ComposterBlock.LEVEL, 8))))));

        WoodenBlocks
            .getBedBlocks()
            .forEach(block -> this.registerLootTable(block, b -> droppingWhen(b, BedBlock.PART, BedPart.HEAD)));

        WoodenBlocks.getBlocks(WoodenObjectTypes.SAWMILL)
            .forEach(block -> this.registerLootTable(block,
                b -> droppingWhen(b, WoodenSawmillBlock.MODEL, WoodenSawmillBlock.WoodenSawmillModel.BOTTOM_LEFT)));
    }
}
