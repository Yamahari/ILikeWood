package yamahari.ilikewood.client.tileentity;

import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.tileentity.TileEntityType;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;

public final class WoodenLecternTileEntity extends LecternTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return WoodenTileEntityTypes.WOODEN_LECTERN.get();
    }
}
