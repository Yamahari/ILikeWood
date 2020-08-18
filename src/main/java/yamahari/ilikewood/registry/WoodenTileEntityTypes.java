package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public final class WoodenTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);
    private static final Map<WoodenObjectType, Map<WoodType, RegistryObject<TileEntityType<?>>>> REGISTRY_OBJECTS;

    static {
        final EnumMap<WoodenObjectType, Map<WoodType, RegistryObject<TileEntityType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.BARREL, registerSimpleTileEntityTypes(WoodenTileEntityTypes::registerBarrelTileEntityType));
        registryObjects.put(WoodenObjectType.CHEST, registerSimpleTileEntityTypes(WoodenTileEntityTypes::registerChestTileEntityType));
        registryObjects.put(WoodenObjectType.LECTERN, registerSimpleTileEntityTypes(WoodenTileEntityTypes::registerLecternTileEntityType));

        REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private WoodenTileEntityTypes() {
    }

    public static TileEntityType<?> getTileEntityType(final WoodenObjectType objectType, final WoodType woodType) {
        return getRegistryObject(objectType, woodType).get();
    }

    public static Stream<TileEntityType<?>> getTileEntityTypes(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static RegistryObject<TileEntityType<?>> getRegistryObject(final WoodenObjectType objectType, final WoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    private static Map<WoodType, RegistryObject<TileEntityType<?>>> registerSimpleTileEntityTypes(final Function<WoodType, RegistryObject<TileEntityType<?>>> function) {
        final Map<WoodType, RegistryObject<TileEntityType<?>>> tileEntityTypes = new EnumMap<>(WoodType.class);
        WoodType.getLoadedValues().forEach(woodType -> tileEntityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(tileEntityTypes);
    }

    private static RegistryObject<TileEntityType<?>> registerBarrelTileEntityType(final WoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.BARREL.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenBarrelTileEntity(woodType, REGISTRY_OBJECTS.get(WoodenObjectType.BARREL).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.BARREL, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    private static RegistryObject<TileEntityType<?>> registerChestTileEntityType(final WoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.CHEST.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenChestTileEntity(woodType, REGISTRY_OBJECTS.get(WoodenObjectType.CHEST).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.CHEST, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    private static RegistryObject<TileEntityType<?>> registerLecternTileEntityType(final WoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.LECTERN.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenLecternTileEntity(woodType),
                                WoodenBlocks.getBlock(WoodenObjectType.LECTERN, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }
}
