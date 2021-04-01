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
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ILikeWoodItemRegistry {
    public static final DeferredRegister<Item> REGISTRY =
        DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> registryObjects = new HashMap<>();

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleBuildingBlockItem =
            registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS));

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleDecorationBlockItem =
            registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.DECORATIONS));

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleMiscBlockItem =
            registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.MISC));

        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> simpleRedstoneBlockItem =
            registerSimpleBlockItem((new Item.Properties()).group(ItemGroup.REDSTONE));

        Stream
            .of(WoodenObjectTypes.PANELS, WoodenObjectTypes.STAIRS, WoodenObjectTypes.SLAB, WoodenObjectTypes.BOOKSHELF)
            .forEach(objectType -> registerBlocksItems(registryObjects, objectType, simpleBuildingBlockItem));

        Stream
            .of(WoodenObjectTypes.BARREL,
                WoodenObjectTypes.WALL,
                WoodenObjectTypes.LADDER,
                WoodenObjectTypes.CRAFTING_TABLE,
                WoodenObjectTypes.POST,
                WoodenObjectTypes.STRIPPED_POST,
                WoodenObjectTypes.SAWMILL)
            .forEach(objectType -> registerBlocksItems(registryObjects, objectType, simpleDecorationBlockItem));

        Stream
            .of(WoodenObjectTypes.COMPOSTER)
            .forEach(objectType -> registerBlocksItems(registryObjects, objectType, simpleMiscBlockItem));

        Stream
            .of(WoodenObjectTypes.LECTERN)
            .forEach(objectType -> registerBlocksItems(registryObjects, objectType, simpleRedstoneBlockItem));

        registryObjects.put(WoodenObjectTypes.CHEST,
            registerBlockItems(WoodenObjectTypes.CHEST,
                registerSimpleBlockItem((new Item.Properties())
                    .group(ItemGroup.DECORATIONS)
                    .setISTER(() -> WoodenChestItemStackTileEntityRenderer::new))));

        registerSimpleItems(registryObjects, WoodenObjectTypes.STICK, ILikeWoodItemRegistry::registerStickItem);
        registerSimpleItems(registryObjects, WoodenObjectTypes.TORCH, ILikeWoodItemRegistry::registerTorchItem);
        registerSimpleItems(registryObjects,
            WoodenObjectTypes.SOUL_TORCH,
            ILikeWoodItemRegistry::registerSoulTorchItem);

        registryObjects.put(WoodenObjectTypes.SCAFFOLDING,
            registerBlockItems(WoodenObjectTypes.SCAFFOLDING,
                (objectType, block) -> new WoodenScaffoldingItem(objectType,
                    block.get(),
                    (new Item.Properties()).group(ItemGroup.DECORATIONS))));

        registerSimpleItems(registryObjects, WoodenObjectTypes.BOW, ILikeWoodItemRegistry::registerBowItem);
        registerSimpleItems(registryObjects, WoodenObjectTypes.CROSSBOW, ILikeWoodItemRegistry::registerCrossbowItem);
        registerSimpleItems(registryObjects,
            WoodenObjectTypes.ITEM_FRAME,
            ILikeWoodItemRegistry::registerItemFrameItem);

        registerSimpleItems(registryObjects,
            WoodenObjectTypes.FISHING_ROD,
            ILikeWoodItemRegistry::registerFishingPoleItem);

        WoodenItems.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);

        final Map<WoodenTieredObjectType, Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>>>
            tieredRegistryObjects = new HashMap<>();

        tieredRegistryObjects.put(WoodenTieredObjectTypes.AXE,
            registerTieredItems(WoodenTieredObjectTypes.AXE, ILikeWoodItemRegistry::registerAxeItem));

        tieredRegistryObjects.put(WoodenTieredObjectTypes.HOE,
            registerTieredItems(WoodenTieredObjectTypes.HOE, ILikeWoodItemRegistry::registerHoeItem));

        tieredRegistryObjects.put(WoodenTieredObjectTypes.PICKAXE,
            registerTieredItems(WoodenTieredObjectTypes.PICKAXE, ILikeWoodItemRegistry::registerPickaxeItem));

        tieredRegistryObjects.put(WoodenTieredObjectTypes.SHOVEL,
            registerTieredItems(WoodenTieredObjectTypes.SHOVEL, ILikeWoodItemRegistry::registerShovelItem));

        tieredRegistryObjects.put(WoodenTieredObjectTypes.SWORD,
            registerTieredItems(WoodenTieredObjectTypes.SWORD, ILikeWoodItemRegistry::registerSwordItem));

        WoodenItems.TIERED_REGISTRY_OBJECTS = Collections.unmodifiableMap(tieredRegistryObjects);

        final Map<IWoodType, Map<DyeColor, RegistryObject<Item>>> bedRegistryObjects = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getObjectTypes().contains(WoodenObjectTypes.BED))
            .forEach(woodType -> {
                final Map<DyeColor, RegistryObject<Item>> beds = new EnumMap<>(DyeColor.class);
                for (final DyeColor color : DyeColor.values()) {
                    beds.put(color, registerBedItem(woodType, color));
                }
                bedRegistryObjects.put(woodType, beds);
            });

        WoodenItems.BED_REGISTRY_OBJECTS = Collections.unmodifiableMap(bedRegistryObjects);
    }

    private ILikeWoodItemRegistry() {
    }

    private static Map<IWoodType, RegistryObject<Item>> registerBlockItems(final WoodenObjectType objectType,
                                                                           final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> function) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getObjectTypes().contains(objectType))
            .forEach(woodType -> {
                final RegistryObject<Block> block = WoodenBlocks.getRegistryObject(objectType, woodType);
                items.put(woodType,
                    REGISTRY.register(block.getId().getPath(), () -> function.apply(objectType, block)));
            });
        return Collections.unmodifiableMap(items);
    }

    private static void registerBlocksItems(
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> registryObjects,
        final WoodenObjectType woodenObjectType,
        final BiFunction<WoodenObjectType, RegistryObject<Block>, Item> function) {
        registryObjects.put(woodenObjectType, registerBlockItems(woodenObjectType, function));
    }

    private static Map<IWoodType, RegistryObject<Item>> registerSimpleItems(final WoodenObjectType objectType,
                                                                            final Function<IWoodType, RegistryObject<Item>> function) {
        final Map<IWoodType, RegistryObject<Item>> items = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getObjectTypes().contains(objectType))
            .forEach(woodType -> items.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(items);
    }

    private static void registerSimpleItems(
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<Item>>> registryObjects,
        final WoodenObjectType woodenObjectType, final Function<IWoodType, RegistryObject<Item>> function) {
        registryObjects.put(woodenObjectType, registerSimpleItems(woodenObjectType, function));
    }

    private static Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> registerTieredItems(
        final WoodenTieredObjectType tieredObjectType,
        final BiFunction<IWoodType, IWoodenItemTier, RegistryObject<Item>> function) {
        final Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> tieredItems = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getTieredObjectTypes().contains(tieredObjectType))
            .forEach(woodType -> {
                final Map<IWoodenItemTier, RegistryObject<Item>> items = new HashMap<>();
                ILikeWood.WOODEN_ITEM_TIER_REGISTRY
                    .getWoodenItemTiers()
                    .filter(itemTier -> !itemTier.isWood() || itemTier.getWoodType().equals(woodType))
                    .forEach(itemTier -> items.put(itemTier, function.apply(woodType, itemTier)));
                tieredItems.put(woodType, Collections.unmodifiableMap(items));
            });
        return Collections.unmodifiableMap(tieredItems);
    }

    private static BiFunction<WoodenObjectType, RegistryObject<Block>, Item> registerSimpleBlockItem(
        final Item.Properties properties) {
        return (objectType, block) -> new WoodenBlockItem(objectType, block.get(), properties);
    }

    private static RegistryObject<Item> registerStickItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.STICK.getName()),
            () -> new WoodenItem(woodType,
                WoodenObjectTypes.STICK,
                (new Item.Properties()).group(ItemGroup.MATERIALS)));
    }

    private static RegistryObject<Item> registerTorchItem(final WoodenObjectType torchObjectType,
                                                          final WoodenObjectType wallTorchObjectType,
                                                          final IWoodType woodType) {
        final RegistryObject<Block> torch = WoodenBlocks.getRegistryObject(torchObjectType, woodType);
        final RegistryObject<Block> wallTorch = WoodenBlocks.getRegistryObject(wallTorchObjectType, woodType);
        return REGISTRY.register(torch.getId().getPath(),
            () -> new WoodenWallOrFloorItem(torchObjectType,
                torch.get(),
                wallTorch.get(),
                (new Item.Properties()).group(ItemGroup.DECORATIONS)));
    }

    private static RegistryObject<Item> registerTorchItem(final IWoodType woodType) {
        return registerTorchItem(WoodenObjectTypes.TORCH, WoodenObjectTypes.WALL_TORCH, woodType);
    }

    private static RegistryObject<Item> registerSoulTorchItem(final IWoodType woodType) {
        return registerTorchItem(WoodenObjectTypes.SOUL_TORCH, WoodenObjectTypes.WALL_SOUL_TORCH, woodType);
    }

    private static RegistryObject<Item> registerHoeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredObjectTypes.HOE.getName()), () -> new WoodenHoeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerSwordItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredObjectTypes.SWORD.getName()), () -> new WoodenSwordItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerAxeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredObjectTypes.AXE.getName()), () -> new WoodenAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerPickaxeItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredObjectTypes.PICKAXE.getName()), () -> new WoodenPickAxeItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerShovelItem(final IWoodType woodType, final IWoodenItemTier itemTier) {
        return REGISTRY.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredObjectTypes.SHOVEL.getName()), () -> new WoodenShovelItem(woodType, itemTier));
    }

    private static RegistryObject<Item> registerBowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.BOW.getName()),
            () -> new WoodenBowItem(woodType));
    }

    private static RegistryObject<Item> registerCrossbowItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.CROSSBOW.getName()),
            () -> new WoodenCrossbowItem(woodType));
    }

    private static RegistryObject<Item> registerItemFrameItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.ITEM_FRAME.getName()),
            () -> new WoodenItemFrameItem(woodType));
    }

    private static RegistryObject<Item> registerBedItem(final IWoodType woodType, final DyeColor color) {
        final RegistryObject<Block> bed = WoodenBlocks.getBedRegistryObject(woodType, color);
        return REGISTRY.register(Util.toRegistryName(color.toString(),
            woodType.getName(),
            WoodenObjectTypes.BED.getName()), () -> new WoodenBedItem(bed.get()));
    }

    private static RegistryObject<Item> registerFishingPoleItem(final IWoodType woodType) {
        return REGISTRY.register(Util.toRegistryName(woodType.getName(), WoodenObjectTypes.FISHING_ROD.getName()),
            () -> new WoodenFishingRodItem(woodType));
    }
}
