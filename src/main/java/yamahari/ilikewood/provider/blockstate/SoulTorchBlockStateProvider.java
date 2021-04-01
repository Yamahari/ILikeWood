package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public class SoulTorchBlockStateProvider extends AbstractBlockStateProvider {
    public SoulTorchBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.SOUL_TORCH);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectTypes.TORCH.getName());
        final ModelFile template = this
            .models()
            .withExistingParent(Util.toPath(ModelProvider.BLOCK_FOLDER,
                WoodenObjectTypes.SOUL_TORCH.getName(),
                woodType), modLoc(Util.toPath(path, "template")))
            .texture("torch", modLoc(Util.toPath(path, woodType)))
            .texture("fire", modLoc(Util.toPath(path, "soul_fire")))
            .texture("coal", modLoc(Util.toPath(path, "soul_coal")));

        this.simpleBlock(block, template);
    }
}
