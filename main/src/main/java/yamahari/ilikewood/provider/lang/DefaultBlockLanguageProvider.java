package yamahari.ilikewood.provider.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public class DefaultBlockLanguageProvider extends LanguageProvider {
    private final WoodenBlockType blockType;

    public DefaultBlockLanguageProvider(final DataGenerator generator, final WoodenBlockType blockType) {
        super(generator, Constants.MOD_ID, "en_us");
        this.blockType = blockType;
    }

    @Override
    protected void addTranslations() {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(this.blockType)
            .forEach(block -> this.add(block,
                Util.toTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));
    }
}
