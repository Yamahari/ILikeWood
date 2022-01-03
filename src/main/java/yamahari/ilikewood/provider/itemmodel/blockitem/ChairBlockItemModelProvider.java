package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public final class ChairBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public ChairBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.CHAIR);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenBlockType.CHAIR.getName());
    }
}
