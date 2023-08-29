package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class WallSoulTorchBlockStateProvider
    extends AbstractBlockStateProvider
{
    public WallSoulTorchBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.WALL_SOUL_TORCH);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.TORCH.getName());
        final var template = this
            .models()
            .withExistingParent(Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.SOUL_TORCH.getName(), woodType.getModId(), "wall", name),
                modLoc(Util.toPath(path, "wall", "template"))
            )
            .texture("torch", modLoc(Util.toPath(path, woodType.getModId(), name)))
            .texture("fire", modLoc(Util.toPath(path, "soul_fire")))
            .texture("coal", modLoc(Util.toPath(path, "soul_coal")));

        this.horizontalBlock(block, template, 90);
    }
}
