package yamahari.ilikewood.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientProxy implements IProxy {
    private static final Logger logger = LogManager.getLogger(ClientProxy.class);

    @Override
    public void onFMLClientSetup(FMLClientSetupEvent event) {
        logger.info("client setup");
    }

    @Override
    public void onFMLCommonSetup(FMLCommonSetupEvent event) {
        logger.info("common setup");
    }
}
