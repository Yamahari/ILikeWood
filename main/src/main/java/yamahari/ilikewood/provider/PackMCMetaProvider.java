package yamahari.ilikewood.provider;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;

public record PackMCMetaProvider(DataGenerator generator) implements DataProvider
{
    @Override
    public void run(@Nonnull final CachedOutput cache) {
        try
        {
            final var gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

            final var reader = gson.newJsonReader(Files.newBufferedReader(ModList.get()
                .getModFileById(Constants.MOD_ID)
                .getFile()
                .findResource("pack.mcmeta")));
            final var jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            final var outMCMeta = new JsonObject();

            // TODO fix this shit once pack version is in sync again
            //outMCMeta.add("pack", jsonObject.get("pack"));
            final var pack = new JsonObject();
            pack.addProperty("description", "ilikewood resources");
            pack.addProperty("pack_format", 10);
            outMCMeta.add("pack", pack);
            DataProvider.saveStable(cache, outMCMeta, this.generator.getOutputFolder().resolve("pack.mcmeta"));
        }
        catch (IOException | IllegalStateException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return "ilikewood - pack.mcmeta";
    }
}
