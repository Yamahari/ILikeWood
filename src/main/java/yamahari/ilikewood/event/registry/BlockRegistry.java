package yamahari.ilikewood.event.registry;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import yamahari.ilikewood.block.*;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodTypes;
import yamahari.ilikewood.util.WoodenObjectType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BlockRegistry {
    private BlockRegistry() {
    }

    @SubscribeEvent
    public static void onRegisterBlock(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        final ImmutableList<WoodenBlock> panels = new ImmutableList.Builder<WoodenBlock>()
                .add(new WoodenBlock(WoodTypes.ACACIA, Block.Properties.from(Blocks.ACACIA_PLANKS)))
                .add(new WoodenBlock(WoodTypes.BIRCH, Block.Properties.from(Blocks.BIRCH_PLANKS)))
                .add(new WoodenBlock(WoodTypes.DARK_OAK, Block.Properties.from(Blocks.DARK_OAK_PLANKS)))
                .add(new WoodenBlock(WoodTypes.JUNGLE, Block.Properties.from(Blocks.JUNGLE_PLANKS)))
                .add(new WoodenBlock(WoodTypes.OAK, Block.Properties.from(Blocks.OAK_PLANKS)))
                .add(new WoodenBlock(WoodTypes.SPRUCE, Block.Properties.from(Blocks.SPRUCE_PLANKS)))
                .build();

        for (final WoodenBlock panel : panels) {
            registry.registerAll(
                    panel.setRegistryName(Util.toRegistryName(panel.getWoodType().toString(), WoodenObjectType.PANELS.toString())),
                    new WoodenStairsBlock(panel.getWoodType(), panel.getDefaultState(), Block.Properties.from(panel)).setRegistryName(Util.toRegistryName(panel.getWoodType().toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString())),
                    new WoodenSlabBlock(panel.getWoodType(), Block.Properties.from(panel)).setRegistryName(Util.toRegistryName(panel.getWoodType().toString(), WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString()))
            );
        }

        WoodTypes.get().forEach(
                woodType -> registry.registerAll(
                        new WoodenBarrelBlock(woodType),
                        new WoodenBookshelfBlock(woodType),
                        new WoodenChestBlock(woodType),
                        new WoodenComposterBlock(woodType)
                )
        );
    }
}
