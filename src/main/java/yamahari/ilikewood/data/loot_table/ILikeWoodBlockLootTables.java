package yamahari.ilikewood.data.loot_table;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ILikeWoodBlockLootTables extends BlockLoot {
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
            .forEach(block -> this.add(block, BlockLoot::createSlabItemTable));

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
                WoodenBlockType.SOUL_TORCH,
                WoodenBlockType.CHAIR,
                WoodenBlockType.TABLE))
            .forEach(this::dropSelf);

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.BARREL, WoodenBlockType.CHEST, WoodenBlockType.LECTERN))
            .forEach(block -> this.add(block, BlockLoot::createNameableBlockEntityTable));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.BOOKSHELF)
            .forEach(block -> this.add(block,
                b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3))));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.COMPOSTER)
            .forEach(block -> this.add(block,
                b -> LootTable
                    .lootTable()
                    .withPool(LootPool
                        .lootPool()
                        .add(LootItem.lootTableItem(block).apply(ApplyExplosionDecay.explosionDecay())))
                    .withPool(LootPool
                        .lootPool()
                        .add(LootItem.lootTableItem(Items.BONE_MEAL))
                        .when(LootItemBlockStatePropertyCondition
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