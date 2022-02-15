package yamahari.ilikewood.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {
    void onFMLClientSetup(final FMLClientSetupEvent event);

    void onFMLCommonSetup(final FMLCommonSetupEvent event);
}
