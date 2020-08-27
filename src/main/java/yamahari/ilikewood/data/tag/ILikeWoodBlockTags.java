package yamahari.ilikewood.data.tag;

import net.minecraft.block.Block;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollectionSupplier;
import net.minecraft.tags.TagRegistry;
import net.minecraft.tags.TagRegistryManager;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodBlockTags {
    public static final TagRegistry<Block> COLLECTION = TagRegistryManager.func_242196_a(new ResourceLocation(Constants.MOD_ID, "block"), ITagCollectionSupplier::func_241835_a);
    public static final INamedTag<Block> PANELS = makeWrapperTag("panels");
    public static final INamedTag<Block> PANELS_SLABS = makeWrapperTag("panels_slab");
    public static final INamedTag<Block> PANELS_STAIRS = makeWrapperTag("panels_stairs");
    public static final INamedTag<Block> BARRELS = makeWrapperTag("barrels");
    public static final INamedTag<Block> CHESTS = makeWrapperTag("chests");
    public static final INamedTag<Block> COMPOSTER = makeWrapperTag("composter");
    public static final INamedTag<Block> BOOKSHELFS = makeWrapperTag("bookshelfs");
    public static final INamedTag<Block> WALLS = makeWrapperTag("walls");
    public static final INamedTag<Block> LADDERS = makeWrapperTag("ladders");
    public static final INamedTag<Block> TORCHES = makeWrapperTag("torches");
    public static final INamedTag<Block> WALL_TORCHES = makeWrapperTag("wall_torches");
    public static final INamedTag<Block> CRAFTING_TABLES = makeWrapperTag("crafting_tables");
    public static final INamedTag<Block> SCAFFOLDINGS = makeWrapperTag("scaffoldings");
    public static final INamedTag<Block> LECTERNS = makeWrapperTag("lecterns");
    public static final INamedTag<Block> POSTS = makeWrapperTag("posts");
    public static final INamedTag<Block> STRIPPED_POSTS = makeWrapperTag("stripped_posts");

    private ILikeWoodBlockTags() {
    }

    private static INamedTag<Block> makeWrapperTag(final String id) {
        return COLLECTION.createOptional(new ResourceLocation(Constants.MOD_ID, id), null);
    }
}
