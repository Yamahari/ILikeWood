package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import yamahari.ilikewood.data.tag.ItemTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {
    public ItemTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    private static void registerTag(final WoodenObjectType objectType, final Tag.Builder<Item> builder) {
        builder.add(WoodenItems.getItems(objectType).toArray(Item[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(WoodenObjectType.STICK, this.getBuilder(ItemTags.STICKS));
    }

    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
