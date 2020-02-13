package yamahari.ilikewood.data.tag;

import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.util.Constants;

public final class ItemTags {
    public static final Tag<Item> STICKS = makeWrapperTag("sticks");

    private ItemTags() {
    }

    private static Tag<Item> makeWrapperTag(final String id) {
        return new net.minecraft.tags.ItemTags.Wrapper(new ResourceLocation(Constants.MOD_ID, id));
    }
}
