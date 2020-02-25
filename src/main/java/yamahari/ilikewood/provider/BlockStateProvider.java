package yamahari.ilikewood.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public BlockStateProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    public static ResourceLocation getPlanks(final WoodType woodType) {
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName(woodType.toString(), "planks"));
        return woodType.getModId().equals(Constants.MOD_ID) ? new ResourceLocation(path) : new ResourceLocation(woodType.getModId(), path);
    }

    @Override
    protected void registerStatesAndModels() {
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS).forEach(block -> this.simpleBlock(block, this.templateWithPlanks(block, "", WoodenObjectType.PANELS)));
        WoodenBlocks.getBlocks(WoodenObjectType.STAIRS).forEach(block -> {
            final ModelFile stairs = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
            final ModelFile stairsInner = this.templateWithPlanks(block, "/inner", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
            final ModelFile stairsOuter = this.templateWithPlanks(block, "/outer", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);

            this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.SLAB).forEach(block -> {
            final String name = ((IWooden) block).getWoodType().toString();
            final ModelFile slabBottom = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
            final ModelFile slabTop = this.templateWithPlanks(block, "/top", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
            final ModelFile slabDouble = new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", name)));

            this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.BARREL).forEach(block -> {
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.BARREL.toString(), "%s", ((IWooden) block).getWoodType().toString());
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
        });
        WoodenBlocks.getBlocks(WoodenObjectType.BOOKSHELF).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final String name = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.BOOKSHELF.toString(), name);
            final ResourceLocation planks = getPlanks(woodType);

            this.simpleBlock(block, this.models().cubeColumn(path, modLoc(path), planks));
        });
        WoodenBlocks.getBlocks(WoodenObjectType.CHEST).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final String name = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.CHEST.toString(), name);
            final ResourceLocation planks = getPlanks(woodType);

            this.simpleBlock(block, this.models().getBuilder(path)
                    .texture("particle", planks));
        });
        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.COMPOSTER.toString());
            final ModelFile template = this.models()
                    .withExistingParent(Util.toPath(path, woodType), modLoc(Util.toPath(path, "template")))
                    .texture("top", Util.toPath(path, "top", woodType))
                    .texture("side", Util.toPath(path, "side", woodType))
                    .texture("bottom", Util.toPath(path, "bottom", woodType));

            final MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block)
                    .part()
                    .modelFile(template)
                    .addModel()
                    .end();

            IntStream.range(1, 9).forEach(level -> {
                final ModelFile content = new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, String.format("composter_contents%s", level != 8 ? level : "_ready"))));
                builder.part()
                        .modelFile(content)
                        .addModel()
                        .condition(ComposterBlock.LEVEL, level)
                        .end();
            });
        });
        WoodenBlocks.getBlocks(WoodenObjectType.WALL).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final String name = woodType.toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.WALL.toString());
            final ResourceLocation postTexture;
            final ResourceLocation sideTexture;
            switch (woodType.getModId()) {
                case Constants.MOD_ID:
                    postTexture = mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName(name, "log")));
                    sideTexture = mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName("stripped", name, "log")));
                    break;
                case Constants.BOP_MOD_ID:
                    postTexture = new ResourceLocation(Constants.BOP_MOD_ID, Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName(name, "log")));
                    sideTexture = new ResourceLocation(Constants.BOP_MOD_ID, Util.toPath(ModelProvider.BLOCK_FOLDER, Util.toRegistryName("stripped", name, "log")));
                    break;
                default:
                    postTexture = null;
                    sideTexture = null;
                    break;
            }
            assert postTexture != null;
            assert sideTexture != null;
            final ModelFile post = this.models().wallPost(Util.toPath(path, "post", name), postTexture);
            final ModelFile side = this.models().wallSide(Util.toPath(path, "side", name), sideTexture);
            this.models()
                    .withExistingParent(Util.toPath(path, "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("post", postTexture)
                    .texture("side", sideTexture);

            this.wallBlock((WallBlock) block, post, side);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.LADDER).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.LADDER.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, woodType), modLoc(Util.toPath(path, "template")), "ladder", modLoc(Util.toPath(path, woodType)));

            this.horizontalBlock(block, template);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.TORCH).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.TORCH.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, woodType), modLoc(Util.toPath(path, "template")), "torch", modLoc(Util.toPath(path, woodType)));
            this.simpleBlock(block, template);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.WALL_TORCH).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.TORCH.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, "wall", woodType), modLoc(Util.toPath(path, "wall", "template")), "torch", modLoc(Util.toPath(path, woodType)));
            this.horizontalBlock(block, template, 90);

        });
        WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE).forEach(block -> this.simpleBlock(block, this.templateWithPlanks(block, "", WoodenObjectType.CRAFTING_TABLE)));
        WoodenBlocks.getBlocks(WoodenObjectType.SCAFFOLDING).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.SCAFFOLDING.toString());
            this.getVariantBuilder(block).forAllStates(blockState -> {
                final String stable = blockState.get(ScaffoldingBlock.field_220120_c) ? "unstable" : "stable";
                final ModelFile template = this.models()
                        .withExistingParent(Util.toPath(path, stable, woodType), modLoc(Util.toPath(path, stable, "template")))
                        .texture("top", modLoc(Util.toPath(path, "top", woodType)))
                        .texture("side", modLoc(Util.toPath(path, "side", woodType)))
                        .texture("bottom", modLoc(Util.toPath(path, "bottom", woodType)));
                return ConfiguredModel.builder().modelFile(template).build();
            });
        });
        WoodenBlocks.getBlocks(WoodenObjectType.LECTERN).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final String name = ((IWooden) block).getWoodType().toString();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.LECTERN.toString());
            final ResourceLocation planks = getPlanks(woodType);
            final ModelFile template = this.models()
                    .withExistingParent(Util.toPath(path, name), modLoc(Util.toPath(path, "template")))
                    .texture("top", modLoc(Util.toPath(path, "top", name)))
                    .texture("sides", modLoc(Util.toPath(path, "sides", name)))
                    .texture("bottom", planks)
                    .texture("base", modLoc(Util.toPath(path, "base", name)))
                    .texture("front", modLoc(Util.toPath(path, "front", name)));

            this.horizontalBlock(block, template);
        });
    }

    private ModelFile templateWithPlanks(final Block block, final String nested, final WoodenObjectType... objectTypes) {
        final WoodType woodType = ((IWooden) block).getWoodType();
        final String name = ((IWooden) block).getWoodType().toString();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, Arrays.stream(objectTypes).map(Objects::toString).collect(Collectors.joining("/")));
        final ResourceLocation planks = getPlanks(woodType);

        return this.models().singleTexture(Util.toPath(path + nested, name), modLoc(Util.toPath(path + nested, "template")), "planks", planks);
    }

    @Override
    public String getName() {
        return "I Like Wood - Blockstates";
    }
}
