package yamahari.ilikewood.registry;

import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import yamahari.ilikewood.registry.objecttype.AbstractWoodenObjectType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.stream.Stream;

public interface IWoodenObjectRegistry<T extends IForgeRegistryEntry<? super T>, O extends AbstractWoodenObjectType> {

    /**
     * @param woodType
     * @param objectType
     * @return
     * @throws IllegalArgumentException
     */
    RegistryObject<T> getRegistryObject(IWoodType woodType, O objectType) throws IllegalArgumentException;

    /**
     * @param objectType
     * @return
     */
    Stream<RegistryObject<T>> getRegistryObjects(O objectType);

    /**
     * @param objectTypes
     * @return
     */
    Stream<RegistryObject<T>> getRegistryObjects(final Stream<O> objectTypes);

    /**
     * @param woodType
     * @param objectType
     * @return
     * @throws IllegalArgumentException
     */
    T getObject(final IWoodType woodType, final O objectType) throws IllegalArgumentException;

    /**
     * @param objectType
     * @return
     */
    Stream<T> getObjects(final O objectType);

    /**
     * @param objectTypes
     * @return
     */
    Stream<T> getObjects(final Stream<O> objectTypes);
}
