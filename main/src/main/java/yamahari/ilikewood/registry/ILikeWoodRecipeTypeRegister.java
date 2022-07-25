package yamahari.ilikewood.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodRecipeTypeRegister
{
    private final DeferredRegister<RecipeType<?>> registry = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES,
        Constants.MOD_ID
    );

    public void register(final IEventBus eventBus) {
        this.register();
        this.registry.register(eventBus);
    }

    public void register() {
        WoodenRecipeTypes.SAWMILLING = this.registry.register(Constants.SAWMILLING,
            () -> RecipeType.simple(new ResourceLocation(Constants.MOD_ID, Constants.SAWMILLING))
        );
    }
}
