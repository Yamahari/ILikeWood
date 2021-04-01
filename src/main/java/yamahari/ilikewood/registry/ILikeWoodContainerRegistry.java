package yamahari.ilikewood.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.container.WoodenSawmillContainer;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodContainerRegistry {
    public static final DeferredRegister<ContainerType<?>> REGISTRY =
        DeferredRegister.create(ForgeRegistries.CONTAINERS, Constants.MOD_ID);

    static {
        WoodenContainerTypes.WOODEN_SAWMILL =
            REGISTRY.register("wooden_sawmill", () -> new ContainerType<>(WoodenSawmillContainer::new));
        WoodenContainerTypes.WOODEN_WORK_BENCH =
            REGISTRY.register("wooden_workbench", () -> new ContainerType<>(WoodenWorkBenchContainer::new));
    }

    private ILikeWoodContainerRegistry() {
    }
}
