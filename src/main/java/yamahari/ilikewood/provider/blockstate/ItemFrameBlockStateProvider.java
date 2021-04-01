package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;

public final class ItemFrameBlockStateProvider extends BlockStateProvider {
    public ItemFrameBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    @Override
    protected final void registerStatesAndModels() {
        WoodenItems.getItems(WoodenObjectTypes.ITEM_FRAME).forEach(this::registerItemFrame);
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - block states & models - %s",
            Constants.MOD_ID,
            WoodenObjectTypes.ITEM_FRAME.getName());
    }

    private void registerItemFrame(final Item item) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenObjectTypes.ITEM_FRAME.getName());
        final ResourceLocation planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        final ModelFile itemFrame = this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("planks", planks);

        final ModelFile itemFrameMap = this
            .models()
            .withExistingParent(Util.toPath(path, "map", woodType.getName()),
                modLoc(Util.toPath(path, "map", "template")))
            .texture("planks", planks);
    }
}
