package yamahari.ilikewood.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.client.tileentity.WoodenCampfireBlockEntity;
import yamahari.ilikewood.registry.WoodenParticleTypes;
import yamahari.ilikewood.registry.WoodenTileEntityTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;

public class WoodenCampfireBlock
    extends CampfireBlock
    implements IWooden
{
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.create("color", DyeColor.class);
    public static final BooleanProperty COLORED = BooleanProperty.create("colored");
    private final IWoodType woodType;
    private final boolean isSoul;

    private WoodenCampfireBlock(
        final boolean spawnParticles,
        final int fireDamage,
        final BlockBehaviour.Properties properties,
        final IWoodType woodType
    )
    {
        super(spawnParticles, fireDamage, properties);
        this.woodType = woodType;
        this.isSoul = !spawnParticles;
        this.defaultBlockState().setValue(COLORED, false).setValue(COLOR, DyeColor.WHITE);
    }

    public static WoodenCampfireBlock campfire(final IWoodType woodType)
    {
        return new WoodenCampfireBlock(true, 1, BlockBehaviour.Properties.copy(Blocks.CAMPFIRE), woodType);
    }

    public static WoodenCampfireBlock soulCampfire(final IWoodType woodType)
    {
        return new WoodenCampfireBlock(false, 2, BlockBehaviour.Properties.copy(Blocks.SOUL_CAMPFIRE), woodType);
    }

    public static void makeParticles(
        final Level level,
        final BlockPos pos,
        final boolean isSignalFire,
        final boolean isCooking
    )
    {
        final var state = level.getBlockState(pos);
        final var random = level.getRandom();
        final var block = state.getBlock();

        if (block instanceof WoodenCampfireBlock campfireBlock)
        {
            final SimpleParticleType particleType;
            if (campfireBlock.isSoul || !state.getValue(COLORED))
            {
                particleType = isSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
            }
            else
            {
                particleType = isSignalFire ? WoodenParticleTypes.COLORED_CAMPFIRE_SIGNAL_SMOKE.get(state.getValue(COLOR)).get()
                    : WoodenParticleTypes.COLORED_CAMPFIRE_COSY_SMOKE.get(state.getValue(COLOR)).get();
            }


            level.addAlwaysVisibleParticle(particleType, true, (double) pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1),
                (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                (double) pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D
            );

            if (isCooking)
            {
                level.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1),
                    (double) pos.getY() + 0.4D, (double) pos.getZ() + 0.5D + random.nextDouble() / 4.0D * (double) (random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D
                );
            }
        }
    }

    @Override
    public void animateTick(
        final BlockState state,
        @Nonnull final Level level,
        @Nonnull final BlockPos pos,
        @Nonnull final RandomSource random
    )
    {
        if (state.getValue(LIT))
        {
            if (random.nextInt(10) == 0)
            {
                level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS,
                    0.1F + random.nextFloat() * 0.4F, random.nextFloat() * 0.7F + 0.6F, false
                );
            }

            if (!this.isSoul && random.nextInt(5) == 0)
            {
                for (int i = 0; i < random.nextInt(1) + 1; ++i)
                {
                    if (!state.getValue(COLORED))
                    {
                        level.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
                            random.nextFloat() / 2.0F, 5.0E-5D, random.nextFloat() / 2.0F
                        );
                    }
                    else
                    {
                        level.addParticle(WoodenParticleTypes.COLORED_LAVA.get(state.getValue(COLOR)).get(), (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D,
                            (double) pos.getZ() + 0.5D, random.nextFloat() / 2.0F, 5.0E-5D, random.nextFloat() / 2.0F
                        );
                    }
                }
            }

        }
    }

    @Override
    public BlockEntity newBlockEntity(
        @Nonnull final BlockPos pos,
        @Nonnull final BlockState state
    )
    {
        return new WoodenCampfireBlockEntity(pos, state);
    }

    @Nonnull
    @Override
    public InteractionResult use(
        @Nonnull final BlockState state,
        final Level level,
        @Nonnull final BlockPos pos,
        @Nonnull final Player player,
        @Nonnull final InteractionHand hand,
        @Nonnull final BlockHitResult hitResult
    )
    {
        final var blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CampfireBlockEntity campfireBlockEntity)
        {
            final var itemStack = player.getItemInHand(hand);
            final var optional = campfireBlockEntity.getCookableRecipe(itemStack);
            if (optional.isPresent())
            {
                if (!level.isClientSide && campfireBlockEntity.placeFood(
                    player, player.getAbilities().instabuild ? itemStack.copy() : itemStack, optional.get().getCookingTime()))
                {
                    player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
            else
            {
                final var item = itemStack.getItem();
                if (item instanceof DyeItem dyeItem)
                {
                    if (!level.isClientSide)
                    {
                        final BlockState newBlockState = state.setValue(COLORED, true).setValue(COLOR, dyeItem.getDyeColor());
                        level.setBlock(pos, newBlockState, state != newBlockState ? Block.UPDATE_NEIGHBORS : 0);
                        if (!player.getAbilities().instabuild)
                        {
                            itemStack.shrink(1);
                        }
                    }
                }
                else if (item instanceof BoneMealItem)
                {
                    if (!level.isClientSide)
                    {
                        final var newBlockState = state.setValue(COLORED, false);
                        level.setBlock(pos, newBlockState, state != newBlockState ? Block.UPDATE_NEIGHBORS : 0);
                        if (!player.getAbilities().instabuild)
                        {
                            itemStack.shrink(1);
                        }
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull final StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR, COLORED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@Nonnull final BlockPlaceContext context)
    {
        return super.getStateForPlacement(context).setValue(COLOR, DyeColor.WHITE).setValue(COLORED, false);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        @Nonnull final Level level,
        @Nonnull final BlockState state,
        @Nonnull final BlockEntityType<T> blockEntityType
    )
    {
        if (level.isClientSide)
        {
            return state.getValue(LIT) ? createTickerHelper(blockEntityType,
                (BlockEntityType<? extends WoodenCampfireBlockEntity>) WoodenTileEntityTypes.WOODEN_CAMPFIRE.get(), WoodenCampfireBlockEntity::particleTick2
            ) : null;
        }
        else
        {
            return state.getValue(LIT) ? createTickerHelper(
                blockEntityType, (BlockEntityType<? extends CampfireBlockEntity>) WoodenTileEntityTypes.WOODEN_CAMPFIRE.get(), CampfireBlockEntity::cookTick)
                : createTickerHelper(blockEntityType, BlockEntityType.CAMPFIRE, CampfireBlockEntity::cooldownTick);
        }
    }

    @Override
    public IWoodType getWoodType()
    {
        return this.woodType;
    }
}
