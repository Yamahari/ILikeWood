package yamahari.ilikewood.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.container.WoodenWorkBenchContainer;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

@JeiPlugin
public final class ILikeWoodPlugin implements IModPlugin
{
    private static final ResourceLocation PLUGIN_UID = new ResourceLocation(Constants.MOD_ID, "plugin");

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerRecipeTransferHandlers(@Nonnull final IRecipeTransferRegistration registration) {
        if (ILikeWoodConfig.CRAFTING_TABLES_CONFIG.isEnabled())
        {
            registration.addRecipeTransferHandler(WoodenWorkBenchContainer.class,
                MenuType.CRAFTING,
                RecipeTypes.CRAFTING,
                1,
                9,
                10,
                36
            );
        }
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
        if (ILikeWoodConfig.CRAFTING_TABLES_CONFIG.isEnabled())
        {
            registration.addRecipeCatalyst(new ItemStack(ILikeWood.BLOCK_REGISTRY.getObject(VanillaWoodTypes.OAK,
                WoodenBlockType.CRAFTING_TABLE
            )), RecipeTypes.CRAFTING);
        }
    }

    @Override
    public void registerGuiHandlers(@Nonnull final IGuiHandlerRegistration registration) {
    }
}
