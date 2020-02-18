package yamahari.ilikewood.provider;


import net.minecraft.data.DataGenerator;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenObjectType;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import java.util.Arrays;
import java.util.Objects;
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
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS, WoodenObjectType.STAIRS, WoodenObjectType.SLAB, WoodenObjectType.BOOKSHELF, WoodenObjectType.COMPOSTER, WoodenObjectType.WALL, WoodenObjectType.LADDER, WoodenObjectType.TORCH, WoodenObjectType.SCAFFOLDING)
                .forEach(block -> this.add(block, getTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));

        WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE, WoodenObjectType.BARREL, WoodenObjectType.CHEST, WoodenObjectType.LECTERN).forEach(block -> {
            final String path = Objects.requireNonNull(block.getRegistryName()).getPath();
            final String name = getTranslationName(path);
            this.add(block, name);
            this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, path), name);
        });

        WoodenItems.getItems(WoodenObjectType.STICK).forEach(item -> this.add(item, getTranslationName(Objects.requireNonNull(item.getRegistryName()).getPath())));
        WoodenItems.getTieredItems(WoodenTieredObjectType.values()).forEach(item -> this.add(item, getTranslationName(Objects.requireNonNull(item.getRegistryName()).getPath())));
    }
}
