package yamahari.ilikewood.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import yamahari.ilikewood.data.condition.ConfigCondition;
import yamahari.ilikewood.util.Constants;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT,
    Dist.DEDICATED_SERVER
}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class RegisterEventHandler
{
    @SubscribeEvent
    public static void onRegisterEvent(final RegisterEvent event)
    {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS))
        {
            CraftingHelper.register(ConfigCondition.ConfigConditionSerializer.INSTANCE);
        }
    }
}
