package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenSawmillBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenPlanksResource;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.EnumMap;
import java.util.Map;

public final class SawmillBlockStateProvider extends AbstractBlockStateProvider {
    public SawmillBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.SAWMILL);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IWoodenPlanksResource planksResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType);
        final IWoodenLogResource logResource = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);
        final IWoodenStrippedLogResource strippedLogResource =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.SAWMILL.getName());

        final ResourceLocation logPile =
            modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LOG_PILE.getName(), woodType.getName()));

        final ModelFile bottomLeft = this
            .models()
            .withExistingParent(Util.toPath(path, "bottom", "left", woodType.getName()),
                modLoc(Util.toPath(path, "bottom", "left", "template")))
            .texture("log_end", logResource.getEndTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("log_pile", logPile);

        final ModelFile bottomRight = this
            .models()
            .withExistingParent(Util.toPath(path, "bottom", "right", woodType.getName()),
                modLoc(Util.toPath(path, "bottom", "right", "template")))
            .texture("log_end", logResource.getEndTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("log_pile", logPile);

        final ModelFile topLeft = this
            .models()
            .withExistingParent(Util.toPath(path, "top", "left", woodType.getName()),
                modLoc(Util.toPath(path, "top", "left", "template")))
            .texture("planks", planksResource.getTexture());

        final ModelFile topRight =
            new ModelFile.UncheckedModelFile(modLoc(Util.toPath(path, "top", "right", "template")));

        final ModelFile inventory = this
            .models()
            .withExistingParent(Util.toPath(path, "inventory", woodType.getName()),
                modLoc(Util.toPath(path, "inventory", "template")))
            .texture("log_end", logResource.getEndTexture())
            .texture("log_side", logResource.getSideTexture())
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("planks", planksResource.getTexture())
            .texture("log_pile", logPile);

        final Map<WoodenSawmillBlock.WoodenSawmillModel, ModelFile> models =
            new EnumMap<>(WoodenSawmillBlock.WoodenSawmillModel.class);
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
