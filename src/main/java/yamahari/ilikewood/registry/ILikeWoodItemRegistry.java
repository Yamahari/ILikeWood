package yamahari.ilikewood.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.*;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ILikeWoodItemRegistry extends AbstractILikeWoodObjectRegistry<Item, WoodenItemType> {
    public ILikeWoodItemRegistry() {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void register() {
        registerItems(WoodenItemType.STICK, this::registerStickItem);
        registerItems(WoodenItemType.BOW, this::registerBowItem);
        registerItems(WoodenItemType.CROSSBOW, this::registerCrossbowItem);
        registerItems(WoodenItemType.ITEM_FRAME, this::registerItemFrameItem);
        registerItems(WoodenItemType.FISHING_ROD, this::registerFishingPoleItem);
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(WoodenItemType objectType) {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(objectType))
            .map(woodType -> this.getRegistryObject(woodType, objectType));
    }

    private void registerItems(final WoodenItemType itemType,
                               final Function<IWoodType, RegistryObject<Item>> function) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(itemType))
            .forEach(woodType -> items.put(woodType, function.apply(woodType)));
        this.registryObjects.put(itemType, Collections.unmodifiableMap(items));
    }

    private RegistryObject<Item> register(final IWoodType woodType, final WoodenItemType itemType,
                                          final Supplier<? extends Item> supplier) {
        return this.registry.register(Util.toRegistryName(woodType.getName(), itemType.getName()), supplier);
    }

    private RegistryObject<Item> registerStickItem(final IWoodType woodType) {
        return this.register(woodType,
            WoodenItemType.STICK,
            () -> new WoodenItem(woodType,
                WoodenItemType.STICK,
                new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    }

    private RegistryObject<Item> registerBowItem(final IWoodType woodType) {
        return this.register(woodType, WoodenItemType.BOW, () -> new WoodenBowItem(woodType));
    }

    private RegistryObject<Item> registerCrossbowItem(final IWoodType woodType) {
        return this.register(woodType, WoodenItemType.CROSSBOW, () -> new WoodenCrossbowItem(woodType));
    }

    private RegistryObject<Item> registerItemFrameItem(final IWoodType woodType) {
        return this.register(woodType, WoodenItemType.ITEM_FRAME, () -> new WoodenItemFrameItem(woodType));
    }

    private RegistryObject<Item> registerFishingPoleItem(final IWoodType woodType) {
        return this.register(woodType, WoodenItemType.FISHING_ROD, () -> new WoodenFishingRodItem(woodType));
    }

}
