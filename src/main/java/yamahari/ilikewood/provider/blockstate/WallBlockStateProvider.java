package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class WallBlockStateProvider
    extends AbstractBlockStateProvider
{
    public WallBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.WALL);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.WALL.getName());
        final var log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
        final var strippedLog = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();

        final var post = this.models().wallPost(Util.toPath(path, woodType.getModId(), "post", name), log);
        final var side = this.models().wallSide(Util.toPath(path, woodType.getModId(), "side", name), strippedLog);
        final var sideTall = this.models().wallSideTall(Util.toPath(path, woodType.getModId(), "side_tall", name), strippedLog);

        this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
            .texture("post", log)
            .texture("side", strippedLog);

        this.wallBlock((WallBlock) block, post, side, sideTall);
    }
}
