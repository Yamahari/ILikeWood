package yamahari.ilikewood.registry.woodtype;

import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.ILikeWood;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class WoodTypeRegistry
    implements IWoodTypeRegistry
{
    private final List<IWoodType> woodTypes = new ArrayList<>();

    @Override
    public void register(final IWoodType woodType)
    {
        this.woodTypes.add(woodType);
    }

    public Stream<IWoodType> getWoodTypes()
    {
        try
        {
            final String dataModId = System.getProperty("ilikewood.datagen.modid");
            if (dataModId != null)
            {
                return this.woodTypes.stream().filter(woodType -> woodType.getModId().equals(dataModId));
            }
        }
        catch (NullPointerException | SecurityException | IllegalArgumentException e)
        {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return this.woodTypes.stream().filter(woodType -> ModList.get().isLoaded(woodType.getModId()));
    }
}
