package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.EnumMap;
import java.util.Map;

public final class SawmillBlockStateProvider
    extends AbstractBlockStateProvider
{
    public SawmillBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.SAWMILL);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var planksResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType);
        final var logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);
        final var strippedLogResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.SAWMILL.getName());

        final var logPile = modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LOG_PILE.getName(), woodType.getModId(), woodType.getName()));

        final var bottomLeft = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "bottom", "left", woodType.getName()), modLoc(Util.toPath(path, "bottom", "left", "template")))
            .texture("log_end", logResource.getEndTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("log_pile", logPile);

        final var bottomRight = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "bottom", "right", woodType.getName()), modLoc(Util.toPath(path, "bottom", "right", "template")))
            .texture("log_end", logResource.getEndTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("log_pile", logPile);

        final var topLeft = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "top", "left", woodType.getName()), modLoc(Util.toPath(path, "top", "left", "template")))
            .texture("planks", planksResource.getTexture());

        final var topRight = new ModelFile.UncheckedModelFile(modLoc(Util.toPath(path, "top", "right", "template")));

        this.models().withExistingParent(Util.toPath(path, woodType.getModId(), "inventory", woodType.getName()), modLoc(Util.toPath(path, "inventory", "template")))
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

        this
            .getVariantBuilder(block)
            .forAllStates(state -> ConfiguredModel
                .builder()
                .modelFile(models.get(state.getValue(WoodenSawmillBlock.MODEL)))
                .rotationY(((state.getValue(WoodenSawmillBlock.HORIZONTAL_FACING).get2DDataValue() + 2) % 4) * 90)
                .uvLock(false)
                .build());
    }
}
