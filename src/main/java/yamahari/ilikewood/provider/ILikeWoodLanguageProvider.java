package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ILikeWoodLanguageProvider extends LanguageProvider {
    public ILikeWoodLanguageProvider(final DataGenerator generator, final String locale) {
        super(generator, Constants.MOD_ID, locale);
    }

    private static String getTranslationName(final String type) {
        return Arrays
            .stream(StringUtils.split(type, '_'))
            .map(StringUtils::capitalize)
            .collect(Collectors.joining(" "));
    }

    @Override
    protected void addTranslations() {
        WoodenBlocks
            .getBlocks(WoodenObjectTypes.PANELS,
                WoodenObjectTypes.STAIRS,
                WoodenObjectTypes.SLAB,
                WoodenObjectTypes.BOOKSHELF,
                WoodenObjectTypes.COMPOSTER,
                WoodenObjectTypes.WALL,
                WoodenObjectTypes.LADDER,
                WoodenObjectTypes.TORCH,
                WoodenObjectTypes.SCAFFOLDING,
                WoodenObjectTypes.POST,
                WoodenObjectTypes.STRIPPED_POST,
                WoodenObjectTypes.SOUL_TORCH)
            .forEach(block -> this.add(block,
                getTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));

        WoodenBlocks
            .getBedBlocks()
            .forEach(block -> this.add(block,
                getTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));

        WoodenBlocks
            .getBlocks(WoodenObjectTypes.CRAFTING_TABLE,
                WoodenObjectTypes.BARREL,
                WoodenObjectTypes.CHEST,
                WoodenObjectTypes.LECTERN,
                WoodenObjectTypes.SAWMILL)
            .forEach(block -> {
                final String path = Objects.requireNonNull(block.getRegistryName()).getPath();
                final String name = getTranslationName(path);
                this.add(block, name);
                this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, path), name);
            });

        WoodenItems
            .getItems(WoodenObjectTypes.STICK,
                WoodenObjectTypes.BOW,
                WoodenObjectTypes.CROSSBOW,
                WoodenObjectTypes.ITEM_FRAME,
                WoodenObjectTypes.FISHING_ROD)
            .forEach(item -> this.add(item,
                getTranslationName(Objects.requireNonNull(item.getRegistryName()).getPath())));

        WoodenTieredObjectTypes
            .get()
            .flatMap(WoodenItems::getTieredItems)
            .forEach(item -> this.add(item,
                getTranslationName(Objects.requireNonNull(item.getRegistryName()).getPath())));
    }
}
