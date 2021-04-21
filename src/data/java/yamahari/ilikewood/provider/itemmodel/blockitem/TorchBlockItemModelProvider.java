package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class TorchBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public TorchBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.TORCH);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenBlockType.TORCH.getName());
    }
}
