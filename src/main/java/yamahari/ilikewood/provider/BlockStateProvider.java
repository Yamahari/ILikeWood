package yamahari.ilikewood.provider;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
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
                            StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "panels", type),
                            modLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "panels", "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    this.simpleBlock(block, model);
                }
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "panels", "stairs");
                    final ModelFile stairs = this.models().singleTexture(
                            StringUtils.joinWith("/", path, type),
                            modLoc(StringUtils.joinWith("/", path, "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile stairsInner = this.models().singleTexture(
                            StringUtils.joinWith("/", path, "inner", type),
                            modLoc(StringUtils.joinWith("/", path, "inner", "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile stairsOuter = this.models().singleTexture(
                            StringUtils.joinWith("/", path, "inner", type),
                            modLoc(StringUtils.joinWith("/", path, "inner", "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));
                    this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
                }
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "panels", "slab");
                    final ModelFile slabBottom = this.models().singleTexture(
                            StringUtils.joinWith("/", path, type),
                            modLoc(StringUtils.joinWith("/", path, "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile slabTop = this.models().singleTexture(
                            StringUtils.joinWith("/", path, "top", type),
                            modLoc(StringUtils.joinWith("/", path, "top", "template")),
                            "planks",
                            mcLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, type + "_planks")));

                    final ModelFile slabDouble = new ModelFile.UncheckedModelFile(modLoc(StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "panels", type)));

                    this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
                }
        );

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String path = StringUtils.joinWith("/", ModelProvider.BLOCK_FOLDER, "barrel", "%s", type);
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
    }

    @Override
    public String getName() {
        return "I Like Wood - Blockstates";
    }
}
