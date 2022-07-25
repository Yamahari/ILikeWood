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

public final class PostLanguageProvider extends LanguageProvider
{
    public PostLanguageProvider(final DataGenerator generator) {
        super(generator, Constants.MOD_ID, "en_us");

    }

    @Override
    protected void addTranslations() {
        ILikeWood.BLOCK_REGISTRY.getObjects(Stream.of(WoodenBlockType.POST, WoodenBlockType.STRIPPED_POST)).forEach(
            block -> this.add(block,
                Util.toTranslationName(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath())
            ));
    }
}
