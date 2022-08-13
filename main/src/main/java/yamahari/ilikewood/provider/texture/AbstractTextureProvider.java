package yamahari.ilikewood.provider.texture;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.AbstractILikeWoodObjectRegistry;
import yamahari.ilikewood.registry.objecttype.AbstractWoodenObjectType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractTextureProvider<T, O extends AbstractWoodenObjectType, R extends AbstractILikeWoodObjectRegistry<T, O>>
    implements DataProvider
{
    public static final ExistingFileHelper.ResourceType TEXTURE = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");

    public static final String BLOCK_FOLDER = "block";
    public static final String ENTITY_FOLDER = "entity";
    public static final String ITEM_FOLDER = "item";

    public final Map<ResourceLocation, TextureBuilder> generatedTextures = new HashMap<>();
    private final String root;
    private final O objectType;
    private final R objectRegistry;
    private final Function<ResourceLocation, TextureBuilder> factory;
    private final DataGenerator generator;
    private final ExistingFileHelper helper;
    private final String folder;
    private final boolean runOnce;

    public AbstractTextureProvider(
        final DataGenerator generator,
        final String folder,
        final ExistingFileHelper helper,
        final String root,
        final O objectType,
        final R objectRegistry,
        final boolean runOnce
    )
    {
        this.generator = generator;
        this.folder = folder;
        this.helper = helper;
        this.root = root;
        this.objectType = objectType;
        this.factory = location -> new TextureBuilder(location, helper);
        this.objectRegistry = objectRegistry;
        this.runOnce = runOnce;
    }

    public AbstractTextureProvider(
        final DataGenerator generator,
        final String folder,
        final ExistingFileHelper helper,
        final String root,
        final O objectType,
        final R objectRegistry
    )
    {
        this(generator, folder, helper, root, objectType, objectRegistry, false);
    }


    private Map<Integer, Integer> createColorMap(final IWoodType woodType)
    {
        final Map<Integer, Integer> colorMap = new HashMap<>();

        final int[] spruceColors = VanillaWoodTypes.SPRUCE.getColors().colors();
        final int[] colors = woodType.getColors().colors();

        for (int i = 0; i < 8; ++i)
        {
            colorMap.put(spruceColors[i], colors[i]);
        }

        return Collections.unmodifiableMap(colorMap);
    }

    protected Function<NativeImage, NativeImage> createColorMapTransformer(final Map<Integer, Integer> colorMap)
    {
        return image ->
        {
            final NativeImage result = new NativeImage(image.getWidth(), image.getHeight(), true);
            result.copyFrom(image);
            for (int y = 0; y < image.getHeight(); ++y)
            {
                for (int x = 0; x < image.getWidth(); ++x)
                {
                    final int rgba = image.getPixelRGBA(x, y);
                    if (colorMap.containsKey(rgba))
                    {
                        result.setPixelRGBA(x, y, colorMap.get(rgba));
                    }
                }
            }
            return result;
        };
    }

    protected Function<NativeImage, NativeImage> createColorMapTransformer2(final Map<Integer, Integer> colorMap)
    {
        return image ->
        {
            final NativeImage result = new NativeImage(image.getWidth(), image.getHeight(), true);
            result.copyFrom(image);
            for (int y = 0; y < image.getHeight(); ++y)
            {
                for (int x = 0; x < image.getWidth(); ++x)
                {
                    final int rgba = image.getPixelRGBA(x, y);
                    if (colorMap.containsKey(rgba))
                    {
                        result.setPixelRGBA(x, y, colorMap.get(rgba));
                    }
                }
            }
            return result;
        };
    }

    protected Function<NativeImage, NativeImage> createColorMapTransformer(final IWoodType woodType)
    {
        return createColorMapTransformer(createColorMap(woodType));
    }

    protected NativeImage postTransformer(final NativeImage image)
    {
        Preconditions.checkArgument(image.getWidth() == 16);
        Preconditions.checkArgument(image.getHeight() % image.getWidth() == 0);

        final NativeImage result = new NativeImage(image.getWidth(), image.getHeight(), true);
        result.copyFrom(image);

        final int di = image.getHeight() / image.getWidth();
        for (int i = 0; i < di; ++i)
        {
            Rect2i cut = new Rect2i(0, 12, 8, 4);
            NativeImage copy;
            copy = copyRect(image, i, cut);

            final Rect2i paste = new Rect2i(8, 0, 8, 4);

            for (int y = 0; y < paste.getHeight(); ++y)
            {
                for (int x = 0; x < paste.getWidth(); ++x)
                {
                    result.setPixelRGBA(paste.getX() + x, i * 16 + paste.getY() + y, copy.getPixelRGBA(copy.getWidth() - x - 1, copy.getHeight() - y - 1));
                }
            }

            cut = new Rect2i(0, 0, 16, 4);
            copy = copyRect(result, i, cut);

            for (int y = 0; y < cut.getHeight(); ++y)
            {
                for (int x = 0; x < cut.getWidth(); ++x)
                {
                    final int color = copy.getPixelRGBA(x, y);
                    result.setPixelRGBA(15 - y, i * 16 + x, color);
                    result.setPixelRGBA(15 - x, i * 16 + 15 - y, color);
                    result.setPixelRGBA(y, i * 16 + 15 - x, color);
                }
            }

            for (int y = 4; y < 12; ++y)
            {
                for (int x = 4; x < 12; ++x)
                {
                    result.setPixelRGBA(x, i * 16 + y, 0x00000000);
                }
            }

            for (int y = 0; y < 4; ++y)
            {
                for (int x = 0; x < 4; ++x)
                {
                    result.setPixelRGBA(x, i * 16 + y, 0x00000000);
                    result.setPixelRGBA(12 + x, i * 16 + y, 0x00000000);
                    result.setPixelRGBA(12 + x, i * 16 + 12 + y, 0x00000000);
                    result.setPixelRGBA(x, 12 + i * 16 + y, 0x00000000);
                }
            }
        }

        return result;
    }

    private NativeImage copyRect(
        final NativeImage image,
        final int i,
        final Rect2i cut
    )
    {
        final NativeImage copy = new NativeImage(cut.getWidth(), cut.getHeight(), true);

        for (int y = 0; y < cut.getHeight(); ++y)
        {
            for (int x = 0; x < cut.getWidth(); ++x)
            {
                copy.setPixelRGBA(x, y, image.getPixelRGBA(cut.getX() + x, i * 16 + cut.getY() + y));
            }
        }

        return copy;
    }

    public TextureBuilder getBuilder(String path)
    {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation outputLoc = extendWithFolder(path.contains(":") ? mcLoc(path) : modLoc(path));
        this.helper.trackGenerated(outputLoc, TEXTURE);
        return this.generatedTextures.computeIfAbsent(outputLoc, this.factory);
    }

    private ResourceLocation extendWithFolder(ResourceLocation rl)
    {
        if (rl.getPath().contains("/"))
        {
            return rl;
        }
        return new ResourceLocation(rl.getNamespace(), this.folder + "/" + rl.getPath());
    }

    public TextureBuilder withExistingParent(
        final String name,
        final String parent
    )
    {
        return withExistingParent(name, mcLoc(parent));
    }

    public TextureBuilder withExistingParent(
        final String name,
        final ResourceLocation parent
    )
    {
        return this.getBuilder(name).parent(getExistingFile(parent));
    }

    public TextureFile.ExistingTextureFile getExistingFile(ResourceLocation path)
    {
        final TextureFile.ExistingTextureFile file = new TextureFile.ExistingTextureFile(extendWithFolder(path), helper);
        file.assertExistence();
        return file;
    }

    public ResourceLocation modLoc(final String name)
    {
        return new ResourceLocation(Constants.MOD_ID, name);
    }

    public ResourceLocation mcLoc(final String name)
    {
        return new ResourceLocation(name);
    }

    @Override
    public void run(@Nonnull final CachedOutput cache)
        throws
        IOException
    {
        // TODO hacky workaround for bad design
        if (this.runOnce)
        {
            this.createTexture(null);
        }
        else
        {
            this.objectRegistry.getObjects(this.objectType).forEach(this::createTexture);
        }

        for (final TextureBuilder builder : this.generatedTextures.values())
        {
            builder.build(this.generator.getOutputFolder().resolve(this.root), cache);
        }
    }

    @Nonnull
    @Override
    public String getName()
    {
        return String.format("%s - textures - %s", Constants.MOD_ID, this.objectType.getName());
    }

    protected abstract void createTexture(T object);
}
