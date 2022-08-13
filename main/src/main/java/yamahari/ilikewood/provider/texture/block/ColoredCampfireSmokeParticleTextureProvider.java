package yamahari.ilikewood.provider.texture.block;


import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Map;

// TODO hacky workaround for bad design
public class ColoredCampfireSmokeParticleTextureProvider
    extends AbstractBlockTextureProvider
{
    public ColoredCampfireSmokeParticleTextureProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, "particle", helper, Constants.CAMPFIRE_PLURAL, WoodenBlockType.CAMPFIRE, true);
    }


    @Override
    protected void createTexture(final Block block)
    {
        final int[] smokeColors = {
            7038561,
            7235939,
            7301733,
            7433575,
            7630698,
            7827821,
            7893870,
            8025456,
            8156784,
            8222579,
            8288628,
            8354162,
            8485748,
            8486006,
            8683384,
            8880506,
            9077373,
            9143422,
            9275008,
            9341057
        };

        final int[] lavaColors = {
            16711680,
            16738816,
            16766976,
            16774598
        };

        for (final DyeColor dyeColor : DyeColor.values())
        {

            final Map<Integer, Integer> smokeColorMap = Util.createShadeColorMap(dyeColor, smokeColors);
            final Map<Integer, Integer> lavaColorMap = Util.createShadeColorMap(dyeColor, lavaColors);


            for (int i = 0; i < 12; ++i)
            {
                this.withExistingParent(
                    Util.toPath("particle", WoodenBlockType.CAMPFIRE.getName(), "big_smoke", dyeColor.getName(), Integer.toString(i)),
                    mcLoc(Util.toPath("particle", String.format("big_smoke_%d", i)))
                ).transform(createColorMapTransformer(smokeColorMap));
            }

            this
                .withExistingParent(Util.toPath("particle", WoodenBlockType.CAMPFIRE.getName(), "lava", dyeColor.getName()), mcLoc(Util.toPath("particle", "lava")))
                .transform(createColorMapTransformer(lavaColorMap));
        }
    }
}
