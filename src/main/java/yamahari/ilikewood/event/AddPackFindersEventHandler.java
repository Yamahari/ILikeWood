package yamahari.ilikewood.event;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.PathResourcePack;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.IModPlugin;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.io.IOException;

@Mod.EventBusSubscriber(value = {Dist.CLIENT,
                                 Dist.DEDICATED_SERVER}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class AddPackFindersEventHandler {
    @SubscribeEvent
    public static void onAddPackFinders(final AddPackFindersEvent event) {
        for (final IModPlugin plugin : ILikeWood.PLUGINS) {
            final var modId = plugin.getModId();
            final var modFile = ModList.get().getModFileById(modId).getFile();
            final var s = Util.toRegistryName(modId, "resources");
            final var resourcePath = modFile.findResource(s);
            final var pack = new PathResourcePack(modFile.getFileName() + ":" + resourcePath, resourcePath);
            final PackMetadataSection metaDataSection;
            try {
                metaDataSection = pack.getMetadataSection(PackMetadataSection.SERIALIZER);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            if (metaDataSection != null) {
                event.addRepositorySource((consumer, constructor) -> consumer.accept(constructor.create(String.format(
                        "%s:%s",
                        Constants.MOD_ID,
                        modId),
                    new TextComponent(String.format("%s - %s resources", Constants.MOD_ID, modId)),
                    true,
                    () -> pack,
                    metaDataSection,
                    Pack.Position.BOTTOM,
                    PackSource.BUILT_IN,
                    false)));
            }
        }
    }
}
