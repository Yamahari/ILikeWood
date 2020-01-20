package yamahari.ilikewood.event.registry;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RecipeSerializerRegistry {
    private RecipeSerializerRegistry() {
        // Nothing to do here!
    }

    @SubscribeEvent
    public static void onRegisterRecipeSerializer(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        final IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
    }
}
