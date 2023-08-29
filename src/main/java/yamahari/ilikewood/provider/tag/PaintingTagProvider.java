package yamahari.ilikewood.provider.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.WoodenPaintingVariants;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class PaintingTagProvider extends TagsProvider<PaintingVariant> {
    public PaintingTagProvider(final DataGenerator generator, final ResourceKey<Registry<PaintingVariant>> registryKey, final CompletableFuture<HolderLookup.Provider> lookupProvider, final String modId, final @Nullable ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), registryKey, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull final HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TAJ_MAHAL.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COLOSSEUM.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WHITE_HOUSE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.EMPIRE_STATE_BUILDING.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.BUCKINGHAM_PALACE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.STATUE_OF_LIBERTY.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.ACROPOLIS.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.HAGIA_SOPHIA.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.NOTRE_DAME.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PANTHEON.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.BRANDENBURG_GATE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.GOLDEN_GATE_BRIDGE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PONTE_VECCHIO.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TOWER_BRIDGE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MACHU_PICCHU.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COLOGNE_CATHEDRAL.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WOODS.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SNOWY_TAIGA.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SWAMP.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PALM_BEACH.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MANGROVE_SWAMP.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SAVANNA.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DESERT.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SNOWY_MOUNTAIN.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.UNDERWATER.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CAVE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SUNNY_BEACH.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.ARCTIC.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.JUNGLE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WATERFALL.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CANYON.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MOON.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PIGS.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COW.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CHICKEN.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SHEEP.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CAT.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DOG.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.HORSE.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.FARM.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DUCK_POND.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TRACTOR.getKey());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SUNFLOWERS.getKey());
    }

}
