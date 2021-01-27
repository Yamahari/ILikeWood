package yamahari.ilikewood.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO do we need Util.getBlocksWith/Util.getItemsWith? Block/Item registry already filters wood types that dont meet predicates
public final class ILikeWoodBlockStateProvider extends BlockStateProvider {
    public ILikeWoodBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }


    @Override
    protected void registerStatesAndModels() {
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS).forEach(block -> this.simpleBlock(block, this.templateWithPlanks(block, "", WoodenObjectType.PANELS)));

        Util.getBlocksWith(WoodenObjectType.STAIRS, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final ModelFile stairs = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
            final ModelFile stairsInner = this.templateWithPlanks(block, "/inner", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);
            final ModelFile stairsOuter = this.templateWithPlanks(block, "/outer", WoodenObjectType.PANELS, WoodenObjectType.STAIRS);

            this.stairsBlock((StairsBlock) block, stairs, stairsInner, stairsOuter);
        });

        Util.getBlocksWith(WoodenObjectType.SLAB, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String name = ((IWooden) block).getWoodType().getName();
            final ModelFile slabBottom = this.templateWithPlanks(block, "", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
            final ModelFile slabTop = this.templateWithPlanks(block, "/top", WoodenObjectType.PANELS, WoodenObjectType.SLAB);
            final ModelFile slabDouble = new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "panels", name)));

            this.slabBlock((SlabBlock) block, slabBottom, slabTop, slabDouble);
        });

        Util.getBlocksWith(WoodenObjectType.BARREL, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.BARREL.toString(), "%s", ((IWooden) block).getWoodType().getName());
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

        Util.getBlocksWith(WoodenObjectType.BOOKSHELF, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.BOOKSHELF.toString(), name);

            final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

            this.simpleBlock(block, this.models().cubeColumn(path, modLoc(path), planks));
        });

        Util.getBlocksWith(WoodenObjectType.CHEST, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.CHEST.toString(), name);
            final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

            this.simpleBlock(block, this.models().getBuilder(path)
                    .texture("particle", planks));
        });

        Util.getBlocksWith(WoodenObjectType.COMPOSTER, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
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

        Util.getBlocksWith(WoodenObjectType.WALL, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = woodType.getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.WALL.toString());
            final ResourceLocation log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
            final ResourceLocation strippedLog = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();

            final ModelFile post = this.models().wallPost(Util.toPath(path, "post", name), log);
            final ModelFile side = this.models().wallSide(Util.toPath(path, "side", name), strippedLog);
            final ModelFile sideTall = this.models().wallSideTall(Util.toPath(path, "side_tall", name), strippedLog);

            this.models()
                    .withExistingParent(Util.toPath(path, "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("post", log)
                    .texture("side", strippedLog);

            this.wallBlock((WallBlock) block, post, side, sideTall);
        });

        Util.getBlocksWith(WoodenObjectType.LADDER, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.LADDER.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, woodType), modLoc(Util.toPath(path, "template")), "ladder", modLoc(Util.toPath(path, woodType)));

            this.horizontalBlock(block, template);
        });

        Util.getBlocksWith(WoodenObjectType.TORCH, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.TORCH.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, woodType), modLoc(Util.toPath(path, "template")), "torch", modLoc(Util.toPath(path, woodType)));
            this.simpleBlock(block, template);
        });

        Util.getBlocksWith(WoodenObjectType.WALL_TORCH, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.TORCH.toString());
            final ModelFile template = this.models()
                    .singleTexture(Util.toPath(path, "wall", woodType), modLoc(Util.toPath(path, "wall", "template")), "torch", modLoc(Util.toPath(path, woodType)));
            this.horizontalBlock(block, template, 90);

        });

        Util.getBlocksWith(WoodenObjectType.CRAFTING_TABLE, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> this.simpleBlock(block, this.templateWithPlanks(block, "", WoodenObjectType.CRAFTING_TABLE)));

        Util.getBlocksWith(WoodenObjectType.SCAFFOLDING, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.SCAFFOLDING.toString());
            this.getVariantBuilder(block).forAllStates(blockState -> {
                final String stable = blockState.get(ScaffoldingBlock.BOTTOM) ? "unstable" : "stable";
                final ModelFile template = this.models()
                        .withExistingParent(Util.toPath(path, stable, woodType), modLoc(Util.toPath(path, stable, "template")))
                        .texture("top", modLoc(Util.toPath(path, "top", woodType)))
                        .texture("side", modLoc(Util.toPath(path, "side", woodType)))
                        .texture("bottom", modLoc(Util.toPath(path, "bottom", woodType)));
                return ConfiguredModel.builder().modelFile(template).build();
            });
        });

        Util.getBlocksWith(WoodenObjectType.LECTERN, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = woodType.getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.LECTERN.toString());
            final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
            final ModelFile template = this.models()
                    .withExistingParent(Util.toPath(path, name), modLoc(Util.toPath(path, "template")))
                    .texture("top", modLoc(Util.toPath(path, "top", name)))
                    .texture("sides", modLoc(Util.toPath(path, "sides", name)))
                    .texture("bottom", planks)
                    .texture("base", modLoc(Util.toPath(path, "base", name)))
                    .texture("front", modLoc(Util.toPath(path, "front", name)));

            this.horizontalBlock(block, template);
        });

        Util.getBlocksWith(WoodenObjectType.POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = woodType.getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.POST.toString());
            final ResourceLocation log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getSideTexture();
            final ModelFile post = this.models()
                    .withExistingParent(Util.toPath(path, name), modLoc(Util.toPath(path, "template")))
                    .texture("wall", log);
            final ModelFile side = this.models()
                    .withExistingParent(Util.toPath(path, "side", name), modLoc(Util.toPath(path, "side", "template")))
                    .texture("texture", modLoc(Util.toPath(path, name)));

            this.models()
                    .withExistingParent(Util.toPath(path, "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("wall", log);

            this.postBlock(this.getMultipartBuilder(block), post, side);
        });

        Util.getBlocksWith(WoodenObjectType.STRIPPED_POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final String name = woodType.getName();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.POST.toString());

            final ResourceLocation strippedLog = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getSideTexture();
            final ModelFile post = this.models()
                    .withExistingParent(Util.toPath(path, "stripped", name), modLoc(Util.toPath(path, "template")))
                    .texture("wall", strippedLog);
            final ModelFile side = this.models()
                    .withExistingParent(Util.toPath(path, "stripped", "side", name), modLoc(Util.toPath(path, "side", "template")))
                    .texture("texture", modLoc(Util.toPath(path, "stripped", name)));

            this.models()
                    .withExistingParent(Util.toPath(path, "stripped", "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("wall", strippedLog);

            this.postBlock(this.getMultipartBuilder(block), post, side);
        });

        Util.getItemsWith(WoodenObjectType.ITEM_FRAME, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.ITEM_FRAME.toString());
            final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
            final ModelFile itemFrame = this.models()
                    .withExistingParent(Util.toPath(path, woodType.getName()), modLoc(Util.toPath(path, "template")))
                    .texture("planks", planks);

            final ModelFile itemFrameMap = this.models()
                    .withExistingParent(Util.toPath(path, "map", woodType.getName()), modLoc(Util.toPath(path, "map", "template")))
                    .texture("planks", planks);
        });

        Util.getBedBlocksWith(Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.BED.toString());
            final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
            final ResourceLocation frame = modLoc(Util.toPath(path, "frame", woodType.getName()));
            final ResourceLocation headTop = modLoc(Util.toPath(path, "sheets", "head", "top", color.toString()));
            final ResourceLocation headSides = modLoc(Util.toPath(path, "sheets", "head", "sides", color.toString()));
            final ResourceLocation footTop = modLoc(Util.toPath(path, "sheets", "foot", "top", color.toString()));
            final ResourceLocation footSides = modLoc(Util.toPath(path, "sheets", "foot", "sides", color.toString()));

            final ModelFile head = this.models()
                    .withExistingParent(Util.toPath(path, "head", color.toString(), woodType.getName()), modLoc(Util.toPath(path, "head", "template")))
                    .texture("planks", planks)
                    .texture("frame", frame)
                    .texture("top", headTop)
                    .texture("sides", headSides);

            final ModelFile foot = this.models()
                    .withExistingParent(Util.toPath(path, "foot", color.toString(), woodType.getName()), modLoc(Util.toPath(path, "foot", "template")))
                    .texture("planks", planks)
                    .texture("frame", frame)
                    .texture("top", footTop)
                    .texture("sides", footSides);

            final ModelFile inventory = this.models()
                    .withExistingParent(Util.toPath(path, "inventory", color.toString(), woodType.getName()), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("planks", planks)
                    .texture("frame", frame)
                    .texture("head_top", headTop)
                    .texture("head_sides", headSides)
                    .texture("foot_top", footTop)
                    .texture("foot_sides", footSides);

            this.getVariantBuilder(block)
                    .forAllStates(state -> ConfiguredModel.builder()
                            .modelFile(state.get(BlockStateProperties.BED_PART).equals(BedPart.HEAD) ? head : foot)
                            .rotationY(((state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalIndex() + 2) % 4) * 90)
                            .uvLock(false)
                            .build()
                    );
        });

        Util.getBlocksWith(WoodenObjectType.SAWMILL, Util.HAS_PLANKS.and(Util.HAS_SLAB).and(Util.HAS_LOG).and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IWoodenPlanksResource planksResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType);
            final IWoodenLogResource logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);
            final IWoodenStrippedLogResource strippedLogResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);
            final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectType.SAWMILL.toString());

            // TODO remove hardcoded log_pile once log piles are back in the mod
            final ResourceLocation logPile = modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "log_pile", woodType.getName()));

            final ModelFile bottomLeft = this.models()
                    .withExistingParent(Util.toPath(path, "bottom", "left", woodType.getName()), modLoc(Util.toPath(path, "bottom", "left", "template")))
                    .texture("log_end", logResource.getEndTexture())
                    .texture("log_side", logResource.getSideTexture())
                    .texture("stripped_log_side", strippedLogResource.getSideTexture())
                    .texture("log_pile", logPile);

            final ModelFile bottomRight = this.models()
                    .withExistingParent(Util.toPath(path, "bottom", "right", woodType.getName()), modLoc(Util.toPath(path, "bottom", "right", "template")))
                    .texture("log_end", logResource.getEndTexture())
                    .texture("log_side", logResource.getSideTexture())
                    .texture("stripped_log_side", strippedLogResource.getSideTexture())
                    .texture("log_pile", logPile);

            final ModelFile topLeft = this.models()
                    .withExistingParent(Util.toPath(path, "top", "left", woodType.getName()), modLoc(Util.toPath(path, "top", "left", "template")))
                    .texture("planks", planksResource.getTexture());

            final ModelFile topRight = new ModelFile.UncheckedModelFile(modLoc(Util.toPath(path, "top", "right", "template")));

            final ModelFile inventory = this.models()
                    .withExistingParent(Util.toPath(path, "inventory", woodType.getName()), modLoc(Util.toPath(path, "inventory", "template")))
                    .texture("log_end", logResource.getEndTexture())
                    .texture("log_side", logResource.getSideTexture())
                    .texture("stripped_log_side", strippedLogResource.getSideTexture())
                    .texture("planks", planksResource.getTexture())
                    .texture("log_pile", logPile);

            final Map<WoodenSawmillBlock.WoodenSawmillModel, ModelFile> models = new EnumMap<>(WoodenSawmillBlock.WoodenSawmillModel.class);
            models.put(WoodenSawmillBlock.WoodenSawmillModel.BOTTOM_LEFT, bottomLeft);
            models.put(WoodenSawmillBlock.WoodenSawmillModel.BOTTOM_RIGHT, bottomRight);
            models.put(WoodenSawmillBlock.WoodenSawmillModel.TOP_LEFT, topLeft);
            models.put(WoodenSawmillBlock.WoodenSawmillModel.TOP_RIGHT, topRight);

            this.getVariantBuilder(block)
                    .forAllStates(state -> ConfiguredModel.builder()
                            .modelFile(models.get(state.get(WoodenSawmillBlock.MODEL)))
                            .rotationY(((state.get(WoodenSawmillBlock.HORIZONTAL_FACING).getHorizontalIndex() + 2) % 4) * 90)
                            .uvLock(false)
                            .build());
        });
    }

    private ModelFile templateWithPlanks(final Block block, final String nested, final WoodenObjectType... objectTypes) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = ((IWooden) block).getWoodType().getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, Arrays.stream(objectTypes).map(Objects::toString).collect(Collectors.joining("/")));
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

        return this.models().singleTexture(Util.toPath(path + nested, name), modLoc(Util.toPath(path + nested, "template")), "planks", planks);
    }

    private void postBlock(final MultiPartBlockStateBuilder builder, final ModelFile post, final ModelFile side) {
        builder.part()
                .modelFile(post)
                .addModel()
                .condition(WoodenStrippedPostBlock.AXIS, Direction.Axis.Y)
                .end()
                .part()
                .modelFile(post)
                .rotationY(90)
                .rotationX(90)
                .addModel()
                .condition(WoodenStrippedPostBlock.AXIS, Direction.Axis.X)
                .end()
                .part()
                .modelFile(post)
                .rotationX(90)
                .addModel()
                .condition(WoodenStrippedPostBlock.AXIS, Direction.Axis.Z)
                .end();

        SixWayBlock.FACING_TO_PROPERTY_MAP.forEach((direction, property) -> {
            if (direction.getAxis().isHorizontal()) {
                builder.part()
                        .modelFile(side)
                        .rotationY((((int) direction.getHorizontalAngle()) + 180) % 360)
                        .uvLock(false)
                        .addModel()
                        .condition(property, true);
            } else {
                builder.part()
                        .modelFile(side)
                        .rotationX(direction == Direction.UP ? 270 : 90)
                        .uvLock(false)
                        .addModel()
                        .condition(property, true);
            }
        });
    }

    @Override
    public String getName() {
        return "I Like Wood - Blockstates";
    }
}
