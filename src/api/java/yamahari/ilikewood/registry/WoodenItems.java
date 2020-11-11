package yamahari.ilikewood.registry;

import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenItems {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> REGISTRY_OBJECTS;
    static Map<IWoodType, Map<DyeColor, RegistryObject<Item>>> BED_REGISTRY_OBJECTS;
    static Map<WoodenTieredObjectType, Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>>> TIERED_REGISTRY_OBJECTS;

    public static RegistryObject<Item> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static Stream<RegistryObject<Item>> getRegistryObjects(final WoodenObjectType woodenObjectType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).values().stream();
    }

    public static Stream<RegistryObject<Item>> getRegistryObjects(final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenItems::getRegistryObjects);
    }


    public static Item getItem(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return getRegistryObject(woodenObjectType, woodType).get();
    }

    public static Stream<Item> getItems(final WoodenObjectType woodenObjectType) {
        return getRegistryObjects(woodenObjectType).map(RegistryObject::get);
    }

    public static Stream<Item> getItems(final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenItems::getItems);
    }


    public static RegistryObject<Item> getTieredRegistryObject(final WoodenTieredObjectType woodenTieredObjectType, final IWoodType woodType, final IWoodenItemTier woodenItemTier) {
        return TIERED_REGISTRY_OBJECTS.get(woodenTieredObjectType).get(woodType).get(woodenItemTier);
    }

    public static Stream<RegistryObject<Item>> getTieredRegistryObjects(final WoodenTieredObjectType woodenTieredObjectType, final IWoodType woodType) {
        return TIERED_REGISTRY_OBJECTS.get(woodenTieredObjectType).get(woodType).values().stream();
    }

    public static Stream<RegistryObject<Item>> getTieredRegistryObjects(final WoodenTieredObjectType woodenTieredObjectType) {
        return TIERED_REGISTRY_OBJECTS.get(woodenTieredObjectType).values().stream().flatMap(m -> m.values().stream());
    }

    public static Stream<RegistryObject<Item>> getTieredRegistryObjects(final WoodenTieredObjectType... woodenTieredObjectTypes) {
        return Arrays.stream(woodenTieredObjectTypes).flatMap(WoodenItems::getTieredRegistryObjects);
    }


    public static Item getTieredItem(final WoodenTieredObjectType woodenTieredObjectType, final IWoodType woodType, final IWoodenItemTier woodenItemTier) {
        return getTieredRegistryObject(woodenTieredObjectType, woodType, woodenItemTier).get();
    }

    public static Stream<Item> getTieredItems(final WoodenTieredObjectType woodenTieredObjectType, final IWoodType woodType) {
        return getTieredRegistryObjects(woodenTieredObjectType, woodType).map(RegistryObject::get);
    }

    public static Stream<Item> getTieredItems(final WoodenTieredObjectType woodenTieredObjectType) {
        return getTieredRegistryObjects(woodenTieredObjectType).map(RegistryObject::get);
    }

    public static Stream<Item> getTieredItems(final WoodenTieredObjectType... woodenTieredObjectTypes) {
        return getTieredRegistryObjects(woodenTieredObjectTypes).map(RegistryObject::get);
    }


    public static RegistryObject<Item> getBedRegistryObject(final IWoodType woodType, final DyeColor dyeColor) {
        return BED_REGISTRY_OBJECTS.get(woodType).get(dyeColor);
    }

    public static Stream<RegistryObject<Item>> getBedRegistryObjects(final IWoodType woodType) {
        return Arrays.stream(DyeColor.values()).map(dyeColor -> getBedRegistryObject(woodType, dyeColor));
    }

    public static Stream<RegistryObject<Item>> getBedRegistryObjects() {
        return BED_REGISTRY_OBJECTS.values().stream().flatMap(m -> m.values().stream());
    }


    public static Item getBedItem(final IWoodType woodType, final DyeColor dyeColor) {
        return getBedRegistryObject(woodType, dyeColor).get();
    }

    public static Stream<Item> getBedItems(final IWoodType woodType) {
        return getBedRegistryObjects(woodType).map(RegistryObject::get);
    }

    public static Stream<Item> getBedItems() {
        return getBedRegistryObjects().map(RegistryObject::get);
    }

    private WoodenItems() {
    }
}
