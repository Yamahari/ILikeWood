package yamahari.ilikewood.event.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ContainerTypeRegistry {
    private ContainerTypeRegistry() {
        // Nothing to do here!
    }

    @SubscribeEvent
    public static void onRegisterContainerType(final RegistryEvent.Register<ContainerType<?>> event) {
        final IForgeRegistry<ContainerType<?>> registry = event.getRegistry();
    }
}
