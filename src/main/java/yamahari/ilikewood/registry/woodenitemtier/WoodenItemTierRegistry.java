package yamahari.ilikewood.registry.woodenitemtier;

import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.ILikeWood;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class WoodenItemTierRegistry implements IWoodenItemTierRegistry {
    private final List<IWoodenItemTier> woodenItemTiers = new ArrayList<>();

    @Override
    public void register(final IWoodenItemTier woodenItemTier) {
        this.woodenItemTiers.add(woodenItemTier);
    }

    public Stream<IWoodenItemTier> getWoodenItemTiers() {
        try {
            final String dataModId = System.getProperty("ilikewood.datagen.modid");
            if (dataModId != null) {
                return this.woodenItemTiers.stream().filter(itemTier -> !itemTier.isWood() || itemTier.getModId().equals(dataModId));
            }
        } catch (NullPointerException | SecurityException | IllegalArgumentException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return this.woodenItemTiers.stream().filter(itemTier -> ModList.get().isLoaded(itemTier.getModId()));
    }
}
