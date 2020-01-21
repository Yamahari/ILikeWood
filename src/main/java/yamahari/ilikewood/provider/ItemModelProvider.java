package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    private void blockItem(final Block block, final String path) {
        final String type = ((IWooden) block).getWoodType().toString();
        this.getBuilder(block.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(BLOCK_FOLDER, path, type))));
    }

    @Override
    protected void registerModels() {
        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> this.blockItem(block, WoodenObjectType.PANELS.toString())
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> this.blockItem(block, Util.toPath(WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString()))
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> this.blockItem(block, Util.toPath(WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString()))
        );

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> this.blockItem(block, WoodenObjectType.BARREL.toString())
        );

        Util.getBlocks(WoodenBookshelfBlocks.class).forEach(
                block -> this.blockItem(block, WoodenObjectType.BOOKSHELF.toString())
        );
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Models";
    }
}
