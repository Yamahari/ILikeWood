package yamahari.ilikewood.data.tag;

import net.minecraft.block.Block;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.util.Constants;

public final class BlockTags {
    public static final Tag<Block> PANELS = makeWrapperTag("panels");
    public static final Tag<Block> PANELS_SLABS = makeWrapperTag("panels_slab");
    public static final Tag<Block> PANELS_STAIRS = makeWrapperTag("panels_stairs");
    public static final Tag<Block> BARRELS = makeWrapperTag("barrels");
    public static final Tag<Block> CHESTS = makeWrapperTag("chests");
    public static final Tag<Block> COMPOSTER = makeWrapperTag("composter");
    public static final Tag<Block> BOOKSHELFS = makeWrapperTag("bookshelfs");
    public static final Tag<Block> WALLS = makeWrapperTag("walls");
    public static final Tag<Block> LADDERS = makeWrapperTag("ladders");
    public static final Tag<Block> TORCHES = makeWrapperTag("torches");
    public static final Tag<Block> WALL_TORCHES = makeWrapperTag("wall_torches");
    public static final Tag<Block> CRAFTING_TABLES = makeWrapperTag("crafting_tables");

    private BlockTags() {
    }

    private static Tag<Block> makeWrapperTag(final String id) {
        return new net.minecraft.tags.BlockTags.Wrapper(new ResourceLocation(Constants.MOD_ID, id));
    }
}
