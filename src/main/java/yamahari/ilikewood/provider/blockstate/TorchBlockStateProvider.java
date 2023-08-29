package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class TorchBlockStateProvider
    extends AbstractBlockStateProvider
{
    public TorchBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.TORCH);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.TORCH.getName());
        final var template = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), name), modLoc(Util.toPath(path, "template")))
            .texture("torch", modLoc(Util.toPath(path, woodType.getModId(), name)))
            .texture("fire", modLoc(Util.toPath(path, "fire")))
            .texture("coal", modLoc(Util.toPath(path, "coal")));

        this.simpleBlock(block, template);
    }
}
