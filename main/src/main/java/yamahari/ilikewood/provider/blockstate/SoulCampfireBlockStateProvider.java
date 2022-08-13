package yamahari.ilikewood.provider.blockstate;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenCampfireBlock;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.resource.resources.IWoodenLogResource;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.util.HashMap;

public class SoulCampfireBlockStateProvider
    extends AbstractBlockStateProvider
{
    public SoulCampfireBlockStateProvider(
        final DataGenerator generator,
        final ExistingFileHelper helper
    )
    {
        super(generator, helper, WoodenBlockType.SOUL_CAMPFIRE);
    }

    @Override
    public void registerStateAndModel(final Block block)
    {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final String path = Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.CAMPFIRE.getName());

        final IWoodenLogResource log = ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType);

        final ResourceLocation logPileEnd = modLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, WoodenBlockType.LOG_PILE.getName(), woodType.getName()));

        final var off = this
            .models()
            .withExistingParent(Util.toPath(path, "soul", "off", woodType.getName()), modLoc(Util.toPath(path, "off", "template")))
            .texture("log_pile_end", logPileEnd)
            .texture("log_side", log.getSideTexture());

        final var on = this
            .models()
            .withExistingParent(Util.toPath(path, "soul", woodType.getName()), modLoc(Util.toPath(path, "template")))
            .texture("fire", mcLoc(Util.toPath(ModelProvider.BLOCK_FOLDER, "soul_campfire_fire")))
            .texture("log_pile_end", logPileEnd)
            .texture("log_side", log.getSideTexture())
            .texture("campfire_log_lit", modLoc(Util.toPath(path, "soul", "log_lit", "template")));

        final var colored = new HashMap<DyeColor, BlockModelBuilder>();

        for (final DyeColor color : DyeColor.values())
        {
            colored.put(color, this
                .models()
                .withExistingParent(Util.toPath(path, "soul", color.getName(), woodType.getName()), modLoc(Util.toPath(path, "template")))
                .texture("fire", modLoc(Util.toPath(path, "fire", color.getName())))
                .texture("log_pile_end", logPileEnd)
                .texture("log_side", log.getSideTexture())
                .texture("campfire_log_lit", modLoc(Util.toPath(path, "log_lit", color.getName()))));
        }

        this.getVariantBuilder(block).forAllStates(state ->
        {
            if (state.getValue(CampfireBlock.LIT))
            {
                return ConfiguredModel
                    .builder()
                    .modelFile(state.getValue(WoodenCampfireBlock.COLORED) ? colored.get(state.getValue(WoodenCampfireBlock.COLOR)) : on)
                    .rotationY(((state.getValue(CampfireBlock.FACING).get2DDataValue() + 2) % 4) * 90)
                    .uvLock(false)
                    .build();
            }
            else
            {
                return ConfiguredModel.builder().modelFile(off).rotationY(((state.getValue(CampfireBlock.FACING).get2DDataValue() + 2) % 4) * 90).uvLock(false).build();
            }
        });
    }
}
