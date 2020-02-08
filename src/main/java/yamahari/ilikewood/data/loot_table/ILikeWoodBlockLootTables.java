package yamahari.ilikewood.data.loot_table;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.chest.WoodenChestBlocks;
import yamahari.ilikewood.objectholder.composter.WoodenComposterBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Util;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ILikeWoodBlockLootTables extends BlockLootTables {
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.of(WoodenPanelsBlocks.class, WoodenPanelsSlabBlocks.class, WoodenPanelsStairsBlocks.class, WoodenBarrelBlocks.class, WoodenBookshelfBlocks.class, WoodenChestBlocks.class, WoodenComposterBlocks.class)
                .map(Util::getBlocks)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        Util.getBlocks(WoodenPanelsSlabBlocks.class)
                .forEach(block -> this.registerLootTable(block, BlockLootTables::func_218513_d));

        Stream.of(WoodenPanelsBlocks.class, WoodenPanelsStairsBlocks.class)
                .forEach(blockClass -> Util.getBlocks(blockClass).forEach(this::func_218492_c));

        Stream.of(WoodenBarrelBlocks.class, WoodenChestBlocks.class)
                .forEach(blockClass -> Util.getBlocks(blockClass).forEach(
                        block -> this.registerLootTable(block, BlockLootTables::func_218481_e)));

        Util.getBlocks(WoodenBookshelfBlocks.class).forEach(
                block -> this.registerLootTable(block, b -> func_218530_a(b, Items.BOOK, ConstantRange.of(3)))
        );

        Util.getBlocks(WoodenComposterBlocks.class).forEach(
                block -> this.registerLootTable(block, b -> LootTable.builder().addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(block).acceptFunction(ExplosionDecay.func_215863_b()))).addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(BlockStateProperty.builder(b).func_227567_a_(StatePropertiesPredicate.Builder.func_227191_a_().func_227192_a_(ComposterBlock.field_220298_a, 8)))))
        );
    }
}
