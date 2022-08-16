package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LecternBlockItemModelProvider
    extends AbstractBlockItemModelProvider
{
    public LecternBlockItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.LECTERN);
    }

    @Override
    protected void registerModel(final Block block)
    {
        this.blockItem(block, Util.toPath(WoodenBlockType.LECTERN.getName(), ((IWooden) block).getWoodType().getModId()));
    }
}
