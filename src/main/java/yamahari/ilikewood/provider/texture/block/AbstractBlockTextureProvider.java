package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.provider.texture.AbstractTextureProvider;
import yamahari.ilikewood.registry.ILikeWoodBlockRegistry;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public abstract class AbstractBlockTextureProvider
    extends AbstractTextureProvider<Block, WoodenBlockType, ILikeWoodBlockRegistry>
{
    public AbstractBlockTextureProvider(
        final DataGenerator generator,
        final String folder,
        final ExistingFileHelper helper,
        final String root,
        final WoodenBlockType blockType,
        final boolean runOnce
    )
    {
        super(generator, folder, helper, root, blockType, ILikeWood.BLOCK_REGISTRY, runOnce);
    }

    public AbstractBlockTextureProvider(
        final DataGenerator generator,
        final String folder,
        final ExistingFileHelper helper,
        final String root,
        final WoodenBlockType blockType
    )
    {
        super(generator, folder, helper, root, blockType, ILikeWood.BLOCK_REGISTRY);
    }
}
