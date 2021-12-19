package yamahari.ilikewood.provider.blockstate;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

public final class BarrelBlockStateProvider extends AbstractBlockStateProvider {
    public BarrelBlockStateProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, helper, WoodenBlockType.BARREL);
    }

    @Override
    public void registerStateAndModel(final Block block) {
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER,
            WoodenBlockType.BARREL.getName(),
            "%s",
            ((IWooden) block).getWoodType().getName());
        this.directionalBlock(block, state -> {
            final boolean open = state.getValue(BarrelBlock.OPEN);
            return this
                .models()
                .cubeBottomTop(String.format(path, (open ? "open" : "")),
                    modLoc(String.format(path, "side")),
                    modLoc(String.format(path, "bottom")),
                    modLoc(String.format(path, "top" + (open ? "/open" : ""))));
        });
    }
}
