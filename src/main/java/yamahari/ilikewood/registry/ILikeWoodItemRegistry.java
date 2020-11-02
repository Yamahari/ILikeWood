package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.item.*;
import yamahari.ilikewood.item.tiered.WoodenHoeItem;
import yamahari.ilikewood.item.tiered.WoodenSwordItem;
import yamahari.ilikewood.item.tiered.tool.WoodenAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenPickAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenShovelItem;
import yamahari.ilikewood.util.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class ILikeWoodItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleBuildingBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleDecorationBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleMiscBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.MISC));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleRedstoneBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.REDSTONE));

        registryObjects.put(WoodenObjectType.PANELS, registerBlockItems(WoodenObjectType.PANELS, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.STAIRS, registerBlockItems(WoodenObjectType.STAIRS, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.SLAB, registerBlockItems(WoodenObjectType.SLAB, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.BARREL, registerBlockItems(WoodenObjectType.BARREL, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerBlockItems(WoodenObjectType.BOOKSHELF, simpleBuildingBlockItem));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerBlockItems(WoodenObjectType.COMPOSTER, simpleMiscBlockItem));
        registryObjects.put(WoodenObjectType.WALL, registerBlockItems(WoodenObjectType.WALL, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.CHEST, registerBlockItems(WoodenObjectType.CHEST, registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS).setISTER(() -> WoodenChestItemStackTileEntityRenderer::new))));
        registryObjects.put(WoodenObjectType.LADDER, registerBlockItems(WoodenObjectType.LADDER, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.STICK, registerSimpleItems(ILikeWoodItemRegistry::registerStickItem));
        registryObjects.put(WoodenObjectType.TORCH, registerSimpleItems(ILikeWoodItemRegistry::registerTorchItem));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerBlockItems(WoodenObjectType.CRAFTING_TABLE, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerBlockItems(WoodenObjectType.SCAFFOLDING, (objectType, block) -> new WoodenScaffoldingItem(objectType, block.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))));
        registryObjects.put(WoodenObjectType.LECTERN, registerBlockItems(WoodenObjectType.LECTERN, simpleRedstoneBlockItem));
        registryObjects.put(WoodenObjectType.POST, registerBlockItems(WoodenObjectType.POST, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.STRIPPED_POST, registerBlockItems(WoodenObjectType.STRIPPED_POST, simpleDecorationBlockItem));
        registryObjects.put(WoodenObjectType.BOW, registerSimpleItems(ILikeWoodItemRegistry::registerBowItem));
        registryObjects.put(WoodenObjectType.CROSSBOW, registerSimpleItems(ILikeWoodItemRegistry::registerCrossbowItem));
        registryObjects.put(WoodenObjectType.ITEM_FRAME, registerSimpleItems(ILikeWoodItemRegistry::registerItemFrameItem));

        WoodenItems.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);

        final Map<WoodenTieredObjectType, Map<IWoodType, Map<WoodenItemTier, RegistryObject<Item>>>> tieredRegistryObjects = new EnumMap<>(WoodenTieredObjectType.class);

        tieredRegistryObjects.put(WoodenTieredObjectType.AXE, registerTieredItems(ILikeWoodItemRegistry::registerAxeItem));
        tieredRegistryObjects.put(WoodenTieredObjectType.HOE, registerTieredItems(ILikeWoodItemRegistry::registerHoeItem));
        tieredRegistryObjects.put(WoodenTieredObjectType.PICKAXE, registerTieredItems(ILikeWoodItemRegistry::registerPickaxeItem));
        tieredRegistryObjects.put(WoodenTieredObjectType.SHOVEL, registerTieredItems(ILikeWoodItemRegistry::registerShovelItem));
        tieredRegistryObjects.put(WoodenTieredObjectType.SWORD, registerTieredItems(ILikeWoodItemRegistry::registerSwordItem));

        WoodenItems.TIERED_REGISTRY_OBJECTS = Collections.unmodifiableMap(tieredRegistryObjects);

        final Map<IWoodType, Map<DyeColor, RegistryObject<Item>>> bedRegistryObjects = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final Map<DyeColor, RegistryObject<Item>> beds = new EnumMap<>(DyeColor.class);
            for (final DyeColor color : DyeColor.values())
                beds.put(color, registerBedItem(woodType, color));
            bedRegistryObjects.put(woodType, beds);
        });

        WoodenItems.BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private ILikeWoodItemRegistry() {
    }


    private static Map<IWoodType, RegistryObject<Item>> registerBlockItems(final WoodenObjectType objectType, final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> function) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final RegistryObject<Block> block = WoodenBlocks.getRegistryObject(objectType, woodType);
            items.put(woodType, REGISTRY.register(block.getId().getPath(), () -> function.apply(objectType, block)));
        });
        return Collections.unmodifiableMap(items);
    }

    private static Map<IWoodType, RegistryObject<Item>> registerSimpleItems(final Function<IWoodType, RegistryObject<Item>> function) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> items.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(items);
    }

    private static Map<IWoodType, Map<WoodenItemTier, RegistryObject<Item>>> registerTieredItems(final BiFunction<IWoodType, WoodenItemTier, RegistryObject<Item>> function) {
        final Map<IWoodType, Map<WoodenItemTier, RegistryObject<Item>>> tieredItems = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType -> {
            final Map<WoodenItemTier, RegistryObject<Item>> items = new EnumMap<>(WoodenItemTier.class);
            for (final WoodenItemTier itemTier : WoodenItemTier.values()) {
                if (itemTier.isWood() && !itemTier.toString().equals(woodType.toString())) continue;
                items.put(itemTier, function.apply(woodType, itemTier));
            }
            tieredItems.put(woodType, Collections.unmodifiableMap(items));
        });
        return Collections.unmodifiableMap(tieredItems);
    }

    private static BiFunction<WoodenObjectType, RegistryObject<Block>, Item> registerSimpleBlockItem(final Item.Properties properties) {
        return (objectType, block) -> new WoodenBlockItem(objectType, block.get(), properties);
    }

    private static RegistryObject<Item> registerStickItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.STICK.toString()), () -> new WoodenItem(woodType, WoodenObjectType.STICK, (new Item.Properties()).group(ItemGroup.MATERIALS)));
    }

    private static RegistryObject<Item> registerTorchItem(final IWoodType woodType) {
        final RegistryObject<Block> torch = WoodenBlocks.getRegistryObject(WoodenObjectType.TORCH, woodType);
        final RegistryObject<Block> wallTorch = WoodenBlocks.getRegistryObject(WoodenObjectType.WALL_TORCH, woodType);
        return REGISTRY.register(torch.getId().getPath(), () -> new WoodenWallOrFloorItem(WoodenObjectType.TORCH, torch.get(), wallTorch.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS)));
    }

    private static RegistryObject<Item> registerHoeItem(final IWoodType woodType, final WoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.toString() + "_") + woodType.toString(), WoodenTieredObjectType.HOE.toString()), () -> new WoodenHoeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerSwordItem(final IWoodType woodType, final WoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.toString() + "_") + woodType.toString(), WoodenTieredObjectType.SWORD.toString()), () -> new WoodenSwordItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerAxeItem(final IWoodType woodType, final WoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.toString() + "_") + woodType.toString(), WoodenTieredObjectType.AXE.toString()), () -> new WoodenAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerPickaxeItem(final IWoodType woodType, final WoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.toString() + "_") + woodType.toString(), WoodenTieredObjectType.PICKAXE.toString()), () -> new WoodenPickAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerShovelItem(final IWoodType woodType, final WoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.toString() + "_") + woodType.toString(), WoodenTieredObjectType.SHOVEL.toString()), () -> new WoodenShovelItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerBowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.BOW.toString()), () -> new WoodenBowItem(woodType));
    }

    private static RegistryObject<Item> registerCrossbowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.CROSSBOW.toString()), () -> new WoodenCrossbowItem(woodType));
    }

    private static RegistryObject<Item> registerItemFrameItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.toString(), WoodenObjectType.ITEM_FRAME.toString()), () -> new WoodenItemFrameItem(woodType));
    }

    private static RegistryObject<Item> registerBedItem(final IWoodType woodType, final DyeColor color) {
        final RegistryObject<Block> bed = WoodenBlocks.getBedRegistryObject(woodType, color);
        return REGISTRY.register(Util.toRegistryName(color.toString(), woodType.toString(), WoodenObjectType.BED.toString()), () -> new WoodenBedItem(bed.get()));
    }
}
