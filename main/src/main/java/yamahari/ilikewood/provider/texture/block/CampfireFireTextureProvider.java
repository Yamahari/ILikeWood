package yamahari.ilikewood.provider.texture.block;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

public class CampfireFireTextureProvider
    extends AbstractBlockTextureProvider
{

    public CampfireFireTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, BLOCK_FOLDER, helper, Constants.CAMPFIRE_PLURAL, WoodenBlockType.CAMPFIRE, true);
    }

    @Override
    protected void createTexture(final Block block)
    {
        final int[] fireColors = {
            11616000,
            13200387,
            14656027,
            15318329,
            15715670,
            16378795,
            16711419
        };


        for (final var dyeColor : DyeColor.values())
        {
            final var fireColorMap = Util.createShadeColorMap(dyeColor, fireColors);

            this
                .withExistingParent(Util.toPath(BLOCK_FOLDER, WoodenBlockType.CAMPFIRE.getName(), "fire", dyeColor.getName()),
                    mcLoc(Util.toPath(BLOCK_FOLDER, "campfire_fire"))
                )
                .transform(createColorMapTransformer(fireColorMap))
                .animate(false, 2);

            this.withExistingParent(
                Util.toPath(BLOCK_FOLDER, WoodenBlockType.CAMPFIRE.getName(), "log_lit", dyeColor.getName()),
                modLoc(Util.toPath(BLOCK_FOLDER, WoodenBlockType.CAMPFIRE.getName(), "log_lit", "template"))
            ).transform(createColorMapTransformer(fireColorMap)).animate(true, 20);


        }
    }
}
