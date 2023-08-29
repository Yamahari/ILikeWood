package yamahari.ilikewood.event;

import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.block.WoodenCampfireBlock;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(value = {
    Dist.CLIENT,
    Dist.DEDICATED_SERVER
}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class FMLCommonSetupEventHandler
{
    @SubscribeEvent
    public static void onFMLCommonSetupEventHandler(final FMLCommonSetupEvent event)
    {
        for (final var dyeColor : DyeColor.values())
        {
            final var dyeItem = DyeItem.byColor(dyeColor);

            DispenserBlock.registerBehavior(dyeItem, new OptionalDispenseItemBehavior()
            {
                @Nonnull
                @Override
                protected ItemStack execute(
                    @Nonnull final BlockSource source,
                    @Nonnull final ItemStack stack
                )
                {
                    final var level = source.getLevel();
                    if (!level.isClientSide())
                    {
                        final var pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                        final var target = level.getBlockState(pos);
                        this.setSuccess(false);
                        if (target.getBlock() instanceof WoodenCampfireBlock)
                        {
                            if (target.getValue(WoodenCampfireBlock.COLORED))
                            {
                                if (!target.getValue(WoodenCampfireBlock.COLOR).equals(dyeColor))
                                {
                                    level.setBlock(pos, target.setValue(WoodenCampfireBlock.COLOR, dyeColor), Block.UPDATE_ALL);
                                    stack.shrink(1);
                                    this.setSuccess(true);
                                }
                            }
                            else
                            {
                                level.setBlock(pos, target.setValue(WoodenCampfireBlock.COLORED, true).setValue(WoodenCampfireBlock.COLOR, dyeColor), Block.UPDATE_ALL);
                                stack.shrink(1);
                                this.setSuccess(true);
                            }
                            return stack;
                        }
                    }
                    return super.execute(source, stack);
                }
            });
        }

        DispenserBlock.registerBehavior(Items.BONE_MEAL, new OptionalDispenseItemBehavior()
        {
            @Nonnull
            @Override
            protected ItemStack execute(
                @Nonnull final BlockSource source,
                @Nonnull final ItemStack stack
            )
            {
                final var level = source.getLevel();
                final var pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                if (!level.isClientSide)
                {
                    final var target = level.getBlockState(pos);
                    this.setSuccess(false);
                    if (target.getBlock() instanceof WoodenCampfireBlock)
                    {
                        if (target.getValue(WoodenCampfireBlock.COLORED))
                        {
                            level.setBlock(pos, target.setValue(WoodenCampfireBlock.COLORED, false), Block.UPDATE_ALL);
                            stack.shrink(1);
                            this.setSuccess(true);
                        }
                        return stack;
                    }
                }

                // TODO this is a fix for vanilla behaviour

                this.setSuccess(true);

                if (!BoneMealItem.growCrop(stack, level, pos) && !BoneMealItem.growWaterPlant(stack, level, pos, null))
                {
                    this.setSuccess(false);
                }
                else if (!level.isClientSide)
                {
                    level.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, pos, 0);
                }

                return stack;
            }
        });
    }
}
