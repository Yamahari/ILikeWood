package yamahari.ilikewood.registry;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.menu.WoodenCrateMenu;
import yamahari.ilikewood.menu.WoodenSawmillMenu;
import yamahari.ilikewood.menu.WoodenWorkBenchMenu;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodMenuTypeRegistry {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Constants.MOD_ID);

    static {
        if (ILikeWoodConfig.SAWMILLS_CONFIG.isEnabled()) {
            WoodenMenuTypes.WOODEN_SAWMILL = REGISTRY.register("wooden_sawmill", () -> new MenuType<>(WoodenSawmillMenu::new, FeatureFlagSet.of()));
        }

        if (ILikeWoodConfig.CRAFTING_TABLES_CONFIG.isEnabled()) {
            WoodenMenuTypes.WOODEN_WORK_BENCH = REGISTRY.register("wooden_workbench", () -> new MenuType<>(WoodenWorkBenchMenu::new, FeatureFlagSet.of()));
        }

        if (ILikeWoodConfig.CRATE_CONFIG.isEnabled()) {
            WoodenMenuTypes.WOODEN_CRATE = REGISTRY.register("wooden_crate", () -> new MenuType<>(WoodenCrateMenu::new, FeatureFlagSet.of()));
        }
    }

    private ILikeWoodMenuTypeRegistry() {
    }
}
