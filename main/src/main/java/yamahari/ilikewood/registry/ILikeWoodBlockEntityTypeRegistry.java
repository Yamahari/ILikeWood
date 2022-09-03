package yamahari.ilikewood.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.blockentity.WoodenBarrelBlockEntity;
import yamahari.ilikewood.client.blockentity.WoodenCampfireBlockEntity;
import yamahari.ilikewood.client.blockentity.WoodenChestBlockEntity;
import yamahari.ilikewood.client.blockentity.WoodenCrateBlockEntity;
import yamahari.ilikewood.client.blockentity.WoodenLecternBlockEntity;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import java.util.stream.Stream;

public final class ILikeWoodBlockEntityTypeRegistry
{
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MOD_ID);

    static
    {
        if (ILikeWoodConfig.BARRELS_CONFIG.isEnabled())
        {
            WoodenBlockEntityTypes.WOODEN_BARREL = REGISTRY.register(
                "wooden_barrel", () -> BlockEntityType.Builder
                    .of(WoodenBarrelBlockEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.BARREL).toArray(Block[]::new))
                    .build(null));
        }

        if (ILikeWoodConfig.CHESTS_CONFIG.isEnabled())
        {
            WoodenBlockEntityTypes.WOODEN_CHEST = REGISTRY.register(
                "wooden_chest", () -> BlockEntityType.Builder
                    .of(WoodenChestBlockEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CHEST).toArray(Block[]::new))
                    .build(null));
        }

        if (ILikeWoodConfig.LECTERNS_CONFIG.isEnabled())
        {
            WoodenBlockEntityTypes.WOODEN_LECTERN = REGISTRY.register(
                "wooden_lectern", () -> BlockEntityType.Builder
                    .of(WoodenLecternBlockEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.LECTERN).toArray(Block[]::new))
                    .build(null));
        }

        if (ILikeWoodConfig.CAMPFIRE_CONFIG.isEnabled())
        {
            WoodenBlockEntityTypes.WOODEN_CAMPFIRE = REGISTRY.register("wooden_campfire", () -> BlockEntityType.Builder.of(
                WoodenCampfireBlockEntity::new,
                ILikeWood.BLOCK_REGISTRY.getObjects(Stream.of(WoodenBlockType.CAMPFIRE, WoodenBlockType.SOUL_CAMPFIRE)).toArray(Block[]::new)
            ).build(null));
        }

        if (ILikeWoodConfig.CRATE_CONFIG.isEnabled())
        {
            WoodenBlockEntityTypes.WOODEN_CRATE = REGISTRY.register(
                "wooden_crate", () -> BlockEntityType.Builder
                    .of(WoodenCrateBlockEntity::new, ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CRATE).toArray(Block[]::new))
                    .build(null));
        }
    }

    private ILikeWoodBlockEntityTypeRegistry()
    {
    }
}