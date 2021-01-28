package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

public final class LadderBlockStateProvider extends AbstractBlockStateProvider {
    public LadderBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.LADDER);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.LADDER.toString());
        final ModelFile template = this
            .models()
            .singleTexture(Util.toPath(path, woodType),
                modLoc(Util.toPath(path, "template")),
                "ladder",
                modLoc(Util.toPath(path, woodType)));

        this.horizontalBlock(block, template);
    }
}
