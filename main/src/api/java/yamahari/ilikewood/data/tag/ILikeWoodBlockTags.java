package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodBlockTags
{
    public static final TagKey<Block> PANELS = create(Constants.PANELS_PLURAL);
    public static final TagKey<Block> PANELS_SLABS = create(Constants.PANELS_SLAB_PLURAL);
    public static final TagKey<Block> PANELS_STAIRS = create(Constants.PANELS_STAIRS_PLURAL);
    public static final TagKey<Block> BARRELS = create(Constants.BARREL_PLURAL);
    public static final TagKey<Block> CHESTS = create(Constants.CHEST_PLURAL);
    public static final TagKey<Block> COMPOSTER = create(Constants.COMPOSTER_PLURAL);
    public static final TagKey<Block> BOOKSHELVES = create(Constants.BOOKSHELF_PLURAL);
    public static final TagKey<Block> WALLS = create(Constants.WALL_PLURAL);
    public static final TagKey<Block> LADDERS = create(Constants.LADDER_PLURAL);
    public static final TagKey<Block> TORCHES = create(Constants.TORCH_PLURAL);
    public static final TagKey<Block> WALL_TORCHES = create(Constants.WALL_TORCH_PLURAL);
    public static final TagKey<Block> CRAFTING_TABLES = create(Constants.CRAFTING_TABLE_PLURAL);
    public static final TagKey<Block> SCAFFOLDINGS = create(Constants.SCAFFOLDING_PLURAL);
    public static final TagKey<Block> LECTERNS = create(Constants.LECTERN_PLURAL);
    public static final TagKey<Block> POSTS = create(Constants.POST_PLURAL);
    public static final TagKey<Block> STRIPPED_POSTS = create(Constants.STRIPPED_POST_PLURAL);
    public static final TagKey<Block> BEDS = create(Constants.BEDS);
    public static final TagKey<Block> SAWMILLS = create(Constants.SAWMILL_PLURAL);
    public static final TagKey<Block> SOUL_TORCHES = create(Constants.SOUL_TORCH_PLURAL);
    public static final TagKey<Block> WALL_SOUL_TORCHES = create(Constants.WALL_SOUL_TORCH_PLURAL);
    public static final TagKey<Block> CHAIRS = create(Constants.CHAIR_PLURAL);
    public static final TagKey<Block> TABLES = create(Constants.TABLE_PLURAL);
    public static final TagKey<Block> STOOLS = create(Constants.STOOL_PLURAL);
    public static final TagKey<Block> SINGLE_DRESSERS = create(Constants.SINGLE_DRESSER_PLURAL);
    public static final TagKey<Block> LOG_PILES = create(Constants.LOG_PILE_PLURAL);
    public static final TagKey<Block> CAMPFIRES = create(Constants.CAMPFIRE_PLURAL);
    public static final TagKey<Block> SOUL_CAMPFIRES = create(Constants.SOUL_CAMPFIRE_PLURAL);

    private ILikeWoodBlockTags()
    {
    }

    private static TagKey<Block> create(final String id)
    {
        return BlockTags.create(new ResourceLocation(Constants.MOD_ID, id));
    }
}
