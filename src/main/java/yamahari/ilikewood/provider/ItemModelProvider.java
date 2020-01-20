package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    this.getBuilder(block.getRegistryName().getPath())
                            .parent(new ModelFile.UncheckedModelFile(modLoc(StringUtils.joinWith("/", BLOCK_FOLDER, "panels", type))));
                }
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    this.getBuilder(block.getRegistryName().getPath())
                            .parent(new ModelFile.UncheckedModelFile(modLoc(StringUtils.joinWith("/", BLOCK_FOLDER, "panels", "stairs", type))));
                }
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    this.getBuilder(block.getRegistryName().getPath())
                            .parent(new ModelFile.UncheckedModelFile(modLoc(StringUtils.joinWith("/", BLOCK_FOLDER, "panels", "slab", type))));
                }
        );

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    this.getBuilder(block.getRegistryName().getPath())
                            .parent(new ModelFile.UncheckedModelFile(modLoc(StringUtils.joinWith("/", BLOCK_FOLDER, "barrel", type))));
                }
        );
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Models";
    }
}
