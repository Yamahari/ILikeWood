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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public record ColoredCampfireSmokeParticleProvider(DataGenerator generator) implements DataProvider {
    @Override
    @Nonnull
    public CompletableFuture<?> run(@Nonnull final CachedOutput cache) {
        final List<CompletableFuture<?>> cfs = new ArrayList<>();

        for (final DyeColor dyeColor : DyeColor.values()) {
            try {
                final var out = new JsonObject();

                final var textures = new JsonArray();

                for (int i = 0; i < 12; ++i) {
                    textures.add(new ResourceLocation(Constants.MOD_ID,
                        Util.toPath(WoodenBlockType.CAMPFIRE.getName(), "big_smoke", dyeColor.getName(), Integer.toString(i))).toString());
                }

                out.add("textures", textures);

                cfs.add(DataProvider.saveStable(cache,
                    out,
                    this.generator.getPackOutput(Util.toPath("assets", Constants.MOD_ID, "particles", String.format("%s_campfire_cosy_smoke.json", dyeColor.getName())))
                        .getOutputFolder()));
                cfs.add(DataProvider.saveStable(cache,
                    out,
                    this.generator.getPackOutput(Util.toPath("assets", Constants.MOD_ID, "particles", String.format("%s_campfire_signal_smoke.json", dyeColor.getName())))
                        .getOutputFolder()));

            }
            catch (IllegalStateException e) {
                throw new RuntimeException(e);
            }
        }

        return CompletableFuture.allOf(cfs.toArray(CompletableFuture[]::new));
    }

    @Nonnull
    @Override
    public String getName() {
        return "ilikewood - colored campfire smoke particles";
    }
}
