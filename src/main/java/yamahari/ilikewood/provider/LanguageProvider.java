package yamahari.ilikewood.provider;


import net.minecraft.data.DataGenerator;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.chest.WoodenChestBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {
    public LanguageProvider(DataGenerator generator, String locale) {
        super(generator, Constants.MOD_ID, locale);
    }

    private static String getTranslationName(final String type) {
        return Arrays.stream(StringUtils.split(type, '_')).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    @Override
    protected void addTranslations() {
        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels")
        );

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels Stairs")
        );

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Panels Slab")
        );

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String name = getTranslationName(type) + " Barrel";
                    this.add(block, name);
                    this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, type + "_" + WoodenObjectType.BARREL.toString()), name);
                }
        );

        Util.getBlocks(WoodenBookshelfBlocks.class).forEach(
                block -> this.add(block, getTranslationName(((IWooden) block).getWoodType().toString()) + " Bookshelf")
        );

        Util.getBlocks(WoodenChestBlocks.class).forEach(
                block -> {
                    final String type = ((IWooden) block).getWoodType().toString();
                    final String name = getTranslationName(type) + " Chest";
                    this.add(block, name);
                    this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, type + "_" + WoodenObjectType.CHEST.toString()), name);
                }
        );
    }
}
