package yamahari.ilikewood.event.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TileEntityTypeRegistry {
    private TileEntityTypeRegistry() {
        // Nothing to do here!
    }

    @SubscribeEvent
    public static void onRegisterTypeEntityType(final RegistryEvent.Register<TileEntityType<?>> event) {
        final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
    }
}
