package yamahari.ilikewood.provider.tag;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.WoodenPaintingVariants;

public class PaintingTagProvider
    extends TagsProvider<PaintingVariant>
{
    public PaintingTagProvider(
        final DataGenerator generator,
        final Registry<PaintingVariant> registry,
        final String modId,
        final @Nullable ExistingFileHelper existingFileHelper
    )
    {
        super(generator, registry, modId, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TAJ_MAHAL.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COLOSSEUM.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WHITE_HOUSE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.EMPIRE_STATE_BUILDING.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.BUCKINGHAM_PALACE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.STATUE_OF_LIBERTY.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.ACROPOLIS.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.HAGIA_SOPHIA.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.NOTRE_DAME.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PANTHEON.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.BRANDENBURG_GATE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.GOLDEN_GATE_BRIDGE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PONTE_VECCHIO.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TOWER_BRIDGE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MACHU_PICCHU.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COLOGNE_CATHEDRAL.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WOODS.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SNOWY_TAIGA.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SWAMP.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PALM_BEACH.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MANGROVE_SWAMP.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SAVANNA.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DESERT.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SNOWY_MOUNTAIN.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.UNDERWATER.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CAVE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SUNNY_BEACH.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.ARCTIC.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.JUNGLE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.WATERFALL.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CANYON.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.MOON.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.PIGS.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.COW.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CHICKEN.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SHEEP.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.CAT.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DOG.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.HORSE.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.FARM.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.DUCK_POND.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.TRACTOR.get());
        this.tag(PaintingVariantTags.PLACEABLE).add(WoodenPaintingVariants.SUNFLOWERS.get());
    }

}
