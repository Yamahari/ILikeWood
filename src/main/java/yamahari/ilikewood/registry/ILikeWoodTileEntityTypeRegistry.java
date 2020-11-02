package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ILikeWoodTileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);

    static {
        final EnumMap<WoodenObjectType, Map<IWoodType, RegistryObject<TileEntityType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.BARREL, registerSimpleTileEntityTypes(ILikeWoodTileEntityTypeRegistry::registerBarrelTileEntityType));
        registryObjects.put(WoodenObjectType.CHEST, registerSimpleTileEntityTypes(ILikeWoodTileEntityTypeRegistry::registerChestTileEntityType));
        registryObjects.put(WoodenObjectType.LECTERN, registerSimpleTileEntityTypes(ILikeWoodTileEntityTypeRegistry::registerLecternTileEntityType));

        WoodenTileEntityTypes.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private ILikeWoodTileEntityTypeRegistry() {
    }


    private static Map<IWoodType, RegistryObject<TileEntityType<?>>> registerSimpleTileEntityTypes(final Function<IWoodType, RegistryObject<TileEntityType<?>>> function) {
        final Map<IWoodType, RegistryObject<TileEntityType<?>>> tileEntityTypes = new HashMap<>();
        IWoodType.getLoadedValues().forEach(woodType -> tileEntityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(tileEntityTypes);
    }

    private static RegistryObject<TileEntityType<?>> registerBarrelTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.BARREL.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenBarrelTileEntity(woodType, WoodenTileEntityTypes.REGISTRY_OBJECTS.get(WoodenObjectType.BARREL).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.BARREL, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    private static RegistryObject<TileEntityType<?>> registerChestTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.CHEST.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenChestTileEntity(woodType, WoodenTileEntityTypes.REGISTRY_OBJECTS.get(WoodenObjectType.CHEST).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.CHEST, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    private static RegistryObject<TileEntityType<?>> registerLecternTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.toString(), WoodenObjectType.LECTERN.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenLecternTileEntity(woodType),
                                WoodenBlocks.getBlock(WoodenObjectType.LECTERN, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }
}
