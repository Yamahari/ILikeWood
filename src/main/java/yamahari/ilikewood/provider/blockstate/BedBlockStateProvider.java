package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class BedBlockStateProvider extends AbstractBlockStateProvider {
    public BedBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.BED);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectTypes.BED.getName());
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        final ResourceLocation frame = modLoc(Util.toPath(path, "frame", woodType.getName()));
        final ResourceLocation headTop = modLoc(Util.toPath(path, "sheets", "head", "top", color.toString()));
        final ResourceLocation headSides = modLoc(Util.toPath(path, "sheets", "head", "sides", color.toString()));
        final ResourceLocation footTop = modLoc(Util.toPath(path, "sheets", "foot", "top", color.toString()));
        final ResourceLocation footSides = modLoc(Util.toPath(path, "sheets", "foot", "sides", color.toString()));

        final ModelFile head = this
            .models()
            .withExistingParent(Util.toPath(path, "head", color.toString(), woodType.getName()),
                modLoc(Util.toPath(path, "head", "template")))
            .texture("planks", planks)
            .texture("frame", frame)
            .texture("top", headTop)
            .texture("sides", headSides);

        final ModelFile foot = this
            .models()
            .withExistingParent(Util.toPath(path, "foot", color.toString(), woodType.getName()),
                modLoc(Util.toPath(path, "foot", "template")))
            .texture("planks", planks)
            .texture("frame", frame)
            .texture("top", footTop)
            .texture("sides", footSides);

        final ModelFile inventory = this
            .models()
            .withExistingParent(Util.toPath(path, "inventory", color.toString(), woodType.getName()),
                modLoc(Util.toPath(path, "inventory", "template")))
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
                .modelFile(state.get(BlockStateProperties.BED_PART).equals(BedPart.HEAD) ? head : foot)
                .rotationY(((state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalIndex() + 2) % 4) * 90)
                .uvLock(false)
                .build());
    }
}
