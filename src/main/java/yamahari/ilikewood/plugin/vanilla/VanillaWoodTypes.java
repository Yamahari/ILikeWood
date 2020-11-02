package yamahari.ilikewood.plugin.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.Constants;

public final class VanillaWoodTypes {
    public static final IWoodType ACACIA = new VanillaWoodType(Constants.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS));
    public static final IWoodType BIRCH = new VanillaWoodType(Constants.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS));
    public static final IWoodType CRIMSON = new VanillaWoodType(Constants.CRIMSON, Block.Properties.from(Blocks.CRIMSON_PLANKS));
    public static final IWoodType DARK_OAK = new VanillaWoodType(Constants.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS));
    public static final IWoodType JUNGLE = new VanillaWoodType(Constants.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS));
    public static final IWoodType OAK = new VanillaWoodType(Constants.OAK, Block.Properties.from(Blocks.OAK_PLANKS));
    public static final IWoodType SPRUCE = new VanillaWoodType(Constants.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS));
    public static final IWoodType WARPED = new VanillaWoodType(Constants.WARPED, Block.Properties.from(Blocks.WARPED_PLANKS));

    private VanillaWoodTypes() {
    }
}
