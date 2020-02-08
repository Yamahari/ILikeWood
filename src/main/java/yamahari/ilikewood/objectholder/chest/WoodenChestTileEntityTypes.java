package yamahari.ilikewood.objectholder.chest;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.util.Constants;

@ObjectHolder(Constants.MOD_ID)
public final class WoodenChestTileEntityTypes {
    @ObjectHolder("acacia_chest")
    public static final TileEntityType<WoodenChestTileEntity> ACACIA = null;
    @ObjectHolder("birch_chest")
    public static final TileEntityType<WoodenChestTileEntity> BIRCH = null;
    @ObjectHolder("dark_oak_chest")
    public static final TileEntityType<WoodenChestTileEntity> DARK_OAK = null;
    @ObjectHolder("jungle_chest")
    public static final TileEntityType<WoodenChestTileEntity> JUNGLE = null;
    @ObjectHolder("oak_chest")
    public static final TileEntityType<WoodenChestTileEntity> OAK = null;
    @ObjectHolder("spruce_chest")
    public static final TileEntityType<WoodenChestTileEntity> SPRUCE = null;

    private WoodenChestTileEntityTypes() {
    }
}
