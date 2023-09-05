package yamahari.ilikewood.data.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public final class ComposterLoot extends VanillaBlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.COMPOSTER).collect(Collectors.toList());
    }

    @Override
    public void generate() {
        ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.COMPOSTER)
            .forEach(block -> this.add(block,
                b -> LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(block).apply(ApplyExplosionDecay.explosionDecay()))).withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.BONE_MEAL))
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(b)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ComposterBlock.LEVEL, 8))))));
    }
}
