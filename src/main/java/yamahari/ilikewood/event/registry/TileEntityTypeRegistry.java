package yamahari.ilikewood.event.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.tileentity.WoodenBarrelTileEntity;
import yamahari.ilikewood.util.Util;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TileEntityTypeRegistry {
    private TileEntityTypeRegistry() {
        // Nothing to do here!
    }

    @SubscribeEvent
    public static void onRegisterTypeEntityType(final RegistryEvent.Register<TileEntityType<?>> event) {
        final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

        final List<Block> barrels = Util.getBlocks(WoodenBarrelBlocks.class);
        registry.registerAll(
                TileEntityType.Builder.create(WoodenBarrelTileEntity::new, barrels.toArray(new Block[0])).build(null).setRegistryName("wooden_barrel")
        );
    }
}
