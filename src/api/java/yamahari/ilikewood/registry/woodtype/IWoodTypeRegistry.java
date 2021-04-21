package yamahari.ilikewood.registry.woodtype;

import java.util.stream.Stream;

public interface IWoodTypeRegistry {
    void register(IWoodType woodType);

    Stream<IWoodType> getWoodTypes();
}
