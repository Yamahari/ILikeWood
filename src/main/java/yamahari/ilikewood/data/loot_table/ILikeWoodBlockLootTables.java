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
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.stream.Collectors;

public final class ILikeWoodBlockLootTables extends BlockLootTables {
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return WoodenBlocks.REGISTRY.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        WoodenBlocks.getBlocks(WoodenObjectType.SLAB)
                .forEach(block -> this.registerLootTable(block, BlockLootTables::droppingSlab));

        WoodenBlocks.getBlocks(WoodenObjectType.PANELS, WoodenObjectType.STAIRS, WoodenObjectType.WALL, WoodenObjectType.LADDER, WoodenObjectType.TORCH, WoodenObjectType.CRAFTING_TABLE, WoodenObjectType.SCAFFOLDING, WoodenObjectType.POST, WoodenObjectType.STRIPPED_POST)
                .forEach(this::registerDropSelfLootTable);

        WoodenBlocks.getBlocks(WoodenObjectType.BARREL, WoodenObjectType.CHEST, WoodenObjectType.LECTERN)
                .forEach(block -> this.registerLootTable(block, BlockLootTables::droppingWithName));

        WoodenBlocks.getBlocks(WoodenObjectType.BOOKSHELF)
                .forEach(block -> this.registerLootTable(block, b -> droppingWithSilkTouchOrRandomly(b, Items.BOOK, ConstantRange.of(3))));

        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER)
                .forEach(block -> this.registerLootTable(block, b -> LootTable.builder().addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(block).acceptFunction(ExplosionDecay.builder()))).addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(BlockStateProperty.builder(b).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(ComposterBlock.LEVEL, 8))))));

        WoodenBlocks.getBedBlocks()
                .forEach(block -> this.registerLootTable(block, b -> droppingWhen(b, BedBlock.PART, BedPart.HEAD)));
    }
}
