package yamahari.ilikewood.data.loot_table;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
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
        return Stream.of(WoodenPanelsBlocks.class, WoodenPanelsSlabBlocks.class, WoodenPanelsStairsBlocks.class, WoodenBarrelBlocks.class)
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

        Util.getBlocks(WoodenBarrelBlocks.class)
                .forEach(block -> this.registerLootTable(block, BlockLootTables::func_218481_e));
    }


}
