package yamahari.ilikewood.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import yamahari.ilikewood.menu.WoodenCrateMenu;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

public class WoodenCrateScreen extends AbstractContainerScreen<WoodenCrateMenu> {
    private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/crate.png");

    public WoodenCrateScreen(final WoodenCrateMenu menu, final Inventory inventory, final Component component) {
        super(menu, inventory, component);
        ++this.imageHeight;
    }

    @Override
    public void render(@Nonnull final GuiGraphics guiGraphics, final int x, final int y, final float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, x, y, partialTick);
        this.renderTooltip(guiGraphics, x, y);
    }

    @Override
    protected void renderBg(@Nonnull final GuiGraphics guiGraphics, final float partialTick, final int x, final int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CONTAINER_TEXTURE);
        final int i = (this.width - this.imageWidth) / 2;
        final int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CONTAINER_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
