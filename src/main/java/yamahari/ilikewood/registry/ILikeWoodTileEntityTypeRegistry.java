package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class ILikeWoodTileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);

    static {
        // TODO data fixer
        WoodenTileEntityTypes.WOODEN_BARREL = REGISTRY.register("wooden_barrel",
            () -> TileEntityType.Builder
                .create(() -> new WoodenBarrelTileEntity(WoodenTileEntityTypes.WOODEN_BARREL.get()),
                    WoodenBlocks.getBlocks(WoodenObjectTypes.BARREL).toArray(Block[]::new))
                .build(null));

        WoodenTileEntityTypes.WOODEN_CHEST = REGISTRY.register("wooden_chest",
            () -> TileEntityType.Builder
                .create(() -> new WoodenChestTileEntity(WoodenTileEntityTypes.WOODEN_CHEST.get()),
                    WoodenBlocks.getBlocks(WoodenObjectTypes.CHEST).toArray(Block[]::new))
                .build(null));

        WoodenTileEntityTypes.WOODEN_LECTERN = REGISTRY.register("wooden_lectern",
            () -> TileEntityType.Builder
                .create(WoodenLecternTileEntity::new,
                    WoodenBlocks.getBlocks(WoodenObjectTypes.LECTERN).toArray(Block[]::new))
                .build(null));
    }

    private ILikeWoodTileEntityTypeRegistry() {
    }
}
