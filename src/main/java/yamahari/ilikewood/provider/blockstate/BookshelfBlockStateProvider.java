package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BookshelfBlockStateProvider
    extends AbstractBlockStateProvider
{
    public BookshelfBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.BOOKSHELF);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = ((IWooden) block).getWoodType().getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.BOOKSHELF.getName(), woodType.getModId(), name);

        final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

        this.simpleBlock(block, this.models().cubeColumn(path, modLoc(path), planks));
    }
}
