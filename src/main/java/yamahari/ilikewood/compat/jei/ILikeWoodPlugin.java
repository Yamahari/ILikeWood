package yamahari.ilikewood.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenObjectType;

@JeiPlugin
public final class ILikeWoodPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = new ResourceLocation(Constants.MOD_ID, "plugin");

    @SuppressWarnings("NullableProblems")
    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerRecipeTransferHandlers(final IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(WoodenWorkBenchContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
    }

    @Override
    public void registerRecipeCatalysts(final IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(WoodenBlocks.getBlock(WoodenObjectType.CRAFTING_TABLE, VanillaWoodTypes.OAK)), VanillaRecipeCategoryUid.CRAFTING);
        // WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE).forEach(block -> registration.addRecipeCatalyst(new ItemStack(block), VanillaRecipeCategoryUid.CRAFTING));
    }

    @Override
    public void registerGuiHandlers(@SuppressWarnings("NullableProblems") final IGuiHandlerRegistration registration) {
    }
}
