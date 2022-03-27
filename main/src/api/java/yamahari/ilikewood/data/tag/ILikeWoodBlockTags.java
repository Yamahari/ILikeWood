package yamahari.ilikewood.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodBlockTags {
    public static final Tags.IOptionalNamedTag<Block> PANELS = createOptional(Constants.PANELS_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> PANELS_SLABS = createOptional(Constants.PANELS_SLAB_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> PANELS_STAIRS = createOptional(Constants.PANELS_STAIRS_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> BARRELS = createOptional(Constants.BARREL_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> CHESTS = createOptional(Constants.CHEST_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> COMPOSTER = createOptional(Constants.COMPOSTER_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> BOOKSHELVES = createOptional(Constants.BOOKSHELF_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> WALLS = createOptional(Constants.WALL_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> LADDERS = createOptional(Constants.LADDER_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> TORCHES = createOptional(Constants.TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> WALL_TORCHES = createOptional(Constants.WALL_TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> CRAFTING_TABLES = createOptional(Constants.CRAFTING_TABLE_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> SCAFFOLDINGS = createOptional(Constants.SCAFFOLDING_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> LECTERNS = createOptional(Constants.LECTERN_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> POSTS = createOptional(Constants.POST_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> STRIPPED_POSTS = createOptional(Constants.STRIPPED_POST_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> BEDS = createOptional(Constants.BEDS);
    public static final Tags.IOptionalNamedTag<Block> SAWMILLS = createOptional(Constants.SAWMILL_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> SOUL_TORCHES = createOptional(Constants.SOUL_TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> WALL_SOUL_TORCHES =
        createOptional(Constants.WALL_SOUL_TORCH_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> CHAIRS = createOptional(Constants.CHAIR_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> TABLES = createOptional(Constants.TABLE_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> STOOLS = createOptional(Constants.STOOL_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> SINGLE_DRESSERS = createOptional(Constants.SINGLE_DRESSER_PLURAL);
    public static final Tags.IOptionalNamedTag<Block> LOG_PILES = createOptional(Constants.LOG_PILE_PLURAL);

    private ILikeWoodBlockTags() {
    }

    private static Tags.IOptionalNamedTag<Block> createOptional(final String id) {
        return BlockTags.createOptional(new ResourceLocation(Constants.MOD_ID, id));
    }
}
