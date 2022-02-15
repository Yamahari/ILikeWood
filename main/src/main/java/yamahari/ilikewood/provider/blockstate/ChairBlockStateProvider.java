package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.resource.resources.IWoodenStrippedLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ChairBlockStateProvider extends AbstractBlockStateProvider {
    public ChairBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.CHAIR);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IWoodenStrippedLogResource strippedLogResource =
            ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType);

        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CHAIR.getName());

        final ModelFile chair = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("stripped_log_side", strippedLogResource.getSideTexture())
            .texture("stripped_log_end", strippedLogResource.getEndTexture());

        this.horizontalBlock(block, chair);
    }
}
