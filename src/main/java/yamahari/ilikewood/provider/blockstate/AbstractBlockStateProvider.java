package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.block.SixWayBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.post.WoodenStrippedPostBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {
    private final WoodenBlockType blockType;

    public AbstractBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper,
                                      final WoodenBlockType blockType) {
        super(generator, Constants.MOD_ID, helper);
        this.blockType = blockType;
    }

    @Override
    protected final void registerStatesAndModels() {
        if (this.blockType.equals(WoodenBlockType.WHITE_BED)) {
            ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).forEach(this::registerStateAndModel);
        } else {
            ILikeWood.BLOCK_REGISTRY.getObjects(this.blockType).forEach(this::registerStateAndModel);
        }
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - block states & models - %s", Constants.MOD_ID, blockType.getName());
    }

    protected final ModelFile templateWithPlanks(final Block block, final WoodenBlockType... blockTypes) {
        return this.templateWithPlanks(block, "", blockTypes);
    }

    protected final ModelFile templateWithPlanks(final Block block, final String nested,
                                                 final WoodenBlockType... blockTypes) {
        return this.templateWithPlanks(block,
            nested,
            Util.toPath(ModelProvider.BLOCK_FOLDER,
                Arrays.stream(blockTypes).map(WoodenBlockType::getName).collect(Collectors.joining("/"))));
    }

    protected final ModelFile templateWithPlanks(final Block block, final String nested, final String path) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = ((IWooden) block).getWoodType().getName();
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

        return this
            .models()
            .singleTexture(Util.toPath(path + nested, name),
                modLoc(Util.toPath(path + nested, "template")),
                "planks",
                planks);

    }

    protected final void postBlock(final MultiPartBlockStateBuilder builder, final ModelFile post,
                                   final ModelFile side) {
        builder
            .part()
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
                builder
                    .part()
                    .modelFile(side)
                    .rotationY((((int) direction.getHorizontalAngle()) + 180) % 360)
                    .uvLock(false)
                    .addModel()
                    .condition(property, true);
            } else {
                builder
                    .part()
                    .modelFile(side)
                    .rotationX(direction == Direction.UP ? 270 : 90)
                    .uvLock(false)
                    .addModel()
                    .condition(property, true);
            }
        });
    }

    public abstract void registerStateAndModel(Block block);
}
