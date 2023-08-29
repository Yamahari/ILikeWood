package yamahari.ilikewood.data.loot;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public final class DropSelfLoot extends VanillaBlockLoot {
    private final WoodenBlockType blockType;

    public DropSelfLoot(final WoodenBlockType blockType) {
        this.blockType = blockType;
    }

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(this.blockType).collect(Collectors.toList());
    }


    @Override
    public void generate(@Nonnull final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        ILikeWood.BLOCK_REGISTRY.getObjects(this.blockType).forEach(this::dropSelf);
    }
}
