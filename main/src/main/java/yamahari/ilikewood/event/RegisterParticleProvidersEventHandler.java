package yamahari.ilikewood.event;

import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.registry.WoodenParticleTypes;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterParticleProvidersEventHandler
{
    @SubscribeEvent
    public static void onRegisterParticleProviders(
        final RegisterParticleProvidersEvent event
    )
    {
        if (ILikeWoodConfig.CAMPFIRE_CONFIG.isEnabled())
        {
            for (final DyeColor dyeColor : DyeColor.values())
            {
                event.register(WoodenParticleTypes.COLORED_CAMPFIRE_COSY_SMOKE.get(dyeColor).get(), CampfireSmokeParticle.CosyProvider::new);
                event.register(WoodenParticleTypes.COLORED_CAMPFIRE_SIGNAL_SMOKE.get(dyeColor).get(), CampfireSmokeParticle.SignalProvider::new);
                event.register(WoodenParticleTypes.COLORED_LAVA.get(dyeColor).get(), LavaParticle.Provider::new);
            }
        }
    }
}
