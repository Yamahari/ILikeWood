package yamahari.ilikewood.provider.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.io.IOException;


public record ColoredLavaParticleProvider(DataGenerator generator)
    implements DataProvider
{
    @Override
    public void run(@Nonnull final CachedOutput cache)
    {
        for (final DyeColor dyeColor : DyeColor.values())
        {
            try
            {
                final var out = new JsonObject();

                final var textures = new JsonArray();

                for (int i = 0; i < 12; ++i)
                {
                    textures.add(new ResourceLocation(Constants.MOD_ID, Util.toPath(WoodenBlockType.CAMPFIRE.getName(), "lava", dyeColor.getName())).toString());
                }

                out.add("textures", textures);

                DataProvider.saveStable(cache, out,
                    this.generator.getOutputFolder().resolve(Util.toPath("assets", Constants.MOD_ID, "particles", String.format("%s_lava.json", dyeColor.getName())))
                );
            }
            catch (IOException | IllegalStateException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Nonnull
    @Override
    public String getName()
    {
        return "ilikewood - colored lava particles";
    }
}
