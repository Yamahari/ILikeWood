package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class LadderBlockItemModelProvider extends AbstractBlockItemModelProvider
{
    public LadderBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.LADDER);
    }

    @Override
    protected void registerModel(final Block block) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block).getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc(
            Util.toPath(ITEM_FOLDER, "generated")))).texture("layer0",
            modLoc(Util.toPath(BLOCK_FOLDER, WoodenBlockType.LADDER.getName(), woodType))
        );
    }
}
