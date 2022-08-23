package yamahari.ilikewood.registry;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodPaintingRegistry
{
    public static final DeferredRegister<PaintingVariant> REGISTRY = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Constants.MOD_ID);

    static
    {
        WoodenPaintingVariants.TAJ_MAHAL = REGISTRY.register("taj_mahal", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.COLOSSEUM = REGISTRY.register("colosseum", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.WHITE_HOUSE = REGISTRY.register("white_house", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.EMPIRE_STATE_BUILDING = REGISTRY.register("empire_state_building", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.BUCKINGHAM_PALACE = REGISTRY.register("buckingham_palace", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.STATUE_OF_LIBERTY = REGISTRY.register("statue_of_liberty", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.ACROPOLIS = REGISTRY.register("acropolis", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.HAGIA_SOPHIA = REGISTRY.register("hagia_sophia", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.NOTRE_DAME = REGISTRY.register("notre_dame", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.PANTHEON = REGISTRY.register("pantheon", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.BRANDENBURG_GATE = REGISTRY.register("brandenburg_gate", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.GOLDEN_GATE_BRIDGE = REGISTRY.register("golden_gate_bridge", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.PONTE_VECCHIO = REGISTRY.register("ponte_vecchio", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.TOWER_BRIDGE = REGISTRY.register("tower_bridge", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.MACHU_PICCHU = REGISTRY.register("machu_picchu", () -> new PaintingVariant(64, 64));
        WoodenPaintingVariants.COLOGNE_CATHEDRAL = REGISTRY.register("cologne_cathedral", () -> new PaintingVariant(64, 64));

        WoodenPaintingVariants.PIGS = REGISTRY.register("pigs", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.COW = REGISTRY.register("cow", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.CHICKEN = REGISTRY.register("chicken", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.SHEEP = REGISTRY.register("sheep", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.CAT = REGISTRY.register("cat", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.DOG = REGISTRY.register("dog", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.HORSE = REGISTRY.register("horse", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.FARM = REGISTRY.register("farm", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.DUCK_POND = REGISTRY.register("duck_pond", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.TRACTOR = REGISTRY.register("tractor", () -> new PaintingVariant(64, 48));
        WoodenPaintingVariants.SUNFLOWERS = REGISTRY.register("sunflowers", () -> new PaintingVariant(64, 48));

        WoodenPaintingVariants.WOODS = REGISTRY.register("woods", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.SNOWY_TAIGA = REGISTRY.register("snowy_taiga", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.SWAMP = REGISTRY.register("swamp", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.PALM_BEACH = REGISTRY.register("palm_beach", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.MANGROVE_SWAMP = REGISTRY.register("mangrove_swamp", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.SAVANNA = REGISTRY.register("savanna", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.DESERT = REGISTRY.register("desert", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.SNOWY_MOUNTAIN = REGISTRY.register("snowy_mountain", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.UNDERWATER = REGISTRY.register("underwater", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.CAVE = REGISTRY.register("cave", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.SUNNY_BEACH = REGISTRY.register("sunny_beach", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.ARCTIC = REGISTRY.register("arctic", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.JUNGLE = REGISTRY.register("jungle", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.WATERFALL = REGISTRY.register("waterfall", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.CANYON = REGISTRY.register("canyon", () -> new PaintingVariant(32, 32));
        WoodenPaintingVariants.MOON = REGISTRY.register("moon", () -> new PaintingVariant(32, 32));
    }
}
