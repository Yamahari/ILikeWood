package yamahari.ilikewood.data.loot_table;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Items;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.stream.Collectors;

public final class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return WoodenBlocks.REGISTRY.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        WoodenBlocks.getBlocks(WoodenObjectType.SLAB).forEach(block -> this.registerLootTable(block, net.minecraft.data.loot.BlockLootTables::func_218513_d));
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS, WoodenObjectType.STAIRS, WoodenObjectType.WALL).forEach(this::func_218492_c);
        WoodenBlocks.getBlocks(WoodenObjectType.BARREL, WoodenObjectType.CHEST).forEach(block -> this.registerLootTable(block, net.minecraft.data.loot.BlockLootTables::func_218481_e));
        WoodenBlocks.getBlocks(WoodenObjectType.BOOKSHELF).forEach(block -> this.registerLootTable(block, b -> func_218530_a(b, Items.BOOK, ConstantRange.of(3))));
        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER).forEach(block -> this.registerLootTable(block, b -> LootTable.builder().addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(block).acceptFunction(ExplosionDecay.func_215863_b()))).addLootPool(LootPool.builder().addEntry(ItemLootEntry.builder(Items.BONE_MEAL)).acceptCondition(BlockStateProperty.builder(b).func_227567_a_(StatePropertiesPredicate.Builder.func_227191_a_().func_227192_a_(ComposterBlock.field_220298_a, 8))))));

    }
}
