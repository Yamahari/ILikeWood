package yamahari.ilikewood.provider.texture;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.util.function.Function;

// TODO add animated textures
public class TextureBuilder extends TextureFile {
    protected final ExistingFileHelper helper;
    private TextureFile parent;
    private Function<NativeImage, NativeImage> transformer;

    public TextureBuilder(final ResourceLocation outputLocation, final ExistingFileHelper helper) {
        super(outputLocation);
        this.helper = helper;
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

    public NativeImage build() throws IOException {
        final Resource parentResource = this.helper.getResource(this.parent.getLocation(),
            PackType.CLIENT_RESOURCES,
            AbstractTextureProvider.TEXTURE.getSuffix(),
            AbstractTextureProvider.TEXTURE.getPrefix());
        final NativeImage parentImage = NativeImage.read(parentResource.getInputStream());
        return this.transformer.apply(parentImage);
    }

    @Override
    protected boolean exists() {
        return true;
    }
}
