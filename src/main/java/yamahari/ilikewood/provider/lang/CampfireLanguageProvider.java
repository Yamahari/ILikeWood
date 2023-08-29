package yamahari.ilikewood.provider.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Objects;
import java.util.stream.Stream;

public class CampfireLanguageProvider
    extends LanguageProvider
{
    public CampfireLanguageProvider(final DataGenerator generator)
    {
        super(generator.getPackOutput(), Constants.MOD_ID, "en_us");

    }

    @Override
    protected void addTranslations()
    {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.CAMPFIRE, WoodenBlockType.SOUL_CAMPFIRE))
            .forEach(block -> this.add(block, Util.toTranslationName(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath())));
    }
}
