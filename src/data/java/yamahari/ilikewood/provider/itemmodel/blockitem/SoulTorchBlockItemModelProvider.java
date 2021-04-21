package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class SoulTorchBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public SoulTorchBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.SOUL_TORCH);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenBlockType.SOUL_TORCH.getName());
    }
}
