package yamahari.ilikewood.provider.texture;

import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Function;

public class TextureBuilder extends TextureFile {
    protected final ExistingFileHelper helper;
    private TextureFile parent;
    private Function<NativeImage, NativeImage> transformer;
    private boolean animated;
    private boolean interpolate;
    private int frameTime;

    public TextureBuilder(final ResourceLocation outputLocation, final ExistingFileHelper helper) {
        super(outputLocation);
        this.helper = helper;
        this.animated = false;
        this.interpolate = false;
        this.frameTime = 1;
    }

    public TextureBuilder parent(final TextureFile parent) {
        Preconditions.checkNotNull(parent, "Parent must not be null");
        parent.assertExistence();
        this.parent = parent;

        return this;
    }

    public TextureBuilder transform(final Function<NativeImage, NativeImage> transformer) {
        this.transformer = transformer;

        return this;
    }

    public TextureBuilder animate(final boolean interpolate, final int frameTime) {
        this.animated = true;
        this.interpolate = interpolate;
        this.frameTime = frameTime;

        return this;
    }

    public void build(@Nonnull final Path rootPath, @Nonnull final HashCache cache) throws IOException {
        try {
            final var parentResource = this.helper.getResource(this.parent.getLocation(),
                PackType.CLIENT_RESOURCES,
                AbstractTextureProvider.TEXTURE.getSuffix(),
                AbstractTextureProvider.TEXTURE.getPrefix());
            final var parentImage = NativeImage.read(parentResource.getInputStream());

            final var image = this.transformer.apply(parentImage);
            final var pngPath = rootPath.resolve(Paths.get(PackType.CLIENT_RESOURCES.getDirectory(),
                this.getLocation().getNamespace(),
                AbstractTextureProvider.TEXTURE.getPrefix(),
                this.getLocation().getPath() + AbstractTextureProvider.TEXTURE.getSuffix()));

            if (this.animated) {
                final var mcMetaPath = rootPath.resolve(Paths.get(PackType.CLIENT_RESOURCES.getDirectory(),
                    this.getLocation().getNamespace(),
                    AbstractTextureProvider.TEXTURE.getPrefix(),
                    this.getLocation().getPath() + AbstractTextureProvider.TEXTURE.getSuffix()) + ".mcmeta");
                final var jsonObject = new JsonObject();
                final var animation = new JsonObject();
                animation.addProperty("interpolate", this.interpolate);
                animation.addProperty("frametime", this.frameTime);
                jsonObject.add("animation", animation);
                DataProvider.save(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create(),
                    cache,
                    jsonObject,
                    mcMetaPath);
            }

            final var hash = DataProvider.SHA1.hashBytes(image.asByteArray()).toString();
            if (!Objects.equals(cache.getHash(pngPath), hash) || !Files.exists(pngPath)) {
                Files.createDirectories(pngPath.getParent());
                image.writeToFile(pngPath);
            }
            cache.putNew(pngPath, hash);
        } catch (IOException e) {
            ILikeWood.LOGGER.error("Couldn't create data for {}", this.getLocation(), e);
            throw e;
        }
    }

    @Override
    protected boolean exists() {
        return true;
    }
}
