package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LadderBlockStateProvider
    extends AbstractBlockStateProvider
{
    public LadderBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.LADDER);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = woodType.getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LADDER.getName());
        final var template = this
            .models()
            .singleTexture(Util.toPath(path, woodType.getModId(), name), modLoc(Util.toPath(path, "template")), "ladder",
                modLoc(Util.toPath(path, woodType.getModId(), name))
            );

        this.horizontalBlock(block, template);
    }
}
