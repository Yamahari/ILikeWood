package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LecternBlockStateProvider extends AbstractBlockStateProvider {
    public LecternBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.LECTERN);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = woodType.getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LECTERN.getName());
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        final ModelFile template = this
            .models()
            .withExistingParent(Util.toPath(path, name), modLoc(Util.toPath(path, "template")))
            .texture("top", modLoc(Util.toPath(path, "top", name)))
            .texture("sides", modLoc(Util.toPath(path, "sides", name)))
            .texture("bottom", planks)
            .texture("base", modLoc(Util.toPath(path, "base", name)))
            .texture("front", modLoc(Util.toPath(path, "front", name)));

        this.horizontalBlock(block, template);
    }
}
