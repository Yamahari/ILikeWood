package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BedBlockStateProvider
    extends AbstractBlockStateProvider
{
    public BedBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.WHITE_BED);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var color = ((WoodenBedBlock) block).getDyeColor();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, "bed");
        final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        final var frame = modLoc(Util.toPath(path, woodType.getModId(), "frame", woodType.getName()));
        final var headTop = modLoc(Util.toPath(path, "sheets", "head", "top", color.toString()));
        final var headSides = modLoc(Util.toPath(path, "sheets", "head", "sides", color.toString()));
        final var footTop = modLoc(Util.toPath(path, "sheets", "foot", "top", color.toString()));
        final var footSides = modLoc(Util.toPath(path, "sheets", "foot", "sides", color.toString()));

        final var head = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "head", color.toString(), woodType.getName()), modLoc(Util.toPath(path, "head", "template")))
            .texture("planks", planks)
            .texture("frame", frame)
            .texture("top", headTop)
            .texture("sides", headSides);

        final var foot = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "foot", color.toString(), woodType.getName()), modLoc(Util.toPath(path, "foot", "template")))
            .texture("planks", planks)
            .texture("frame", frame)
            .texture("top", footTop)
            .texture("sides", footSides);

        this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "inventory", color.toString(), woodType.getName()),
                modLoc(Util.toPath(path, "inventory", "template"))
            )
            .texture("planks", planks)
            .texture("frame", frame)
            .texture("head_top", headTop)
            .texture("head_sides", headSides)
            .texture("foot_top", footTop)
            .texture("foot_sides", footSides);

        this
            .getVariantBuilder(block)
            .forAllStates(state -> ConfiguredModel
                .builder()
                .modelFile(state.getValue(BlockStateProperties.BED_PART).equals(BedPart.HEAD) ? head : foot)
                .rotationY(((state.getValue(BlockStateProperties.HORIZONTAL_FACING).get2DDataValue() + 2) % 4) * 90)
                .uvLock(false)
                .build());
    }
}
