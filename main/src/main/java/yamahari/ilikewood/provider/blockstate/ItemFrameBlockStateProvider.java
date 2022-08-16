package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;

public final class ItemFrameBlockStateProvider
    extends BlockStateProvider
{
    public ItemFrameBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.ITEM_FRAME).forEach(this::registerItemFrame);
    }

    @Nonnull
    @Override
    public String getName()
    {
        return String.format("%s - block states & models - %s", Constants.MOD_ID, WoodenItemType.ITEM_FRAME.getName());
    }

    private void registerItemFrame(final Item item)
    {
        final var woodType = ((IWooden) item).getWoodType();
        final var path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenItemType.ITEM_FRAME.getName());
        final var planks = ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture();
        this.models().withExistingParent(Util.toPath(path, woodType.getModId(), woodType.getName()), modLoc(Util.toPath(path, "template"))).texture("planks", planks);

        this
            .models()
            .withExistingParent(Util.toPath(path, woodType.getModId(), "map", woodType.getName()), modLoc(Util.toPath(path, "map", "template")))
            .texture("planks", planks);
    }
}
