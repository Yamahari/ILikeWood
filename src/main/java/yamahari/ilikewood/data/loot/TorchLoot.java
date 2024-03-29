package yamahari.ilikewood.data.loot;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TorchLoot extends VanillaBlockLoot {
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ILikeWood.BLOCK_REGISTRY.getObjects(Stream.of(WoodenBlockType.TORCH, WoodenBlockType.WALL_TORCH, WoodenBlockType.SOUL_TORCH, WoodenBlockType.WALL_SOUL_TORCH))
            .collect(Collectors.toList());
    }

    @Override
    public void generate() {
        ILikeWood.BLOCK_REGISTRY.getObjects(Stream.of(WoodenBlockType.TORCH, WoodenBlockType.SOUL_TORCH)).forEach(this::dropSelf);
    }

}
