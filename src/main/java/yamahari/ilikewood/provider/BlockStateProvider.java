package yamahari.ilikewood.provider;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public BlockStateProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {

        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final ModelFile model = this.models().singleTexture(
                            Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", type),
                            modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    this.simpleBlock(block, model);
                }
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "stairs");
                    final ModelFile stairs = this.models().singleTexture(
                            Util.toPath(path, type),
                            modLoc(Util.toPath(path, "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile stairsInner = this.models().singleTexture(
                            Util.toPath(path, "inner", type),
                            modLoc(Util.toPath(path, "inner", "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile stairsOuter = this.models().singleTexture(
                            Util.toPath(path, "outer", type),
                            modLoc(Util.toPath(path, "outer", "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));
                    this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
                }
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", "slab");
                    final ModelFile slabBottom = this.models().singleTexture(
                            Util.toPath(path, type),
                            modLoc(Util.toPath(path, "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile slabTop = this.models().singleTexture(
                            Util.toPath(path, "top", type),
                            modLoc(Util.toPath(path, "top", "template")),
                            "planks",
                            mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile slabDouble = new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", type)));

                    this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
                }
        );

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> {
                    final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, "barrel", "%s", ((IWooden) block).getWoodType().toString());
                    this.directionalBlock(block,
                            state -> {
                                final boolean open = state.get(BarrelBlock.PROPERTY_OPEN);
                                return this.models().cubeBottomTop(
                                        String.format(path, (open ? "open" : "")),
                                        modLoc(String.format(path, "side")),
                                        modLoc(String.format(path, "bottom")),
                                        modLoc(String.format(path, "top" + (open ? "/open" : "")))
                                );
                            }
                    );
                }
        );

        Util.getBlocks(WoodenBookshelfBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, "bookshelf", type);
                    this.simpleBlock(block,
                            this.models().cubeColumn(
                                    path,
                                    modLoc(path),
                                    mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, type + "_planks"))
                            ));
                }
        );
    }

    @Override
    public String getName() {
        return "I Like Wood - Blockstates";
    }
}
