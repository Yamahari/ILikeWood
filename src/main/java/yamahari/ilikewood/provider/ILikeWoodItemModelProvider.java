package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.woodenitemtier.IWoodenItemTier;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Objects;
import java.util.stream.IntStream;

public final class ILikeWoodItemModelProvider extends ItemModelProvider {
    public ILikeWoodItemModelProvider(final DataGenerator generator, final ExistingFileHelper helper) {
        super(generator, Constants.MOD_ID, helper);
    }

    private void blockItem(final Block block, final String path) {
        final String woodType = ((IWooden) block).getWoodType().getName();
        this
            .getBuilder(Objects.requireNonNull(block.getRegistryName(), "Registry name was null.").getPath())
            .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(BLOCK_FOLDER, path, woodType))));
    }

    private void tieredItem(final Item item, final String path) {
        final String woodType = ((IWooden) item).getWoodType().getName();
        final IWoodenItemTier itemTier = ((IWoodenTieredItem) item).getWoodenItemTier();
        final String tier = itemTier.getName();
        final boolean isWood = ((IWoodenTieredItem) item).getWoodenItemTier().isWood();
        this
            .withExistingParent(Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath(),
                mcLoc(Util.toPath(ITEM_FOLDER, "handheld")))
            .texture("layer0",
                modLoc(Util.toPath(ITEM_FOLDER,
                    WoodenObjectType.STICK.toString(),
                    path + (itemTier.equals(VanillaWoodenItemTiers.NETHERITE) ? "/netherite" : ""),
                    woodType)))
            .texture("layer1", modLoc(Util.toPath(ITEM_FOLDER, path + (isWood ? "/wooden" : ""), tier)));
    }

    @Override
    protected void registerModels() {
        Util
            .getBlocksWith(WoodenObjectType.PANELS, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.PANELS.toString()));
        Util
            .getBlocksWith(WoodenObjectType.STAIRS, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block,
                Util.toPath(WoodenObjectType.PANELS.toString(), WoodenObjectType.STAIRS.toString())));
        Util
            .getBlocksWith(WoodenObjectType.SLAB, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block,
                Util.toPath(WoodenObjectType.PANELS.toString(), WoodenObjectType.SLAB.toString())));
        Util
            .getBlocksWith(WoodenObjectType.BARREL, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.BARREL.toString()));
        Util
            .getBlocksWith(WoodenObjectType.BOOKSHELF, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.BOOKSHELF.toString()));
        Util.getBlocksWith(WoodenObjectType.CHEST, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            this
                .getBuilder(Objects.requireNonNull(block.getRegistryName(), "Registry name was null").getPath())
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath("builtin", "entity"))))
                .texture("particle", ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getTexture())
                .transforms()
                .transform(ModelBuilder.Perspective.GUI)
                .rotation(30, 45, 0)
                .scale(0.625F)
                .end()
                .transform(ModelBuilder.Perspective.GROUND)
                .translation(0, 3, 0)
                .scale(0.25F)
                .end()
                .transform(ModelBuilder.Perspective.HEAD)
                .rotation(0, 180, 0)
                .scale(1.f)
                .end()
                .transform(ModelBuilder.Perspective.FIXED)
                .rotation(0, 180, 0)
                .scale(0.5F)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(75, 315, 0)
                .translation(0.F, 2.5F, 0.F)
                .scale(0.375F)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                .rotation(0, 315, 0)
                .scale(0.4F)
                .end()
                .end();
        });
        Util
            .getBlocksWith(WoodenObjectType.COMPOSTER, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.COMPOSTER.toString()));
        Util
            .getBlocksWith(WoodenObjectType.WALL, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG))
            .forEach(block -> this.blockItem(block, Util.toPath(WoodenObjectType.WALL.toString(), "inventory")));
        Util.getBlocksWith(WoodenObjectType.LADDER, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final String woodType = ((IWooden) block).getWoodType().getName();
            this
                .getBuilder(Objects.requireNonNull(block.getRegistryName(), "Registry name was null").getPath())
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
                .texture("layer0", modLoc(Util.toPath(BLOCK_FOLDER, WoodenObjectType.LADDER.toString(), woodType)));
        });
        Util
            .getBlocksWith(WoodenObjectType.TORCH, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.TORCH.toString()));
        Util
            .getBlocksWith(WoodenObjectType.CRAFTING_TABLE, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.CRAFTING_TABLE.toString()));
        Util
            .getBlocksWith(WoodenObjectType.SCAFFOLDING, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, Util.toPath(WoodenObjectType.SCAFFOLDING.toString(), "stable")));
        Util
            .getBlocksWith(WoodenObjectType.LECTERN, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block, WoodenObjectType.LECTERN.toString()));

        Util
            .getItemsWith(WoodenObjectType.STICK, Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(item -> this.singleTexture(Objects
                    .requireNonNull(item.getRegistryName(), "Registry name was null")
                    .getPath(),
                mcLoc(Util.toPath(ITEM_FOLDER, "handheld")),
                "layer0",
                modLoc(Util.toPath(ITEM_FOLDER,
                    WoodenObjectType.STICK.toString(),
                    ((IWooden) item).getWoodType().getName()))));
        Util
            .getTieredItemsWith(Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(item -> this.tieredItem(item, ((IWoodenTieredItem) item).getWoodenTieredObjectType().toString()));

        Util
            .getBlocksWith(WoodenObjectType.POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG))
            .forEach(block -> this.blockItem(block, Util.toPath(WoodenObjectType.POST.toString(), "inventory")));
        Util
            .getBlocksWith(WoodenObjectType.STRIPPED_POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG))
            .forEach(block -> this.blockItem(block,
                Util.toPath(WoodenObjectType.POST.toString(), "stripped", "inventory")));
        Util.getItemsWith(WoodenObjectType.BOW, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final String woodType = ((IWooden) item).getWoodType().getName();
            this
                .getBuilder(Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath())
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
                .texture("layer0", modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.BOW.toString(), woodType)))
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(-80.0F, 260.0F, -40.F)
                .translation(-1.0F, -2.0F, 2.5F)
                .scale(0.9F)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                .rotation(-80.0F, 280.0F, 40.F)
                .translation(-1.0F, -2.0F, 2.5F)
                .scale(0.9F)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                .rotation(0.0F, -90.0F, 25.F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                .rotation(0.0F, 90.0F, -25.F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.BOW.toString(), "pulling", "0")))))
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .predicate(mcLoc("pull"), 0.65F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.BOW.toString(), "pulling", "1")))))
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .predicate(mcLoc("pull"), 0.9F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.BOW.toString(), "pulling", "2")))))
                .end();

            IntStream
                .range(0, 3)
                .forEach(i -> this
                    .getBuilder(Util.toRegistryName(woodType,
                        WoodenObjectType.BOW.toString(),
                        "pulling",
                        Integer.toString(i)))
                    .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                        Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath()))))
                    .texture("layer0",
                        modLoc(Util.toPath(ITEM_FOLDER,
                            WoodenObjectType.BOW.toString(),
                            "pulling",
                            Integer.toString(i),
                            woodType))));

        });

        Util.getItemsWith(WoodenObjectType.CROSSBOW, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final String woodType = ((IWooden) item).getWoodType().getName();
            this
                .getBuilder(Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath())
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.CROSSBOW.toString(), "standby", woodType)))
                .transforms()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(-90.0F, 260.0F, -60.F)
                .translation(2.0F, 0.1F, -3.0F)
                .scale(0.9F)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
                .rotation(-90.0F, 0.0F, 30.F)
                .translation(2.0F, 0.1F, -3.0F)
                .scale(0.9F)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                .rotation(-90.0F, 0.0F, -55.F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                .rotation(-90.0F, 0.0F, 35.F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F)
                .end()
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "pulling", "0")))))
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .predicate(mcLoc("pull"), 0.58F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "pulling", "1")))))
                .end()
                .override()
                .predicate(mcLoc("pulling"), 1.0F)
                .predicate(mcLoc("pull"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "pulling", "2")))))
                .end()
                .override()
                .predicate(mcLoc("charged"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "arrow")))))
                .end()
                .override()
                .predicate(mcLoc("charged"), 1.0F)
                .predicate(mcLoc("firework"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "firework")))))
                .end();

            IntStream
                .range(0, 3)
                .forEach(i -> this
                    .getBuilder(Util.toRegistryName(woodType,
                        WoodenObjectType.CROSSBOW.toString(),
                        "pulling",
                        Integer.toString(i)))
                    .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                        Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath()))))
                    .texture("layer0",
                        modLoc(Util.toPath(ITEM_FOLDER,
                            WoodenObjectType.CROSSBOW.toString(),
                            "pulling",
                            Integer.toString(i),
                            woodType))));

            this
                .getBuilder(Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "arrow"))
                .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath()))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.CROSSBOW.toString(), "arrow", woodType)));

            this
                .getBuilder(Util.toRegistryName(woodType, WoodenObjectType.CROSSBOW.toString(), "firework"))
                .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Objects.requireNonNull(item.getRegistryName(), "Registry name was null").getPath()))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.CROSSBOW.toString(), "firework", woodType)));
        });

        Util.getItemsWith(WoodenObjectType.ITEM_FRAME, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
            this
                .getBuilder(Util.toRegistryName(woodType.getName(), WoodenObjectType.ITEM_FRAME.toString()))
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "generated"))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.ITEM_FRAME.toString(), woodType.getName())));
        });

        Util
            .getBedBlocksWith(Util.HAS_PLANKS.and(Util.HAS_SLAB))
            .forEach(block -> this.blockItem(block,
                Util.toPath(WoodenObjectType.BED.toString(),
                    "inventory",
                    ((WoodenBedBlock) block).getDyeColor().toString())));
        Util
            .getBlocksWith(WoodenObjectType.SAWMILL,
                Util.HAS_PLANKS.and(Util.HAS_SLAB).and(Util.HAS_LOG).and(Util.HAS_STRIPPED_LOG))
            .forEach(block -> this.blockItem(block, Util.toPath(WoodenObjectType.SAWMILL.toString(), "inventory")));

        Util.getItemsWith(WoodenObjectType.FISHING_ROD, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();

            this
                .getBuilder(item.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile(mcLoc(Util.toPath(ITEM_FOLDER, "handheld_rod"))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER, WoodenObjectType.FISHING_ROD.toString(), woodType.getName())))
                .override()
                .predicate(mcLoc("cast"), 1.0F)
                .model(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    Util.toRegistryName(item.getRegistryName().getPath(), "cast")))))
                .end();

            this
                .getBuilder(Util.toRegistryName(item.getRegistryName().getPath(), "cast"))
                .parent(new ModelFile.UncheckedModelFile(modLoc(Util.toPath(ITEM_FOLDER,
                    item.getRegistryName().getPath()))))
                .texture("layer0",
                    modLoc(Util.toPath(ITEM_FOLDER,
                        WoodenObjectType.FISHING_ROD.toString(),
                        "cast",
                        woodType.getName())));
        });
    }
}
