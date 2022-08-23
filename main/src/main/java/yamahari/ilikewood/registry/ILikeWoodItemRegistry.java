package yamahari.ilikewood.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.WoodenBowItem;
import yamahari.ilikewood.item.WoodenCrossbowItem;
import yamahari.ilikewood.item.WoodenFishingRodItem;
import yamahari.ilikewood.item.WoodenItem;
import yamahari.ilikewood.item.WoodenItemFrameItem;
import yamahari.ilikewood.item.WoodenPaintingItem;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ILikeWoodItemRegistry
    extends AbstractILikeWoodObjectRegistry<Item, WoodenItemType>
{
    public ILikeWoodItemRegistry()
    {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void register()
    {
        registerItems(WoodenItemType.STICK, this::registerStickItem);
        registerItems(WoodenItemType.BOW, this::registerBowItem);
        registerItems(WoodenItemType.CROSSBOW, this::registerCrossbowItem);
        registerItems(WoodenItemType.ITEM_FRAME, this::registerItemFrameItem);
        registerItems(WoodenItemType.FISHING_ROD, this::registerFishingPoleItem);
        registerItems(WoodenItemType.PAINTING, this::registerPaintingItem);
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(WoodenItemType objectType)
    {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(objectType))
            .map(woodType -> this.getRegistryObject(woodType, objectType));
    }

    private void registerItems(
        final WoodenItemType itemType,
        final Function<IWoodType, RegistryObject<Item>> function
    )
    {
        if (itemType.isEnabled())
        {
            final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
            ILikeWood.WOOD_TYPE_REGISTRY
                .getWoodTypes()
                .filter(woodType -> woodType.getItemTypes().contains(itemType))
                .forEach(woodType -> items.put(woodType, function.apply(woodType)));
            this.registryObjects.put(itemType, Collections.unmodifiableMap(items));
        }
    }

    private RegistryObject<Item> register(
        final IWoodType woodType,
        final WoodenItemType itemType,
        final Supplier<? extends Item> supplier
    )
    {
        final String name;
        if (woodType.getModId().equals(Constants.MOD_ID))
        {
            name = Util.toRegistryName(woodType.getName(), itemType.getName());
        }
        else
        {
            name = Util.toRegistryName(woodType.getModId(), woodType.getName(), itemType.getName());
        }

        return this.registry.register(name, supplier);
    }

    private RegistryObject<Item> registerStickItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.STICK,
            () -> new WoodenItem(woodType, WoodenItemType.STICK, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS))
        );
    }

    private RegistryObject<Item> registerBowItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.BOW, () -> new WoodenBowItem(woodType));
    }

    private RegistryObject<Item> registerCrossbowItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.CROSSBOW, () -> new WoodenCrossbowItem(woodType));
    }

    private RegistryObject<Item> registerItemFrameItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.ITEM_FRAME, () -> new WoodenItemFrameItem(woodType));
    }

    private RegistryObject<Item> registerFishingPoleItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.FISHING_ROD, () -> new WoodenFishingRodItem(woodType));
    }

    private RegistryObject<Item> registerPaintingItem(final IWoodType woodType)
    {
        return this.register(woodType, WoodenItemType.PAINTING, () -> new WoodenPaintingItem(woodType));
    }
}