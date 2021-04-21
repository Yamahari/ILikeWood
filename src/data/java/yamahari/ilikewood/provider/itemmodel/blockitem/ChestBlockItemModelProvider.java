package yamahari.ilikewood.provider.itemmodel.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import java.util.Objects;

public final class ChestBlockItemModelProvider extends AbstractBlockItemModelProvider {
    public ChestBlockItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.CHEST);
    }

    @Override
    protected void registerModel(final Block block) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        this
            .getBuilder(Objects.requireNonNull(block.getRegistryName(), "Registry name was null").getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath("builtin", "entity"))))
            .texture("particle", ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture())
            .transforms()
            .transform(ModelBuilder.Perspective.GUI)
            .rotation(30, 45, 0)
            .scale(0.625F)
            .end()
            .transform(ModelBuilder.Perspective.GROUND)
            .translation(0, 3, 0)
            .scale(0.25F)
            .end()
            .transform(ModelBuilder.Perspective.HEAD)
            .rotation(0, 180, 0)
            .scale(1.f)
            .end()
            .transform(ModelBuilder.Perspective.FIXED)
            .rotation(0, 180, 0)
            .scale(0.5F)
            .end()
            .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
            .rotation(75, 315, 0)
            .translation(0.F, 2.5F, 0.F)
            .scale(0.375F)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .rotation(0, 315, 0)
            .scale(0.4F)
            .end()
            .end();

    }
}
