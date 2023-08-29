package yamahari.ilikewood.registry;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import yamahari.ilikewood.util.Constants;

public class ILikeWoodCreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    static {
        ILikeWoodCreativeModeTabs.CREATIVE_MODE_TAB = REGISTRY.register("creative_mode_tab",
            () -> CreativeModeTab.builder()
                .title(Component.translatable(Util.makeDescriptionId("itemGroup", new ResourceLocation(Constants.MOD_ID, "creative_mode_tab"))))
                .icon(() -> new ItemStack(Items.OAK_LOG))
                .build());
    }
}
