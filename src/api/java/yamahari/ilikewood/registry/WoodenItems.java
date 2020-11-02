package yamahari.ilikewood.registry;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.IWoodenItemTier;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenItems {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> REGISTRY_OBJECTS;
    static Map<IWoodType, Map<DyeColor, RegistryObject<Item>>> BED_REGISTRY_OBJECTS;
    static Map<WoodenTieredObjectType, Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>>> TIERED_REGISTRY_OBJECTS;

    public static Item getItem(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType).get();
    }

    public static Stream<Item> getItems(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static Stream<Item> getItems(final WoodenObjectType... objectTypes) {
        return Arrays.stream(objectTypes).flatMap(WoodenItems::getItems);
    }

    public static Stream<Item> getBedItems() {
        return getBedRegistryObjects().map(RegistryObject::get);
    }

    public static Stream<Item> getBedItems(final IWoodType woodType) {
        return getBedRegistryObjects(woodType).map(RegistryObject::get);
    }

    public static Item getBedItem(final IWoodType woodType, final DyeColor color) {
        return getBedRegistryObject(woodType, color).get();
    }

    public static Stream<RegistryObject<Item>> getBedRegistryObjects() {
        return BED_REGISTRY_OBJECTS.values().stream().flatMap(m -> m.values().stream());
    }

    public static Stream<RegistryObject<Item>> getBedRegistryObjects(final IWoodType woodType) {
        return Arrays.stream(DyeColor.values()).map(color -> getBedRegistryObject(woodType, color));
    }

    public static RegistryObject<Item> getBedRegistryObject(final IWoodType woodType, final DyeColor color) {
        return BED_REGISTRY_OBJECTS.get(woodType).get(color);
    }

    public static Item getTieredItem(final WoodenTieredObjectType tieredObjectType, final IWoodType woodType, final IWoodenItemTier itemTier) {
        return TIERED_REGISTRY_OBJECTS.get(tieredObjectType).get(woodType).get(itemTier).get();
    }

    public static Stream<Item> getTieredItems(final WoodenTieredObjectType tieredObjectType) {
        return TIERED_REGISTRY_OBJECTS.get(tieredObjectType).values().stream().flatMap(m -> m.values().stream()).map(RegistryObject::get);
    }

    public static Stream<Item> getTieredItems(final WoodenTieredObjectType... tieredObjectTypes) {
        return Arrays.stream(tieredObjectTypes).flatMap(WoodenItems::getTieredItems);
    }

    public static RegistryObject<Item> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    private WoodenItems() {
    }
}
