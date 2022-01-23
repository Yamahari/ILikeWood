package yamahari.ilikewood.provider.texture;

import com.google.common.base.Preconditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class TextureFile {
    private final ResourceLocation location;

    protected TextureFile(final ResourceLocation location) {
        this.location = location;
    }

    protected abstract boolean exists();

    public ResourceLocation getLocation() {
        assertExistence();
        return this.location;
    }

    public void assertExistence() {
        Preconditions.checkState(exists(), "Texture at %s does not exist", this.location);
    }

    public ResourceLocation getUncheckedLocation() {
        return this.location;
    }

    public static class UncheckedTextureFile extends TextureFile {

        public UncheckedTextureFile(final String location) {
            this(new ResourceLocation(location));
        }

        public UncheckedTextureFile(final ResourceLocation location) {
            super(location);
        }

        @Override
        protected boolean exists() {
            return true;
        }
    }

    public static class ExistingTextureFile extends TextureFile {
        private final ExistingFileHelper existingHelper;

        public ExistingTextureFile(final ResourceLocation location, final ExistingFileHelper existingHelper) {
            super(location);
            this.existingHelper = existingHelper;
        }

        @Override
        protected boolean exists() {
            return existingHelper.exists(getUncheckedLocation(), AbstractTextureProvider.TEXTURE);
        }
    }
}
