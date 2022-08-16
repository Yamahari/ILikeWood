package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class SoulTorchBlockItemModelProvider
    extends AbstractBlockItemModelProvider
{
    public SoulTorchBlockItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.SOUL_TORCH);
    }

    @Override
    protected void registerModel(final Block block)
    {
        this.blockItem(block, Util.toPath(WoodenBlockType.SOUL_TORCH.getName(), ((IWooden) block).getWoodType().getModId()));
    }
}
