package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.PANELS,
                WoodenBlockType.PANELS_STAIRS,
                WoodenBlockType.PANELS_SLAB,
                WoodenBlockType.BOOKSHELF,
                WoodenBlockType.COMPOSTER,
                WoodenBlockType.WALL,
                WoodenBlockType.LADDER,
                WoodenBlockType.TORCH,
                WoodenBlockType.SCAFFOLDING,
                WoodenBlockType.POST,
                WoodenBlockType.STRIPPED_POST,
                WoodenBlockType.SOUL_TORCH,
                WoodenBlockType.CHAIR,
                WoodenBlockType.TABLE))
            .forEach(block -> this.add(block,
                getTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(WoodenBlockType.getBeds())
            .forEach(block -> this.add(block,
                getTranslationName(Objects.requireNonNull(block.getRegistryName()).getPath())));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.CRAFTING_TABLE,
                WoodenBlockType.BARREL,
                WoodenBlockType.CHEST,
                WoodenBlockType.LECTERN,
                WoodenBlockType.SAWMILL))
            .forEach(block -> {
                final String path = Objects.requireNonNull(block.getRegistryName()).getPath();
                final String name = getTranslationName(path);
                this.add(block, name);
                this.add(StringUtils.joinWith(".", "container", Constants.MOD_ID, path), name);
            });

        ILikeWood.ITEM_REGISTRY
            .getObjects(Stream.of(WoodenItemType.STICK,
                WoodenItemType.BOW,
                WoodenItemType.CROSSBOW,
                WoodenItemType.ITEM_FRAME,
                WoodenItemType.FISHING_ROD))
            .forEach(item -> this.add(item,
                getTranslationName(Objects.requireNonNull(item.getRegistryName()).getPath())));

        WoodenTieredItemType
            .getAll()
            .flatMap(ILikeWood.TIERED_ITEM_REGISTRY::getObjects)
            .forEach(tieredItem -> this.add(tieredItem,
                getTranslationName(Objects.requireNonNull(tieredItem.getRegistryName()).getPath())));
    }
}
