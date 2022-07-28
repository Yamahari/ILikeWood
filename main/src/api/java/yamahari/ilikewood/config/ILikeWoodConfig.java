package yamahari.ilikewood.config;

import com.google.common.base.Suppliers;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.data.loading.DatagenModLoader;
import org.apache.commons.lang3.tuple.Pair;
import yamahari.ilikewood.util.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ILikeWoodConfig(String name, Supplier<Boolean> flag)
{
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

    public static final Map<String, ILikeWoodConfig> NAME_TO_CONFIG =
        getAll().collect(Collectors.toUnmodifiableMap(ILikeWoodConfig::name,
        Function.identity()
    ));

    public static final ForgeConfigSpec COMMON_SPEC;
    private static final CommonConfig COMMON_CONFIG;

    static
    {
        final Pair<CommonConfig, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(CommonConfig::new);

        COMMON_SPEC = common.getRight();
        COMMON_CONFIG = common.getLeft();
    }

    public static Stream<ILikeWoodConfig> getAll() {
        return Stream.of(PANELS_CONFIG,
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
        return new ILikeWoodConfig(name,
            Suppliers.memoize(() -> COMMON_CONFIG.isEnabled(name) || DatagenModLoader.isRunningDataGen())
        );
    }

    public static class CommonConfig
    {
        private final Map<String, ForgeConfigSpec.BooleanValue> values = new HashMap<>();

        public CommonConfig(final ForgeConfigSpec.Builder builder) {
            ILikeWoodConfig.getAll().forEach(config -> values.put(config.name(),
                builder.define(String.format("ilikewood.enable.%s", config.name()), true)
            ));
        }

        public boolean isEnabled(final String name) {
            if (!values.containsKey(name))
            {
                throw new IllegalArgumentException(String.format("Missing option for \"%s\" in common config file.",
                    name
                ));
            }
            return values.get(name).get();
        }
    }

    public boolean isEnabled() {
        return flag.get();
    }
}