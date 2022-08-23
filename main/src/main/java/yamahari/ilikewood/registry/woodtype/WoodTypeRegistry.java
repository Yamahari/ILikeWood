package yamahari.ilikewood.registry.woodtype;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.ILikeWood;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodTypeRegistry
    implements IWoodTypeRegistry
{
    private final Map<ResourceLocation, IWoodType> woodTypes = new HashMap<>();

    @Override
    public void register(final IWoodType woodType)
    {
        this.woodTypes.put(new ResourceLocation(woodType.getModId(), woodType.getName()), woodType);
    }

    public IWoodType get(final ResourceLocation resourceLocation)
    {
        if (!this.woodTypes.containsKey(resourceLocation))
        {
            throw new IllegalArgumentException(String.format("wood type [%s] was not registered", resourceLocation.toString()));
        }
        return this.woodTypes.get(resourceLocation);
    }

    public Stream<IWoodType> getWoodTypes()
    {
        try
        {
            final String dataModId = System.getProperty("ilikewood.datagen.modid");
            if (dataModId != null)
            {
                return this.woodTypes.values().stream().filter(woodType -> woodType.getModId().equals(dataModId));
            }
        }
        catch (NullPointerException | SecurityException | IllegalArgumentException e)
        {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return this.woodTypes.values().stream().filter(woodType -> ModList.get().isLoaded(woodType.getModId()));
    }
}
