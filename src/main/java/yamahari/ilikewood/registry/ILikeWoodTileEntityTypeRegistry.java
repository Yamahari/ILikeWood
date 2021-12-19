package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodTileEntityTypeRegistry {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Constants.MOD_ID);

    static {
        // TODO data fixer
        WoodenTileEntityTypes.WOODEN_BARREL = REGISTRY.register("wooden_barrel",
            () -> TileEntityType.Builder
                .of(() -> new WoodenBarrelTileEntity(WoodenTileEntityTypes.WOODEN_BARREL.get()),
                    ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.BARREL).toArray(Block[]::new))
                .build(null));

        WoodenTileEntityTypes.WOODEN_CHEST = REGISTRY.register("wooden_chest",
            () -> TileEntityType.Builder
                .of(() -> new WoodenChestTileEntity(WoodenTileEntityTypes.WOODEN_CHEST.get()),
                    ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CHEST).toArray(Block[]::new))
                .build(null));

        WoodenTileEntityTypes.WOODEN_LECTERN = REGISTRY.register("wooden_lectern",
            () -> TileEntityType.Builder
                .of(WoodenLecternTileEntity::new,
                    ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.LECTERN).toArray(Block[]::new))
                .build(null));
    }

    private ILikeWoodTileEntityTypeRegistry() {
    }
}
