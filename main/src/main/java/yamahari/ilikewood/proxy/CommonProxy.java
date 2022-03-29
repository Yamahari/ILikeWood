package yamahari.ilikewood.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.ILikeWood;

public class CommonProxy implements IProxy
{
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        ILikeWood.LOGGER.info("CommonProxy: FMLClientSetupEvent");
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
        ILikeWood.LOGGER.info("CommonProxy: FMLCommonSetupEvent");
    }
}
