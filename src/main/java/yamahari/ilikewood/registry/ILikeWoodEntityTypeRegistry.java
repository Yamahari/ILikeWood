package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class ILikeWoodEntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.ENTITIES, Constants.MOD_ID);

    static {
        final Map<WoodenObjectType, Map<IWoodType, RegistryObject<EntityType<?>>>> registryObjects = new HashMap<>();

        registryObjects.put(WoodenObjectTypes.ITEM_FRAME,
            registerSimpleEntityTypes(WoodenObjectTypes.ITEM_FRAME,
                ILikeWoodEntityTypeRegistry::registerItemFrameEntityTypes));

        WoodenEntityTypes.REGISTRY_OBJECTS = Collections.unmodifiableMap(registryObjects);
    }

    private ILikeWoodEntityTypeRegistry() {
    }

    private static Map<IWoodType, RegistryObject<EntityType<?>>> registerSimpleEntityTypes(
        final WoodenObjectType objectType, final Function<IWoodType, RegistryObject<EntityType<?>>> function) {
        final Map<IWoodType, RegistryObject<EntityType<?>>> entityTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getObjectTypes().contains(objectType))
            .forEach(woodType -> entityTypes.put(woodType, function.apply(woodType)));
        return Collections.unmodifiableMap(entityTypes);
    }

    private static RegistryObject<EntityType<?>> registerItemFrameEntityTypes(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.getName(), WoodenObjectTypes.ITEM_FRAME.getName());
        return REGISTRY.register(name,
            () -> EntityType.Builder.<WoodenItemFrameEntity>create((entityType, world) -> new WoodenItemFrameEntity(
                woodType,
                entityType,
                world), EntityClassification.MISC)
                .size(0.5F, 0.5F)
                .trackingRange(10)
                .func_233608_b_(Integer.MAX_VALUE)
                .build(name));
    }
}
