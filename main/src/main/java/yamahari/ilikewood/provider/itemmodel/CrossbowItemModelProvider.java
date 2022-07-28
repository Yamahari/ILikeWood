package yamahari.ilikewood.provider.itemmodel;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Objects;
import java.util.stream.IntStream;

public final class CrossbowItemModelProvider extends AbstractItemModelProvider
{
    public CrossbowItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void registerModel(final Item item) {
        final String woodType = ((IWooden) item).getWoodType().getName();
        this.getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item), "Registry name was null").getPath())
            .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
            .texture("layer0", modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.CROSSBOW.getName(), "standby", woodType)))
            .transforms()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
            .rotation(-90.0F, 0.0F, -60.F)
            .translation(2.0F, 0.1F, -3.0F)
            .scale(0.9F)
            .end()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
            .rotation(-90.0F, 0.0F, 30.F)
            .translation(2.0F, 0.1F, -3.0F)
            .scale(0.9F)
            .end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            .rotation(-90.0F, 0.0F, -55.F)
            .translation(1.13F, 3.2F, 1.13F)
            .scale(0.68F)
            .end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
            .rotation(-90.0F, 0.0F, 35.F)
            .translation(1.13F, 3.2F, 1.13F)
            .scale(0.68F)
            .end()
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "pulling", "0")
            ))))
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .predicate(mcLoc("pull"), 0.58F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "pulling", "1")
            ))))
            .end()
            .override()
            .predicate(mcLoc("pulling"), 1.0F)
            .predicate(mcLoc("pull"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "pulling", "2")
            ))))
            .end()
            .override()
            .predicate(mcLoc("charged"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "arrow")
            ))))
            .end()
            .override()
            .predicate(mcLoc("charged"), 1.0F)
            .predicate(mcLoc("firework"), 1.0F)
            .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "firework")
            ))))
            .end();

        IntStream.range(0, 3).forEach(i -> this.getBuilder(Util.toRegistryName(woodType,
                WoodenItemType.CROSSBOW.getName(),
                "pulling",
                Integer.toString(i)
            ))
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item), "Registry name was null").getPath()
            ))))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER,
                    WoodenItemType.CROSSBOW.getName(),
                    "pulling",
                    Integer.toString(i),
                    woodType
                ))
            ));

        this.getBuilder(Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "arrow"))
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item), "Registry name was null").getPath()
            ))))
            .texture("layer0", modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.CROSSBOW.getName(), "arrow", woodType)));

        this.getBuilder(Util.toRegistryName(woodType, WoodenItemType.CROSSBOW.getName(), "firework"))
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item), "Registry name was null").getPath()
            ))))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER, WoodenItemType.CROSSBOW.getName(), "firework", woodType))
            );
    }
}
