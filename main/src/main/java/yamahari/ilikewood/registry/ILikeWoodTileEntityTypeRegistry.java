package yamahari.ilikewood.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenLecternTileEntity;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodTileEntityTypeRegistry
{
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MOD_ID);

    static
    {
        if (ILikeWoodConfig.BARRELS_CONFIG.isEnabled())
        {
            WoodenTileEntityTypes.WOODEN_BARREL = REGISTRY.register(
                "wooden_barrel", () -> BlockEntityType.Builder
                    .of(WoodenBarrelTileEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.BARREL).toArray(Block[]::new))
                    .build(null));
        }

        if (ILikeWoodConfig.CHESTS_CONFIG.isEnabled())
        {
            WoodenTileEntityTypes.WOODEN_CHEST = REGISTRY.register(
                "wooden_chest",
                () -> BlockEntityType.Builder.of(WoodenChestTileEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CHEST).toArray(Block[]::new)).build(null)
            );
        }

        if (ILikeWoodConfig.LECTERNS_CONFIG.isEnabled())
        {
            WoodenTileEntityTypes.WOODEN_LECTERN = REGISTRY.register(
                "wooden_lectern", () -> BlockEntityType.Builder
                    .of(WoodenLecternTileEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.LECTERN).toArray(Block[]::new))
                    .build(null));
        }
    }

    private ILikeWoodTileEntityTypeRegistry()
    {
    }
}