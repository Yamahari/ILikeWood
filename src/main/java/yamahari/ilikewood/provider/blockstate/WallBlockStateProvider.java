package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WallBlockStateProvider extends AbstractBlockStateProvider {
    public WallBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.WALL, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG));
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = woodType.getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.WALL.toString());
        final ResourceLocation log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
        final ResourceLocation strippedLog =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();

        final ModelFile post = this.models().wallPost(Util.toPath(path, "post", name), log);
        final ModelFile side = this.models().wallSide(Util.toPath(path, "side", name), strippedLog);
        final ModelFile sideTall = this.models().wallSideTall(Util.toPath(path, "side_tall", name), strippedLog);

        this
            .models()
            .withExistingParent(Util.toPath(path, "inventory", name),
                modLoc(Util.toPath(path, "inventory", "template")))
            .texture("post", log)
            .texture("side", strippedLog);

        this.wallBlock((WallBlock) block, post, side, sideTall);
    }
}
