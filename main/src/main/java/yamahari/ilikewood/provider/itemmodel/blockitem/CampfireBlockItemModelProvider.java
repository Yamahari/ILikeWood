package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public class CampfireBlockItemModelProvider
    extends AbstractBlockItemModelProvider
{
    public CampfireBlockItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.CAMPFIRE);
    }

    @Override
    protected void registerModel(final Block block)
    {
        this.blockItem(block, WoodenBlockType.CAMPFIRE.getName());
    }
}
