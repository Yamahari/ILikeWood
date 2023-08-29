package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public final class ChestBlockItemModelProvider
    extends AbstractBlockItemModelProvider
{
    public ChestBlockItemModelProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.CHEST);
    }

    @Override
    protected void registerModel(final Block block)
    {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        this
            .getBuilder(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block), "Registry name was null").getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath("builtin", "entity"))))
            .texture("particle", ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture()).transforms().transform(ItemDisplayContext.GUI)
            .rotation(30, 45, 0)
            .scale(0.625F).end().transform(ItemDisplayContext.GROUND)
            .translation(0, 3, 0)
            .scale(0.25F).end().transform(ItemDisplayContext.HEAD)
            .rotation(0, 180, 0)
            .scale(1.f).end().transform(ItemDisplayContext.FIXED)
            .rotation(0, 180, 0)
            .scale(0.5F).end().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(75, 315, 0)
            .translation(0.F, 2.5F, 0.F)
            .scale(0.375F).end().transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .rotation(0, 315, 0)
            .scale(0.4F)
            .end()
            .end();

    }
}
