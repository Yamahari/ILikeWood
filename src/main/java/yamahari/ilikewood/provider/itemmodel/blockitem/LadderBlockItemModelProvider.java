package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class LadderBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public LadderBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.LADDER);
    }

    @Override
    protected void registerModel(final Block block) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        this
            .getBuilder(block.getRegistryName().getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
            .texture("layer0", modLoc(Util.toPath(BLOCK_FOLDER, WoodenObjectTypes.LADDER.getName(), woodType)));
    }
}
