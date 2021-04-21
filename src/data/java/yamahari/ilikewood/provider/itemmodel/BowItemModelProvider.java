package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Objects;
import java.util.stream.IntStream;

public final class BowItemModelProvider extends AbstractItemModelProvider {
    public BowItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenItemType.BOW);
    }

    @Override
    protected void registerModel(final Item item) {
        final String woodType = ((IWooden) item).getWoodType().getName();
        this
            .getBuilder(item.getRegistryName().getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
            .texture("layer0", modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.BOW.getName(), woodType)))
            .transforms()
            .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
            .rotation(-80.0F, 260.0F, -40.F)
            .translation(-1.0F, -2.0F, 2.5F)
            .scale(0.9F)
            .end()
            .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
            .rotation(-80.0F, 280.0F, 40.F)
            .translation(-1.0F, -2.0F, 2.5F)
            .scale(0.9F)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .rotation(0.0F, -90.0F, 25.F)
            .translation(1.13F, 3.2F, 1.13F)
            .scale(0.68F)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
            .rotation(0.0F, 90.0F, -25.F)
            .translation(1.13F, 3.2F, 1.13F)
            .scale(0.68F)
            .end()
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.BOW.getName(), "pulling", "0")))))
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .predicate(mcLoc("pull"), 0.65F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.BOW.getName(), "pulling", "1")))))
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .predicate(mcLoc("pull"), 0.9F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.BOW.getName(), "pulling", "2")))))
            .end();

        IntStream
            .range(0, 3)
            .forEach(i -> this
                .getBuilder(Util.toRegistryName(woodType, WoodenItemType.BOW.getName(), "pulling", Integer.toString(i)))
                .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath()))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER,
                        WoodenItemType.BOW.getName(),
                        "pulling",
                        Integer.toString(i),
                        woodType))));
    }
}
