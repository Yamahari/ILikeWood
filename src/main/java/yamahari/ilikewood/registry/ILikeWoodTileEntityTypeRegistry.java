package yamahari.ilikewood.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ILikeWoodTileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);

    static {
        final EnumMap<WoodenObjectType, Map<IWoodType, RegistryObject<TileEntityType<?>>>> registryObjects = new EnumMap<>(WoodenObjectType.class);

        registryObjects.put(WoodenObjectType.BARREL, registerSimpleTileEntityTypesWith(ILikeWoodTileEntityTypeRegistry::registerBarrelTileEntityType, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.CHEST, registerSimpleTileEntityTypesWith(ILikeWoodTileEntityTypeRegistry::registerChestTileEntityType, Util.HAS_PLANKS));
        registryObjects.put(WoodenObjectType.LECTERN, registerSimpleTileEntityTypesWith(ILikeWoodTileEntityTypeRegistry::registerLecternTileEntityType, Util.HAS_PLANKS));

        WoodenTileEntityTypes.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private ILikeWoodTileEntityTypeRegistry() {
    }

    private static Map<IWoodType, RegistryObject<TileEntityType<?>>> registerSimpleTileEntityTypesWith(final Function<IWoodType, RegistryObject<TileEntityType<?>>> function,
                                                                                                       final Predicate<IWoodType> predicate) {
        final Map<IWoodType, RegistryObject<TileEntityType<?>>> tileEntityTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes()
                .filter(predicate)
                .forEach(woodType -> tileEntityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(tileEntityTypes);
    }

    // TODO data fixer
    private static RegistryObject<TileEntityType<?>> registerBarrelTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.getName(), WoodenObjectType.BARREL.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenBarrelTileEntity(woodType, WoodenTileEntityTypes.REGISTRY_OBJECTS.get(WoodenObjectType.BARREL).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.BARREL, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    // TODO data fixer
    private static RegistryObject<TileEntityType<?>> registerChestTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.getName(), WoodenObjectType.CHEST.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenChestTileEntity(woodType, WoodenTileEntityTypes.REGISTRY_OBJECTS.get(WoodenObjectType.CHEST).get(woodType).get()),
                                WoodenBlocks.getBlock(WoodenObjectType.CHEST, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }

    // TODO data fixer
    private static RegistryObject<TileEntityType<?>> registerLecternTileEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.getName(), WoodenObjectType.LECTERN.toString());
        return REGISTRY.register(name,
                () -> TileEntityType.Builder
                        .create(() -> new WoodenLecternTileEntity(woodType),
                                WoodenBlocks.getBlock(WoodenObjectType.LECTERN, woodType))
                        .build(net.minecraft.util.Util.attemptDataFix(TypeReferences.BLOCK_ENTITY, name)));
    }
}
