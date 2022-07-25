package yamahari.ilikewood.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import yamahari.ilikewood.client.renderer.tileentity.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class WoodenChestBlockItem extends WoodenBlockItem
{
    public WoodenChestBlockItem(final Block block) {
        super(WoodenBlockType.CHEST, block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    }

    @Override
    public void initializeClient(@Nonnull final Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new WoodenChestItemStackTileEntityRenderer();
            }
        });
    }
}
