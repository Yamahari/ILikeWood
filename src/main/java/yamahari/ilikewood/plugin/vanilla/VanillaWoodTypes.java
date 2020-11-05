package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.DummyWoodType;
import yamahari.ilikewood.util.WoodType;

import java.util.stream.Stream;

public final class VanillaWoodTypes {
    public static final IWoodType DUMMY = new DummyWoodType();
    public static final IWoodType ACACIA = new WoodType(Constants.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS));
    public static final IWoodType BIRCH = new WoodType(Constants.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS));
    public static final IWoodType CRIMSON = new WoodType(Constants.CRIMSON, Block.Properties.from(Blocks.CRIMSON_PLANKS));
    public static final IWoodType DARK_OAK = new WoodType(Constants.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS));
    public static final IWoodType JUNGLE = new WoodType(Constants.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS));
    public static final IWoodType OAK = new WoodType(Constants.OAK, Block.Properties.from(Blocks.OAK_PLANKS));
    public static final IWoodType SPRUCE = new WoodType(Constants.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS));
    public static final IWoodType WARPED = new WoodType(Constants.WARPED, Block.Properties.from(Blocks.WARPED_PLANKS));

    public static Stream<IWoodType> get() {
        return Stream.of(ACACIA, BIRCH, CRIMSON, DARK_OAK, JUNGLE, OAK, SPRUCE, WARPED);
    }

    private VanillaWoodTypes() {
    }
}
