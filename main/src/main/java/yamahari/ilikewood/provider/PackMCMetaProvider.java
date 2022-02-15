package yamahari.ilikewood.provider;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;

public record PackMCMetaProvider(DataGenerator generator) implements DataProvider {
    @Override
    public void run(@Nonnull final HashCache cache) {
        try {
            final var gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

            final var reader = gson.newJsonReader(Files.newBufferedReader(ModList
                .get()
                .getModFileById(Constants.MOD_ID)
                .getFile()
                .findResource("pack.mcmeta")));
            final var jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            final var outMCMeta = new JsonObject();

            outMCMeta.add("pack", jsonObject.get("pack"));
            DataProvider.save(gson, cache, outMCMeta, this.generator.getOutputFolder().resolve("pack.mcmeta"));
        } catch (IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return "ilikewood - pack.mcmeta";
    }
}
