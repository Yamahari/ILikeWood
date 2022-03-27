package yamahari.ilikewood.provider.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public final class ContainerBlockLanguageProvider extends LanguageProvider {
    private final WoodenBlockType blockType;

    public ContainerBlockLanguageProvider(final DataGenerator generator, final WoodenBlockType blockType) {
        super(generator, Constants.MOD_ID, "en_us");
        this.blockType = blockType;
    }

    @Override
    protected void addTranslations() {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(this.blockType)
            .forEach(block -> {
                final String path = Objects.requireNonNull(block.getRegistryName()).getPath();
                final String name = Util.toTranslationName(path);
                this.add(block, name);
                this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, path), name);
            });
    }
}