package yamahari.ilikewood.provider.itemmodel.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

import java.util.Objects;

public abstract class AbstractTieredItemModelProvider extends ItemModelProvider {
    private final WoodenTieredObjectType tieredObjectType;

    public AbstractTieredItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper,
                                           final WoodenTieredObjectType tieredObjectType) {
        super(generator, Constants.MOD_ID, helper);
        this.tieredObjectType = tieredObjectType;
    }

    @Override
    protected void registerModels() {
        WoodenItems.getTieredItems(this.tieredObjectType).forEach(this::registerModel);
    }

    protected abstract void registerModel(Item item);

    protected void tieredItem(final Item item, final String path) {
        final String woodType = ((IWooden) item).getWoodType().getName();
        final IWoodenItemTier itemTier = ((IWoodenTieredItem) item).getWoodenItemTier();
        final String tier = itemTier.getName();
        final boolean isWood = ((IWoodenTieredItem) item).getWoodenItemTier().isWood();
        this
            .withExistingParent(Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath(),
                mcLoc(Util.toPath(ITEM_FOLDER, "handheld")))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER,
                    WoodenObjectTypes.STICK.getName(),
                    path + (itemTier.equals(VanillaWoodenItemTiers.NETHERITE) ? "/netherite" : ""),
                    woodType)))
            .texture("layer1", modLoc(Util.toPath(ITEM_FOLDER, path + (isWood ? "/wooden" : ""), tier)));
    }
}
