package yamahari.ilikewood.provider.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public final class BedLanguageProvider extends LanguageProvider {
    public BedLanguageProvider(final DataGenerator generator) {
        super(generator, Constants.MOD_ID, "en_us");

    }

    @Override
    protected void addTranslations() {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.getBeds())
            .forEach(block -> this.add(
                block,
                Util.toTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())
            ));
    }
}