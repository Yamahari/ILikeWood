package yamahari.ilikewood.objectholder;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.util.Constants;

@ObjectHolder(Constants.MOD_ID)
public final class WoodenTileEntityTypes {
    @ObjectHolder("wooden_barrel")
    public static final TileEntityType<WoodenBarrelTileEntity> BARREL = null;

    private WoodenTileEntityTypes() {
    }
}
