package yamahari.ilikewood.provider;


import net.minecraft.data.DataGenerator;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(final DataGenerator generator, final String locale) {
        super(generator, Constants.MOD_ID, locale);
    }

    private static String getTranslationName(final String type) {
        return Arrays.stream(StringUtils.split(type, '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    @Override
    protected void addTranslations() {
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels"));
        WoodenBlocks.getBlocks(WoodenObjectType.STAIRS).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels Stairs"));
        WoodenBlocks.getBlocks(WoodenObjectType.SLAB).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels Slab"));
        WoodenBlocks.getBlocks(WoodenObjectType.BARREL).forEach(block -> {
            final String type = ((IWooden) block).getWoodType().toString();
            final String name = getTranslationName(type) + " Barrel";
            this.add(block, name);
            this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, type + "_" + WoodenObjectType.BARREL.toString()), name);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.BOOKSHELF).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Bookshelf"));
        WoodenBlocks.getBlocks(WoodenObjectType.CHEST).forEach(block -> {
            final String type = ((IWooden) block).getWoodType().toString();
            final String name = getTranslationName(type) + " Chest";
            this.add(block, name);
            this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, type + "_" + WoodenObjectType.CHEST.toString()), name);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Composter"));
        WoodenBlocks.getBlocks(WoodenObjectType.WALL).forEach(block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Wall"));
        WoodenItems.getItems(WoodenObjectType.STICK).forEach(item -> this.add(item, getTranslationName(((IWooden) item).getWoodType().toString()) + " Stick"));
    }
}
