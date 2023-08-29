package yamahari.ilikewood.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodParticleTypeRegistry
{
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Constants.MOD_ID);

    static
    {
        if (ILikeWoodConfig.CAMPFIRE_CONFIG.isEnabled())
        {
            for (final DyeColor dyeColor : DyeColor.values())
            {
                WoodenParticleTypes.COLORED_CAMPFIRE_COSY_SMOKE.put(
                    dyeColor, REGISTRY.register(String.format("%s_campfire_cosy_smoke", dyeColor.getName()), () -> new SimpleParticleType(false)));
                WoodenParticleTypes.COLORED_CAMPFIRE_SIGNAL_SMOKE.put(
                    dyeColor, REGISTRY.register(String.format("%s_campfire_signal_smoke", dyeColor.getName()), () -> new SimpleParticleType(false)));
                WoodenParticleTypes.COLORED_LAVA.put(dyeColor, REGISTRY.register(String.format("%s_lava", dyeColor.getName()), () -> new SimpleParticleType(false)));
            }
        }
    }
}
