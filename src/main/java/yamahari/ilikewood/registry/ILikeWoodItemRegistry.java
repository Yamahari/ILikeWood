package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.item.*;
import yamahari.ilikewood.item.tiered.WoodenHoeItem;
import yamahari.ilikewood.item.tiered.WoodenSwordItem;
import yamahari.ilikewood.item.tiered.tool.WoodenAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenPickAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenShovelItem;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ILikeWoodItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleBuildingBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleDecorationBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleMiscBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.MISC));
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleRedstoneBlockItem = registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.REDSTONE));

        registryObjects.put(WoodenObjectType.PANELS, registerBlockItemsWith(WoodenObjectType.PANELS, simpleBuildingBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.STAIRS, registerBlockItemsWith(WoodenObjectType.STAIRS, simpleBuildingBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.SLAB, registerBlockItemsWith(WoodenObjectType.SLAB, simpleBuildingBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.BARREL, registerBlockItemsWith(WoodenObjectType.BARREL, simpleDecorationBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.BOOKSHELF, registerBlockItemsWith(WoodenObjectType.BOOKSHELF, simpleBuildingBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.COMPOSTER, registerBlockItemsWith(WoodenObjectType.COMPOSTER, simpleMiscBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.WALL, registerBlockItemsWith(WoodenObjectType.WALL, simpleDecorationBlockItem, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.CHEST, registerBlockItemsWith(WoodenObjectType.CHEST, registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS).setISTER(() -> WoodenChestItemStackTileEntityRenderer::new)), Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.LADDER, registerBlockItemsWith(WoodenObjectType.LADDER, simpleDecorationBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.STICK, registerSimpleItemsWith(ILikeWoodItemRegistry::registerStickItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.TORCH, registerSimpleItemsWith(ILikeWoodItemRegistry::registerTorchItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.CRAFTING_TABLE, registerBlockItemsWith(WoodenObjectType.CRAFTING_TABLE, simpleDecorationBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.SCAFFOLDING, registerBlockItemsWith(WoodenObjectType.SCAFFOLDING, (objectType, block) -> new WoodenScaffoldingItem(objectType, block.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS)), Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.LECTERN, registerBlockItemsWith(WoodenObjectType.LECTERN, simpleRedstoneBlockItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.POST, registerBlockItemsWith(WoodenObjectType.POST, simpleDecorationBlockItem, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.STRIPPED_POST, registerBlockItemsWith(WoodenObjectType.STRIPPED_POST, simpleDecorationBlockItem, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)));
        registryObjects.put(WoodenObjectType.BOW, registerSimpleItemsWith(ILikeWoodItemRegistry::registerBowItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.CROSSBOW, registerSimpleItemsWith(ILikeWoodItemRegistry::registerCrossbowItem, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.ITEM_FRAME, registerSimpleItemsWith(ILikeWoodItemRegistry::registerItemFrameItem, Util.HAS_PLANKS));

        WoodenItems.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);

        final Map<WoodenTieredObjectType, Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>>> tieredRegistryObjects = new EnumMap<>(WoodenTieredObjectType.class);

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


    private static Map<IWoodType, RegistryObject<Item>> registerBlockItemsWith(final WoodenObjectType objectType,
                                                                               final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> function,
                                                                               final Predicate<IWoodType> predicate) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(predicate)
                .forEach(woodType -> {
                    final RegistryObject<Block> block = WoodenBlocks.getRegistryObject(objectType, woodType);
                    items.put(woodType, REGISTRY.register(block.getId().getPath(), () -> function.apply(objectType, block)));
                });
        return Collections.unmodifiableMap(items);
    }

    private static Map<IWoodType, RegistryObject<Item>> registerSimpleItemsWith(final Function<IWoodType, RegistryObject<Item>> function,
                                                                                final Predicate<IWoodType> predicate) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(predicate)
                .forEach(woodType -> items.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(items);
    }

    private static Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> registerTieredItems(final BiFunction<IWoodType, IWoodenItemTier, RegistryObject<Item>> function) {
        final Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> tieredItems = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(Util.HAS_PLANKS)
                .forEach(woodType -> {
                    final Map<IWoodenItemTier, RegistryObject<Item>> items = new HashMap<>();
                    ILikeWood.WOODEN_ITEM_TIER_REGISTRY.getWoodenItemTiers()
                            .filter(itemTier -> !itemTier.isWood() || itemTier.getWoodType().equals(woodType))
                            .forEach(itemTier -> items.put(itemTier, function.apply(woodType, itemTier)));
                    tieredItems.put(woodType, Collections.unmodifiableMap(items));
                });
        return Collections.unmodifiableMap(tieredItems);
    }

    private static BiFunction<WoodenObjectType, RegistryObject<Block>, Item> registerSimpleBlockItem(final Item.Properties properties) {
        return (objectType, block) -> new WoodenBlockItem(objectType, block.get(), properties);
    }

    private static RegistryObject<Item> registerStickItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.STICK.toString()), () -> new WoodenItem(woodType, WoodenObjectType.STICK, (new Item.Properties()).group(ItemGroup.MATERIALS)));
    }

    private static RegistryObject<Item> registerTorchItem(final IWoodType woodType) {
        final RegistryObject<Block> torch = WoodenBlocks.getRegistryObject(WoodenObjectType.TORCH, woodType);
        final RegistryObject<Block> wallTorch = WoodenBlocks.getRegistryObject(WoodenObjectType.WALL_TORCH, woodType);
        return REGISTRY.register(torch.getId().getPath(), () -> new WoodenWallOrFloorItem(WoodenObjectType.TORCH, torch.get(), wallTorch.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS)));
    }

    private static RegistryObject<Item> registerHoeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(), WoodenTieredObjectType.HOE.toString()), () -> new WoodenHoeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerSwordItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(), WoodenTieredObjectType.SWORD.toString()), () -> new WoodenSwordItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerAxeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(), WoodenTieredObjectType.AXE.toString()), () -> new WoodenAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerPickaxeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(), WoodenTieredObjectType.PICKAXE.toString()), () -> new WoodenPickAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerShovelItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName((itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(), WoodenTieredObjectType.SHOVEL.toString()), () -> new WoodenShovelItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerBowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.BOW.toString()), () -> new WoodenBowItem(woodType));
    }

    private static RegistryObject<Item> registerCrossbowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.CROSSBOW.toString()), () -> new WoodenCrossbowItem(woodType));
    }

    private static RegistryObject<Item> registerItemFrameItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectType.ITEM_FRAME.toString()), () -> new WoodenItemFrameItem(woodType));
    }

    private static RegistryObject<Item> registerBedItem(final IWoodType woodType, final DyeColor color) {
        final RegistryObject<Block> bed = WoodenBlocks.getBedRegistryObject(woodType, color);
        return REGISTRY.register(Util.toRegistryName(color.toString(), woodType.getName(), WoodenObjectType.BED.toString()), () -> new WoodenBedItem(bed.get()));
    }
}
