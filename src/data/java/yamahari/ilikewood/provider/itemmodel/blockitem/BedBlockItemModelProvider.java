package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Util;

public final class BedBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public BedBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.WHITE_BED);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, Util.toPath("bed", "inventory", ((WoodenBedBlock) block).getDyeColor().toString()));
    }
}
