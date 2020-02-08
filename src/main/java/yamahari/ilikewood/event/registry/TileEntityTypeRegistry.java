package yamahari.ilikewood.event.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import yamahari.ilikewood.client.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.client.tileentity.WoodenChestTileEntity;
import yamahari.ilikewood.objectholder.WoodenTileEntityTypes;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.chest.WoodenChestBlocks;
import yamahari.ilikewood.objectholder.chest.WoodenChestTileEntityTypes;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodTypes;
import yamahari.ilikewood.util.WoodenObjectType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TileEntityTypeRegistry {
    private TileEntityTypeRegistry() {
    }

    @SubscribeEvent
    public static void onRegisterTypeEntityType(final RegistryEvent.Register<TileEntityType<?>> event) {
        final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        WoodTypes.get().forEach(
                woodType -> {
                    final String name = woodType.toString();
                    registry.register(TileEntityType.Builder
                            .create(() -> new WoodenChestTileEntity(woodType, Util.getTileEntityType(name.toUpperCase(), WoodenChestTileEntityTypes.class)), Util.getBlock(name.toUpperCase(), WoodenChestBlocks.class))
                            .build(null)
                            .setRegistryName(Constants.MOD_ID, Util.toRegistryName(name, WoodenObjectType.CHEST.toString())));
                }
        );

        registry.registerAll(
                TileEntityType.Builder.create(() -> new WoodenBarrelTileEntity(WoodenTileEntityTypes.BARREL), Util.getBlocks(WoodenBarrelBlocks.class).toArray(new Block[0])).build(null).setRegistryName("wooden_barrel")
        );
    }
}
