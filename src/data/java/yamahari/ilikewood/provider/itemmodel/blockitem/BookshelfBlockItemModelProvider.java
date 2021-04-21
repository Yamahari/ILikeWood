package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

public final class BookshelfBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public BookshelfBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.BOOKSHELF);
    }

    @Override
    protected void registerModel(final Block block) {
        this.blockItem(block, WoodenBlockType.BOOKSHELF.getName());
    }
}
