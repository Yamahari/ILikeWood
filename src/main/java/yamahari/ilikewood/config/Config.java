package yamahari.ilikewood.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class Config {
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;

    public static final ServerConfig SERVER_CONFIG;
    public static final CommonConfig COMMON_CONFIG;
    public static final ClientConfig CLIENT_CONFIG;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> server = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        final Pair<CommonConfig, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        final Pair<ClientConfig, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(ClientConfig::new);

        SERVER_SPEC = server.getRight();
        SERVER_CONFIG = server.getLeft();

        COMMON_SPEC = common.getRight();
        COMMON_CONFIG = common.getLeft();

        CLIENT_SPEC = client.getRight();
        CLIENT_CONFIG = client.getLeft();
    }

    private Config() {
        // Nothing to do here!
    }
}
