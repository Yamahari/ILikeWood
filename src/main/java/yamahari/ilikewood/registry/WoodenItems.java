package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.client.tileentity.renderer.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.item.WoodenBlockItem;
import yamahari.ilikewood.item.WoodenItem;
import yamahari.ilikewood.item.WoodenScaffoldingItem;
import yamahari.ilikewood.item.WoodenWallOrFloorItem;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WoodenItems {
    public static final DeferredRegister<Item> REGISTRY = new DeferredRegister<>(ForgeRegistries.ITEMS, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<Item>>> REGISTRY_OBJECTS;

    static {
        final Map<WoodenObjectType, Map<WoodType, RegistryObject<Item>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleBuildingBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleDecorationBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleMiscBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.MISC));

        registryObjects.put(WoodenObjectType.PANELS, registerBlockItems(WoodenObjectType.PANELS, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.STAIRS, registerBlockItems(WoodenObjectType.STAIRS, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.SLAB, registerBlockItems(WoodenObjectType.SLAB, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.BARREL, registerBlockItems(WoodenObjectType.BARREL, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerBlockItems(WoodenObjectType.BOOKSHELF, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerBlockItems(WoodenObjectType.COMPOSTER, simpleMiscBlockItem));
        registryObjects.put(WoodenObjectType.WALL, registerBlockItems(WoodenObjectType.WALL, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.CHEST, registerBlockItems(WoodenObjectType.CHEST, registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS).setISTER(() -> WoodenChestItemStackTileEntityRenderer::new))));
        registryObjects.put(WoodenObjectType.LADDER, registerBlockItems(WoodenObjectType.LADDER, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.STICK, registerSimpleItems(WoodenItems::registerStickItem));
        registryObjects.put(WoodenObjectType.TORCH, registerSimpleItems(WoodenItems::registerTorchItem));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerBlockItems(WoodenObjectType.CRAFTING_TABLE, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerBlockItems(WoodenObjectType.SCAFFOLDING, (objectType, block) -> new WoodenScaffoldingItem(objectType, block.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private WoodenItems() {
    }

    public static Item getItem(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType).get();
    }

    public static Stream<Item> getItems(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    private static Map<WoodType, RegistryObject<Item>> registerBlockItems(final WoodenObjectType objectType, final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> function) {
        final Map<WoodType, RegistryObject<Item>> items = new EnumMap<>(WoodType.class);
        for (final WoodType woodType : WoodType.values()) {
            final RegistryObject<Block> block = WoodenBlocks.getRegistryObject(objectType, woodType);
            items.put(woodType, REGISTRY.register(block.getId().getPath(), () -> function.apply(objectType, block)));
        }
        return Collections.unmodifiableMap(items);
    }

    private static Map<WoodType, RegistryObject<Item>> registerSimpleItems(final Function<WoodType, RegistryObject<Item>> function) {
        final Map<WoodType, RegistryObject<Item>> items = new EnumMap<>(WoodType.class);
        for (final WoodType woodType : WoodType.values()) {
            items.put(woodType, function.apply(woodType));
        }
        return Collections.unmodifiableMap(items);
    }

    private static BiFunction<WoodenObjectType, RegistryObject<Block>, Item> registerSimpleBlockItem(final Item.Properties properties) {
        return (objectType, block) -> new WoodenBlockItem(objectType, block.get(), properties);
    }

    private static RegistryObject<Item> registerStickItem(final WoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.STICK.toString()), () -> new WoodenItem(woodType, WoodenObjectType.STICK, (new Item.Properties()).group(ItemGroup.MATERIALS)));
    }

    private static RegistryObject<Item> registerTorchItem(final WoodType woodType) {
        final RegistryObject<Block> torch = WoodenBlocks.getRegistryObject(WoodenObjectType.TORCH, woodType);
        final RegistryObject<Block> wallTorch = WoodenBlocks.getRegistryObject(WoodenObjectType.WALL_TORCH, woodType);
        return REGISTRY.register(torch.getId().getPath(), () -> new WoodenWallOrFloorItem(WoodenObjectType.TORCH, torch.get(), wallTorch.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS)));
    }
}
