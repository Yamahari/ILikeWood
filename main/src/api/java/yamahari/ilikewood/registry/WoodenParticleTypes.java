package yamahari.ilikewood.registry;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;
import java.util.Map;

public final class WoodenParticleTypes
{
    public static final Map<DyeColor, RegistryObject<SimpleParticleType>> COLORED_CAMPFIRE_COSY_SMOKE = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, RegistryObject<SimpleParticleType>> COLORED_CAMPFIRE_SIGNAL_SMOKE = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, RegistryObject<SimpleParticleType>> COLORED_LAVA = new EnumMap<>(DyeColor.class);
}
