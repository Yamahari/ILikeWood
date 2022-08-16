package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenTableBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.function.Supplier;

public final class TableBlockStateProvider
    extends AbstractBlockStateProvider
{
    public TableBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.TABLE);
    }

    private static void tableConditions(
        final Supplier<MultiPartBlockStateBuilder.PartBuilder> builder,
        final int[] is
    )
    {
        for (int i : is)
        {
            builder
                .get()
                .condition(WoodenTableBlock.NORTH, (i & 1) != 0)
                .condition(WoodenTableBlock.EAST, (i & (1 << 1)) != 0)
                .condition(WoodenTableBlock.SOUTH, (i & (1 << 2)) != 0)
                .condition(WoodenTableBlock.WEST, (i & (1 << 3)) != 0);
        }
    }

    private static void nwLeg(
        final MultiPartBlockStateBuilder builder,
        final ModelFile leg
    )
    {
        tableConditions(() -> builder.part().modelFile(leg).addModel(), new int[]{
            15,
            13,
            11,
            6,
            9,
            2,
            4,
            0
        });
    }

    private static void neLeg(
        final MultiPartBlockStateBuilder builder,
        final ModelFile leg
    )
    {
        tableConditions(() -> builder.part().modelFile(leg).rotationY(90).addModel(), new int[]{
            15,
            7,
            11,
            3,
            12,
            4,
            8,
            0
        });

    }

    private static void seLeg(
        final MultiPartBlockStateBuilder builder,
        final ModelFile leg
    )
    {
        tableConditions(() -> builder.part().modelFile(leg).rotationY(180).addModel(), new int[]{
            15,
            7,
            14,
            6,
            9,
            1,
            8,
            0
        });
    }

    private static void swLeg(
        final MultiPartBlockStateBuilder builder,
        final ModelFile leg
    )
    {
        tableConditions(() -> builder.part().modelFile(leg).rotationY(270).addModel(), new int[]{
            15,
            14,
            13,
            3,
            12,
            1,
            2,
            0
        });
    }

    private static void legs(
        final MultiPartBlockStateBuilder builder,
        final ModelFile leg
    )
    {
        nwLeg(builder, leg);
        neLeg(builder, leg);
        seLeg(builder, leg);
        swLeg(builder, leg);
    }

    private static void nBeam(
        final MultiPartBlockStateBuilder builder,
        final ModelFile beam
    )
    {
        tableConditions(() -> builder.part().modelFile(beam).addModel(), new int[]{
            14,
            6,
            12,
            10,
            2,
            4,
            8,
            0
        });
    }

    private static void eBeam(
        final MultiPartBlockStateBuilder builder,
        final ModelFile beam
    )
    {
        tableConditions(() -> builder.part().modelFile(beam).rotationY(90).addModel(), new int[]{
            13,
            12,
            9,
            5,
            1,
            4,
            8,
            0
        });
    }

    private static void sBeam(
        final MultiPartBlockStateBuilder builder,
        final ModelFile beam
    )
    {
        tableConditions(() -> builder.part().modelFile(beam).rotationY(180).addModel(), new int[]{
            11,
            3,
            9,
            10,
            1,
            2,
            8,
            0
        });
    }

    private static void wBeam(
        final MultiPartBlockStateBuilder builder,
        final ModelFile beam
    )
    {
        tableConditions(() -> builder.part().modelFile(beam).rotationY(270).addModel(), new int[]{
            7,
            3,
            6,
            5,
            1,
            2,
            4,
            0
        });
    }

    private static void beams(
        final MultiPartBlockStateBuilder builder,
        final ModelFile beam
    )
    {
        nBeam(builder, beam);
        eBeam(builder, beam);
        sBeam(builder, beam);
        wBeam(builder, beam);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.TABLE.getName());

        final var log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);
        final var strippedLog = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

        final var top = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "top", name), modLoc(Util.toPath(path, "top", "template")))
            .texture("log_end", log.getEndTexture())
            .texture("log_side", log.getSideTexture());

        final var leg = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "leg", name), modLoc(Util.toPath(path, "leg", "template")))
            .texture("log_end", log.getEndTexture())
            .texture("stripped_log_side", strippedLog.getSideTexture());

        final var beam = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "beam", name), modLoc(Util.toPath(path, "beam", "template")))
            .texture("log_end", log.getEndTexture())
            .texture("stripped_log_side", strippedLog.getSideTexture());

        this.models().withExistingParent(Util.toPath(path, woodType.getModId(), "inventory", name), modLoc(Util.toPath(path, "inventory", "template")))
            .texture("log_end", log.getEndTexture())
            .texture("log_side", log.getSideTexture())
            .texture("stripped_log_side", strippedLog.getSideTexture());

        final var builder = this.getMultipartBuilder(block).part().modelFile(top).addModel().end();

        legs(builder, leg);
        beams(builder, beam);
    }
}
