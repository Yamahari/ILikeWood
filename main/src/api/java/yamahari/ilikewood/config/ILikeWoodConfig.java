package yamahari.ilikewood.config;

import com.google.common.base.Suppliers;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.util.Constants;

import java.util.function.Supplier;
import java.util.stream.Stream;

public record ILikeWoodConfig(String name, Supplier<Boolean> flag) {
    public static final ILikeWoodConfig PANELS_CONFIG = make(Constants.PANELS_PLURAL);
    public static final ILikeWoodConfig PANELS_STAIRS_CONFIG = make(Constants.PANELS_STAIRS_PLURAL);
    public static final ILikeWoodConfig PANELS_SLABS_CONFIG = make(Constants.PANELS_SLAB_PLURAL);
    public static final ILikeWoodConfig BARRELS_CONFIG = make(Constants.BARREL_PLURAL);
    public static final ILikeWoodConfig BEDS_CONFIG = make(Constants.BEDS);
    public static final ILikeWoodConfig BOOKSHELVES_CONFIG = make(Constants.BOOKSHELF_PLURAL);
    public static final ILikeWoodConfig COMPOSTERS_CONFIG = make(Constants.COMPOSTER_PLURAL);
    public static final ILikeWoodConfig CRAFTING_TABLES_CONFIG = make(Constants.CRAFTING_TABLE_PLURAL);
    public static final ILikeWoodConfig CHESTS_CONFIG = make(Constants.CHEST_PLURAL);
    public static final ILikeWoodConfig SAWMILLS_CONFIG = make(Constants.SAWMILL_PLURAL);
    public static final ILikeWoodConfig LECTERNS_CONFIG = make(Constants.LECTERN_PLURAL);
    public static final ILikeWoodConfig LADDERS_CONFIG = make(Constants.LADDER_PLURAL);
    public static final ILikeWoodConfig SCAFFOLDINGS_CONFIG = make(Constants.SCAFFOLDING_PLURAL);
    public static final ILikeWoodConfig TORCHES_CONFIG = make(Constants.TORCH_PLURAL);
    public static final ILikeWoodConfig LOG_PILE_CONFIG = make(Constants.LOG_PILE_PLURAL);
    public static final ILikeWoodConfig POSTS_CONFIG = make(Constants.POST_PLURAL);
    public static final ILikeWoodConfig WALLS_CONFIG = make(Constants.WALL_PLURAL);
    public static final ILikeWoodConfig CHAIRS_CONFIG = make(Constants.CHAIR_PLURAL);
    public static final ILikeWoodConfig TABLES_CONFIG = make(Constants.TABLE_PLURAL);
    public static final ILikeWoodConfig STOOLS_CONFIG = make(Constants.STOOL_PLURAL);
    public static final ILikeWoodConfig SINGLE_DRESSERS_CONFIG = make(Constants.SINGLE_DRESSER_PLURAL);
    public static final ILikeWoodConfig STICKS_CONFIG = make(Constants.STICK_PLURAL);
    public static final ILikeWoodConfig BOWS_CONFIG = make(Constants.BOW_PLURAL);
    public static final ILikeWoodConfig CROSSBOWS_CONFIG = make(Constants.CROSSBOW_PLURAL);
    public static final ILikeWoodConfig FISHING_RODS_CONFIG = make(Constants.FISHING_ROD_PLURAL);
    public static final ILikeWoodConfig ITEM_FRAMES_CONFIG = make(Constants.ITEM_FRAME_PLURAL);
    public static final ILikeWoodConfig AXES_CONFIG = make(Constants.AXE_PLURAL);
    public static final ILikeWoodConfig HOES_CONFIG = make(Constants.HOE_PLURAL);
    public static final ILikeWoodConfig PICKAXES_CONFIG = make(Constants.PICKAXE_PLURAL);
    public static final ILikeWoodConfig SHOVELS_CONFIG = make(Constants.SHOVEL_PLURAL);
    public static final ILikeWoodConfig SWORDS_CONFIG = make(Constants.SWORD_PLURAL);

    public static Stream<ILikeWoodConfig> getAll() {
        return Stream.of(
            PANELS_CONFIG,
            PANELS_STAIRS_CONFIG,
            PANELS_SLABS_CONFIG,
            BARRELS_CONFIG,
            BEDS_CONFIG,
            BOOKSHELVES_CONFIG,
            COMPOSTERS_CONFIG,
            CRAFTING_TABLES_CONFIG,
            CHESTS_CONFIG,
            SAWMILLS_CONFIG,
            LECTERNS_CONFIG,
            LADDERS_CONFIG,
            SCAFFOLDINGS_CONFIG,
            TORCHES_CONFIG,
            LOG_PILE_CONFIG,
            POSTS_CONFIG,
            WALLS_CONFIG,
            CHAIRS_CONFIG,
            TABLES_CONFIG,
            STOOLS_CONFIG,
            SINGLE_DRESSERS_CONFIG,
            STICKS_CONFIG,
            BOWS_CONFIG,
            CROSSBOWS_CONFIG,
            FISHING_RODS_CONFIG,
            ITEM_FRAMES_CONFIG,
            AXES_CONFIG,
            HOES_CONFIG,
            PICKAXES_CONFIG,
            SHOVELS_CONFIG,
            SWORDS_CONFIG
        );
    }

    private static ILikeWoodConfig make(final String name) {
        return new ILikeWoodConfig(name, Suppliers.memoize(() -> ModList.get()
            .isLoaded(String.format("%s_%s", Constants.MOD_ID, name)) || DatagenModLoader.isRunningDataGen()));
    }

    public boolean isEnabled() {
        return flag.get();
    }
}