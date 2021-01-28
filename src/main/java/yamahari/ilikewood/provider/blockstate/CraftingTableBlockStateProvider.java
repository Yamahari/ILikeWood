package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.util.WoodenObjectType;

public final class CraftingTableBlockStateProvider extends AbstractBlockStateProvider {
    public CraftingTableBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenObjectType.CRAFTING_TABLE);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        this.simpleBlock(block, this.templateWithPlanks(block, WoodenObjectType.CRAFTING_TABLE));
    }
}
