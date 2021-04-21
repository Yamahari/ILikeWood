package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class ChestBlockStateProvider extends AbstractBlockStateProvider {
    public ChestBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.CHEST);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String name = ((IWooden) block).getWoodType().getName();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CHEST.getName(), name);
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();

        this.simpleBlock(block, this.models().getBuilder(path).texture("particle", planks));
    }
}
