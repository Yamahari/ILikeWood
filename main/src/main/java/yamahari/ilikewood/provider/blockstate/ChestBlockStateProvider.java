package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class ChestBlockStateProvider
    extends AbstractBlockStateProvider
{
    public ChestBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.CHEST);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final var woodType = ((IWooden) block).getWoodType();
        final var name = ((IWooden) block).getWoodType().getName();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CHEST.getName(), woodType.getModId(), name);
        final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

        this.simpleBlock(block, this.models().getBuilder(path).texture("particle", planks));
    }
}
