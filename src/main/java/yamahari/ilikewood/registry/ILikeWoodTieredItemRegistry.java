package yamahari.ilikewood.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.WoodenHoeItem;
import yamahari.ilikewood.item.tiered.WoodenSwordItem;
import yamahari.ilikewood.item.tiered.tool.WoodenAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenPickAxeItem;
import yamahari.ilikewood.item.tiered.tool.WoodenShovelItem;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public final class ILikeWoodTieredItemRegistry implements IWoodenTieredItemRegistry {
    private final DeferredRegister<Item> registry;
    private final Map<WoodenTieredItemType, Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>>>
        tieredRegistryObjects;

    public ILikeWoodTieredItemRegistry() {
        this.registry = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
        this.tieredRegistryObjects = new HashMap<>();
    }

    @Override
    public RegistryObject<Item> getRegistryObject(final IWoodenItemTier woodenItemTier, final IWoodType woodType,
                                                  final WoodenTieredItemType tieredItemType)
        throws IllegalArgumentException {
        final Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> objects =
            tieredRegistryObjects.get(tieredItemType);
        if (objects != null) {
            final Map<IWoodenItemTier, RegistryObject<Item>> tieredObjects = objects.get(woodType);
            if (tieredObjects != null) {
                final RegistryObject<Item> tieredObject = tieredObjects.get(woodenItemTier);
                if (tieredObject != null) {
                    return tieredObject;
                }
                throw new IllegalArgumentException("");
            }
            throw new IllegalArgumentException("");
        }
        throw new IllegalArgumentException("");
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(final IWoodType woodType,
                                                           final WoodenTieredItemType tieredItemType)
        throws IllegalArgumentException {
        return ILikeWood.WOODEN_ITEM_TIER_REGISTRY
            .getWoodenItemTiers()
            .filter(itemTier -> !itemTier.isWood() || itemTier.getWoodType().equals(woodType))
            .map(woodenItemTier -> this.getRegistryObject(woodenItemTier, woodType, tieredItemType));
    }

    @Override
    public Stream<RegistryObject<Item>> getRegistryObjects(final WoodenTieredItemType tieredItemType) {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getTieredItemTypes().contains(tieredItemType))
            .flatMap(woodType -> this.getRegistryObjects(woodType, tieredItemType));
    }

    @Override
    public Item getObject(final IWoodenItemTier woodenItemTier, final IWoodType woodType,
                          final WoodenTieredItemType tieredItemType) throws IllegalArgumentException {
        return this.getRegistryObject(woodenItemTier, woodType, tieredItemType).get();
    }

    @Override
    public Stream<Item> getObjects(final IWoodType woodType, final WoodenTieredItemType tieredItemType)
        throws IllegalArgumentException {
        return this.getRegistryObjects(woodType, tieredItemType).map(RegistryObject::get);
    }

    @Override
    public Stream<Item> getObjects(final WoodenTieredItemType tieredItemType) {
        return this.getRegistryObjects(tieredItemType).map(RegistryObject::get);
    }

    public void register(final IEventBus eventBus) {
        this.registerTieredItems(WoodenTieredItemType.AXE, this::registerAxeItem);
        this.registerTieredItems(WoodenTieredItemType.HOE, this::registerHoeItem);
        this.registerTieredItems(WoodenTieredItemType.PICKAXE, this::registerPickaxeItem);
        this.registerTieredItems(WoodenTieredItemType.SHOVEL, this::registerShovelItem);
        this.registerTieredItems(WoodenTieredItemType.SWORD, this::registerSwordItem);

        this.registry.register(eventBus);
    }

    private void registerTieredItems(final WoodenTieredItemType tieredItemType,
                                     final BiFunction<IWoodenItemTier, IWoodType, RegistryObject<Item>> function) {

        final Map<IWoodType, Map<IWoodenItemTier, RegistryObject<Item>>> tieredRegistryObjects = new HashMap<>();

        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getTieredItemTypes().contains(tieredItemType))
            .forEach(woodType -> {
                final Map<IWoodenItemTier, RegistryObject<Item>> registryObjects = new HashMap<>();
                ILikeWood.WOODEN_ITEM_TIER_REGISTRY
                    .getWoodenItemTiers()
                    .filter(itemTier -> !itemTier.isWood() || itemTier.getWoodType().equals(woodType))
                    .forEach(itemTier -> registryObjects.put(itemTier, function.apply(itemTier, woodType)));
                tieredRegistryObjects.put(woodType, registryObjects);
            });

        this.tieredRegistryObjects.put(tieredItemType, Collections.unmodifiableMap(tieredRegistryObjects));
    }

    private RegistryObject<Item> registerHoeItem(final IWoodenItemTier itemTier, final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredItemType.HOE.getName()), () -> new WoodenHoeItem(woodType, itemTier));
    }

    private RegistryObject<Item> registerSwordItem(final IWoodenItemTier itemTier, final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredItemType.SWORD.getName()), () -> new WoodenSwordItem(woodType, itemTier));
    }

    private RegistryObject<Item> registerAxeItem(final IWoodenItemTier itemTier, final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredItemType.AXE.getName()), () -> new WoodenAxeItem(woodType, itemTier));
    }

    private RegistryObject<Item> registerPickaxeItem(final IWoodenItemTier itemTier, final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredItemType.PICKAXE.getName()), () -> new WoodenPickAxeItem(woodType, itemTier));
    }

    private RegistryObject<Item> registerShovelItem(final IWoodenItemTier itemTier, final IWoodType woodType) {
        return this.registry.register(Util.toRegistryName(
            (itemTier.isWood() ? "" : itemTier.getName() + "_") + woodType.getName(),
            WoodenTieredItemType.SHOVEL.getName()), () -> new WoodenShovelItem(woodType, itemTier));
    }
}
