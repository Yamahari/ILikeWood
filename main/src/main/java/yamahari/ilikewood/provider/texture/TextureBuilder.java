package yamahari.ilikewood.provider.texture;

import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class TextureBuilder extends TextureFile
{
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

    public void build(@Nonnull final Path rootPath, @Nonnull final CachedOutput cache) throws IOException {
        try
        {
            final var parentResource = this.helper.getResource(
                this.parent.getLocation(),
                PackType.CLIENT_RESOURCES,
                AbstractTextureProvider.TEXTURE.getSuffix(),
                AbstractTextureProvider.TEXTURE.getPrefix()
            );
            final var parentImage = NativeImage.read(parentResource.open());

            final var image = this.transformer.apply(parentImage);
            final var pngPath = rootPath.resolve(Paths.get(
                PackType.CLIENT_RESOURCES.getDirectory(),
                this.getLocation().getNamespace(),
                AbstractTextureProvider.TEXTURE.getPrefix(),
                this.getLocation().getPath() + AbstractTextureProvider.TEXTURE.getSuffix()
            ));

            if (this.animated)
            {
                final var mcMetaPath = rootPath.resolve(Paths.get(
                    PackType.CLIENT_RESOURCES.getDirectory(),
                    this.getLocation().getNamespace(),
                    AbstractTextureProvider.TEXTURE.getPrefix(),
                    this.getLocation().getPath() + AbstractTextureProvider.TEXTURE.getSuffix()
                ) + ".mcmeta");
                final var jsonObject = new JsonObject();
                final var animation = new JsonObject();
                animation.addProperty("interpolate", this.interpolate);
                animation.addProperty("frametime", this.frameTime);
                jsonObject.add("animation", animation);
                DataProvider.saveStable(cache, jsonObject, mcMetaPath);
            }


            final var byteArrayOutputStream = new ByteArrayOutputStream();
            final var hashingOutputStream = new HashingOutputStream(Hashing.sha1(), byteArrayOutputStream);
            hashingOutputStream.write(image.asByteArray());
            cache.writeIfNeeded(pngPath, byteArrayOutputStream.toByteArray(), hashingOutputStream.hash());
        }
        catch (IOException e)
        {
            ILikeWood.LOGGER.error("Couldn't create data for {}", this.getLocation(), e);
            throw e;
        }
    }

    @Override
    protected boolean exists() {
        return true;
    }
}
