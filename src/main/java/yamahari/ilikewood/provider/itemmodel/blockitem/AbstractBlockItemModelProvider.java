package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import java.util.Objects;

public abstract class AbstractBlockItemModelProvider extends ItemModelProvider {
    private final WoodenObjectType objectType;

    public AbstractBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper,
                                          final WoodenObjectType objectType) {
        super(generator, Constants.MOD_ID, helper);
        this.objectType = objectType;
    }

    @Override
    protected void registerModels() {
        if (objectType == WoodenObjectTypes.BED) {
            WoodenBlocks.getBedBlocks().forEach(this::registerModel);
        } else {
            WoodenBlocks.getBlocks(this.objectType).forEach(this::registerModel);
        }
    }

    protected abstract void registerModel(Block block);

    protected void blockItem(final Block block, final String path) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        this
            .getBuilder(Objects.requireNonNull(block.getRegistryName(), "Registry name was null.").getPath())
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(BLOCK_FOLDER, path, woodType))));
    }
}
