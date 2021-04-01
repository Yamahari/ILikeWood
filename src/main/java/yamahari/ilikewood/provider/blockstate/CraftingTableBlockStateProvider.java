package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class CraftingTableBlockStateProvider extends AbstractBlockStateProvider {
    public CraftingTableBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectTypes.CRAFTING_TABLE);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        this.simpleBlock(block, this.templateWithPlanks(block, WoodenObjectTypes.CRAFTING_TABLE));
    }
}
