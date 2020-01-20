package yamahari.ilikewood.provider;


import net.minecraft.data.DataGenerator;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator generator, String locale) {
        super(generator, Constants.MOD_ID, locale);
    }

    private static String getTranslationName(final String type) {
        return Arrays.stream(StringUtils.split(type, '_')).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).collect(Collectors.joining(" "));
    }

    @Override
    protected void addTranslations() {
        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getType().toString()) + " Panels")
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getType().toString()) + " Panels Stairs")
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getType().toString()) + " Panels Slab")
        );
    }
}
