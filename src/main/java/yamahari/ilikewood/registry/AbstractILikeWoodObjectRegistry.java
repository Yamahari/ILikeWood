package yamahari.ilikewood.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.registry.objecttype.AbstractWoodenObjectType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class AbstractILikeWoodObjectRegistry<T extends IForgeRegistryEntry<T>, O extends AbstractWoodenObjectType>
    implements IWoodenObjectRegistry<T, O> {

    protected final DeferredRegister<T> registry;
    protected final Map<O, Map<IWoodType, RegistryObject<T>>> registryObjects;

    protected AbstractILikeWoodObjectRegistry(final IForgeRegistry<T> registry) {
        this.registry = DeferredRegister.create(registry, Constants.MOD_ID);
        this.registryObjects = new HashMap<>();
    }

    public final void register(final IEventBus eventBus) {
        this.register();
        this.registry.register(eventBus);
    }

    abstract protected void register();

    @Override
    public final RegistryObject<T> getRegistryObject(final IWoodType woodType, final O objectType)
        throws IllegalArgumentException {
        final Map<IWoodType, RegistryObject<T>> objects = this.registryObjects.get(objectType);
        if (objects != null) {
            final RegistryObject<T> registryObject = objects.get(woodType);
            if (registryObject != null) {
                return registryObject;
            }
            throw new IllegalArgumentException(String.format("ObjectType[%s] has no registered blocks of IWoodType[%s]",
                objectType.getName(),
                woodType.getName()));
        }
        throw new IllegalArgumentException(String.format("ObjectType[%s] has no registered blocks.",
            objectType.getName()));
    }

    @Override
    public final Stream<RegistryObject<T>> getRegistryObjects(final Stream<O> objectTypes) {
        return objectTypes.flatMap(this::getRegistryObjects);
    }

    @Override
    public final T getObject(final IWoodType woodType, final O objectType) throws IllegalArgumentException {
        return this.getRegistryObject(woodType, objectType).get();
    }

    @Override
    public final Stream<T> getObjects(final O objectType) {
        return this.getRegistryObjects(objectType).map(RegistryObject::get);
    }

    @Override
    public final Stream<T> getObjects(final Stream<O> objectTypes) {
        return objectTypes.flatMap(this::getObjects);
    }
}
