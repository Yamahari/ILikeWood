package yamahari.ilikewood.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.entity.WoodenItemFrameEntity;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenEntityType;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class ILikeWoodEntityTypeRegistry
    extends AbstractILikeWoodObjectRegistry<EntityType<?>, WoodenEntityType> {

    public ILikeWoodEntityTypeRegistry() {
        super(ForgeRegistries.ENTITIES);
    }

    private void registerItemFrameEntityTypes() {
        final Map<IWoodType, RegistryObject<EntityType<?>>> entityTypes = new HashMap<>();
        ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(WoodenItemType.ITEM_FRAME))
            .forEach(woodType -> entityTypes.put(woodType, registerItemFrameEntityType(woodType)));
        this.registryObjects.put(WoodenEntityType.ITEM_FRAME, Collections.unmodifiableMap(entityTypes));
    }

    private RegistryObject<EntityType<?>> registerItemFrameEntityType(final IWoodType woodType) {
        final String name = Util.toRegistryName(woodType.getName(), WoodenItemType.ITEM_FRAME.getName());
        return this.registry.register(name,
            () -> EntityType.Builder.<WoodenItemFrameEntity>create((entityType, world) -> new WoodenItemFrameEntity(
                woodType,
                entityType,
                world), EntityClassification.MISC)
                .size(0.5F, 0.5F)
                .trackingRange(10)
                .func_233608_b_(Integer.MAX_VALUE)
                .build(name));
    }

    @Override
    protected void register() {
        registerItemFrameEntityTypes();
    }

    @Override
    public Stream<RegistryObject<EntityType<?>>> getRegistryObjects(final WoodenEntityType objectType) {
        return ILikeWood.WOOD_TYPE_REGISTRY
            .getWoodTypes()
            .filter(woodType -> woodType.getItemTypes().contains(WoodenItemType.ITEM_FRAME))
            .map(woodType -> this.getRegistryObject(woodType, objectType));
    }
}
