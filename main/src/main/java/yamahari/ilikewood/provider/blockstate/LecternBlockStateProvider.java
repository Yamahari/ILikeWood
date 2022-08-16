package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LecternBlockStateProvider
    extends AbstractBlockStateProvider
{
    public LecternBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.LECTERN);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LECTERN.getName());
        final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        final var template = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), name), modLoc(Util.toPath(path, "template")))
            .texture("top", modLoc(Util.toPath(path, woodType.getModId(), "top", name)))
            .texture("sides", modLoc(Util.toPath(path, woodType.getModId(), "sides", name)))
            .texture("bottom", planks)
            .texture("base", modLoc(Util.toPath(path, woodType.getModId(), "base", name)))
            .texture("front", modLoc(Util.toPath(path, woodType.getModId(), "front", name)));

        this.horizontalBlock(block, template);
    }
}
