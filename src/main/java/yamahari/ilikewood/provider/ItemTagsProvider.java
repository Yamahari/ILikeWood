package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;

public final class ItemTagsProvider extends net.minecraft.data.ItemTagsProvider {
    public ItemTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerTags() {

    }

    @Override
    public String getName() {
        return "I Like Wood - Item Tags";
    }
}
