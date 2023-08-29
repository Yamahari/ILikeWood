package yamahari.ilikewood.data.loot;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public final class BookshelfLoot extends VanillaBlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.BOOKSHELF).collect(Collectors.toList());
    }

    @Override
    public void generate(@Nonnull final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.BOOKSHELF)
            .forEach(block -> this.add(block, b -> createSingleItemTableWithSilkTouch(b, Items.BOOK, ConstantValue.exactly(3))));
    }
}
