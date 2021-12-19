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
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ILikeWoodBlockLootTables extends BlockLootTables {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        // TODO remove LOG_PILE filter once log piles are added back!
        return ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.getAll().filter(blockType -> !blockType.equals(WoodenBlockType.LOG_PILE)))
            .collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.PANELS_SLAB)
            .forEach(block -> this.add(block, BlockLootTables::createSlabItemTable));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.PANELS,
                WoodenBlockType.PANELS_STAIRS,
                WoodenBlockType.WALL,
                WoodenBlockType.LADDER,
                WoodenBlockType.TORCH,
                WoodenBlockType.CRAFTING_TABLE,
                WoodenBlockType.SCAFFOLDING,
                WoodenBlockType.POST,
                WoodenBlockType.STRIPPED_POST,
                WoodenBlockType.SOUL_TORCH))
            .forEach(this::dropSelf);

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.BARREL, WoodenBlockType.CHEST, WoodenBlockType.LECTERN))
            .forEach(block -> this.add(block, BlockLootTables::createNameableBlockEntityTable));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.BOOKSHELF)
            .forEach(block -> this.add(block,
                b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantRange.exactly(3))));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.COMPOSTER)
            .forEach(block -> this.add(block,
                b -> LootTable
                    .lootTable()
                    .withPool(LootPool
                        .lootPool()
                        .add(ItemLootEntry.lootTableItem(block).apply(ExplosionDecay.explosionDecay())))
                    .withPool(LootPool
                        .lootPool()
                        .add(ItemLootEntry.lootTableItem(Items.BONE_MEAL))
                        .when(BlockStateProperty
                            .hasBlockStateProperties(b)
                            .setProperties(StatePropertiesPredicate.Builder
                                .properties()
                                .hasProperty(ComposterBlock.LEVEL, 8))))));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.getBeds())
            .forEach(block -> this.add(block, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD)));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.SAWMILL)
            .forEach(block -> this.add(block,
                b -> createSinglePropConditionTable(b,
                    WoodenSawmillBlock.MODEL,
                    WoodenSawmillBlock.WoodenSawmillModel.BOTTOM_LEFT)));
    }
}