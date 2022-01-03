package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodBlockTags {
    public static final Tags.IOptionalNamedTag<Block> PANELS = createOptional("panels");
    public static final Tags.IOptionalNamedTag<Block> PANELS_SLABS = createOptional("panels_slab");
    public static final Tags.IOptionalNamedTag<Block> PANELS_STAIRS = createOptional("panels_stairs");
    public static final Tags.IOptionalNamedTag<Block> BARRELS = createOptional("barrels");
    public static final Tags.IOptionalNamedTag<Block> CHESTS = createOptional("chests");
    public static final Tags.IOptionalNamedTag<Block> COMPOSTER = createOptional("composter");
    public static final Tags.IOptionalNamedTag<Block> BOOKSHELFS = createOptional("bookshelfs");
    public static final Tags.IOptionalNamedTag<Block> WALLS = createOptional("walls");
    public static final Tags.IOptionalNamedTag<Block> LADDERS = createOptional("ladders");
    public static final Tags.IOptionalNamedTag<Block> TORCHES = createOptional("torches");
    public static final Tags.IOptionalNamedTag<Block> WALL_TORCHES = createOptional("wall_torches");
    public static final Tags.IOptionalNamedTag<Block> CRAFTING_TABLES = createOptional("crafting_tables");
    public static final Tags.IOptionalNamedTag<Block> SCAFFOLDINGS = createOptional("scaffoldings");
    public static final Tags.IOptionalNamedTag<Block> LECTERNS = createOptional("lecterns");
    public static final Tags.IOptionalNamedTag<Block> POSTS = createOptional("posts");
    public static final Tags.IOptionalNamedTag<Block> STRIPPED_POSTS = createOptional("stripped_posts");
    public static final Tags.IOptionalNamedTag<Block> BEDS = createOptional("beds");
    public static final Tags.IOptionalNamedTag<Block> SAWMILLS = createOptional("sawmills");
    public static final Tags.IOptionalNamedTag<Block> SOUL_TORCHES = createOptional("soul_torches");
    public static final Tags.IOptionalNamedTag<Block> WALL_SOUL_TORCHES = createOptional("wall_soul_torches");
    public static final Tags.IOptionalNamedTag<Block> CHAIRS = createOptional("chairs");
    public static final Tags.IOptionalNamedTag<Block> TABLES = createOptional("tables");

    private ILikeWoodBlockTags() {
    }

    private static Tags.IOptionalNamedTag<Block> createOptional(final String id) {
        return BlockTags.createOptional(new ResourceLocation(Constants.MOD_ID, id));
    }
}
