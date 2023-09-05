package yamahari.ilikewood.data.loot;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public final class SawmillLoot extends VanillaBlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.SAWMILL).collect(Collectors.toList());
    }

    @Override
    public void generate() {
        ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.SAWMILL)
            .forEach(block -> this.add(block, b -> createSinglePropConditionTable(b, WoodenSawmillBlock.MODEL, WoodenSawmillBlock.WoodenSawmillModel.BOTTOM_LEFT)));
    }
}