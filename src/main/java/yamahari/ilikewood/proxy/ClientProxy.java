package yamahari.ilikewood.proxy;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.client.gui.screen.WoodenSawmillScreen;
import yamahari.ilikewood.container.WoodenSawmillContainer;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;

import java.util.stream.Stream;

public final class ClientProxy implements IProxy {
    @SuppressWarnings("unchecked")
    @Override
    public void onFMLClientSetup(final FMLClientSetupEvent event) {
        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.POST, WoodenBlockType.STRIPPED_POST))
            .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.solid()));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.LADDER,
                WoodenBlockType.TORCH,
                WoodenBlockType.WALL_TORCH,
                WoodenBlockType.SCAFFOLDING,
                WoodenBlockType.SOUL_TORCH,
                WoodenBlockType.WALL_SOUL_TORCH))
            .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout()));

        ILikeWood.BLOCK_REGISTRY
            .getObjects(Stream.of(WoodenBlockType.CRAFTING_TABLE,
                WoodenBlockType.SAWMILL,
                WoodenBlockType.CHAIR,
                WoodenBlockType.TABLE))
            .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped()));

        MenuScreens.register((MenuType<? extends CraftingMenu>) WoodenContainerTypes.WOODEN_WORK_BENCH.get(),
            CraftingScreen::new);

        MenuScreens.register((MenuType<? extends WoodenSawmillContainer>) WoodenContainerTypes.WOODEN_SAWMILL.get(),
            WoodenSawmillScreen::new);

        ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.BOW).forEach(item -> {
            //noinspection deprecation
            ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, i) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return entity.getUseItem() != stack
                           ? 0.0F
                           : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
                }
            });

            //noinspection deprecation
            ItemProperties.register(item,
                new ResourceLocation("pulling"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack
                                             ? 1.0F
                                             : 0.0F);
        });

        ILikeWood.ITEM_REGISTRY.getObjects(WoodenItemType.CROSSBOW).forEach(item -> {
            //noinspection deprecation
            ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, i) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    return CrossbowItem.isCharged(stack)
                           ? 0.0F
                           : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) /
                             (float) CrossbowItem.getChargeDuration(stack);
                }
            });

            //noinspection deprecation
            ItemProperties.register(item,
                new ResourceLocation("pulling"),
                (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack &&
                                             !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);

            //noinspection deprecation
            ItemProperties.register(item,
                new ResourceLocation("charged"),
                (stack, level, entity, i) -> entity != null && CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);

            //noinspection deprecation
            ItemProperties.register(item,
                new ResourceLocation("firework"),
                (stack, level, entity, i) -> entity != null && CrossbowItem.isCharged(stack) &&
                                             CrossbowItem.containsChargedProjectile(stack, Items.FIREWORK_ROCKET)
                                             ? 1.0F
                                             : 0.0F);
        });

        //noinspection deprecation
        ILikeWood.ITEM_REGISTRY
            .getObjects(WoodenItemType.FISHING_ROD)
            .forEach(item -> ItemProperties.register(item, new ResourceLocation("cast"), (stack, level, entity, i) -> {
                if (entity == null) {
                    return 0.0F;
                } else {
                    boolean flag = entity.getMainHandItem() == stack;
                    boolean flag1 = entity.getOffhandItem() == stack;
                    if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                        flag1 = false;
                    }

                    return (flag || flag1) && entity instanceof Player && ((Player) entity).fishing != null
                           ? 1.0F
                           : 0.0F;
                }
            }));
    }

    @Override
    public void onFMLCommonSetup(final FMLCommonSetupEvent event) {
    }
}
