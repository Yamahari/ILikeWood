package yamahari.ilikewood.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.container.WoodenSawmillContainer;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodContainerRegistry {
    public static final DeferredRegister<MenuType<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    static {
        WoodenContainerTypes.WOODEN_SAWMILL =
            REGISTRY.register("wooden_sawmill", () -> new MenuType<>(WoodenSawmillContainer::new));
        WoodenContainerTypes.WOODEN_WORK_BENCH =
            REGISTRY.register("wooden_workbench", () -> new MenuType<>(WoodenWorkBenchContainer::new));
    }

    private ILikeWoodContainerRegistry() {
    }
}
