package yamahari.ilikewood.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.Constants;

public final class ILikeWoodBlockTagsProvider extends BlockTagsProvider {
    public ILikeWoodBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final Tag.Named<Block> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Block[]::new));
        } else {
            this.tag(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(blockType).toArray(Block[]::new));
        }
    }

    @Override
    protected void addTags() {
        registerTag(ILikeWoodBlockTags.BARRELS, WoodenBlockType.BARREL);
        registerTag(ILikeWoodBlockTags.CHESTS, WoodenBlockType.CHEST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenBlockType.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenBlockType.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenBlockType.PANELS_SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenBlockType.PANELS_STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenBlockType.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenBlockType.POST);
        registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);
        registerTag(ILikeWoodBlockTags.WALLS, WoodenBlockType.WALL);
        registerTag(ILikeWoodBlockTags.LADDERS, WoodenBlockType.LADDER);
        registerTag(ILikeWoodBlockTags.TORCHES, WoodenBlockType.TORCH);
        registerTag(ILikeWoodBlockTags.WALL_TORCHES, WoodenBlockType.WALL_TORCH);
        registerTag(ILikeWoodBlockTags.CRAFTING_TABLES, WoodenBlockType.CRAFTING_TABLE);
        registerTag(ILikeWoodBlockTags.SCAFFOLDINGS, WoodenBlockType.SCAFFOLDING);
        registerTag(ILikeWoodBlockTags.LECTERNS, WoodenBlockType.LECTERN);
        registerTag(ILikeWoodBlockTags.BEDS, WoodenBlockType.WHITE_BED);
        registerTag(ILikeWoodBlockTags.SAWMILLS, WoodenBlockType.SAWMILL);
        registerTag(ILikeWoodBlockTags.SOUL_TORCHES, WoodenBlockType.SOUL_TORCH);
        registerTag(ILikeWoodBlockTags.WALL_SOUL_TORCHES, WoodenBlockType.WALL_SOUL_TORCH);
        registerTag(ILikeWoodBlockTags.CHAIRS, WoodenBlockType.CHAIR);
        registerTag(ILikeWoodBlockTags.TABLES, WoodenBlockType.TABLE);
        registerTag(ILikeWoodBlockTags.STOOLS, WoodenBlockType.STOOL);

        this.tag(Tags.Blocks.CHESTS).addTag(ILikeWoodBlockTags.CHESTS);
        this.tag(Tags.Blocks.CHESTS_WOODEN).addTag(ILikeWoodBlockTags.CHESTS);
        this.tag(Tags.Blocks.BARRELS).addTag(ILikeWoodBlockTags.BARRELS);
        this.tag(Tags.Blocks.BARRELS_WOODEN).addTag(ILikeWoodBlockTags.BARRELS);

        this.tag(BlockTags.WALLS).addTag(ILikeWoodBlockTags.WALLS);

        // noinspection unchecked
        this.tag(BlockTags.CLIMBABLE).addTags(ILikeWoodBlockTags.LADDERS, ILikeWoodBlockTags.SCAFFOLDINGS);

        // noinspection unchecked
        this
            .tag(BlockTags.PIGLIN_REPELLENTS)
            .addTags(ILikeWoodBlockTags.SOUL_TORCHES, ILikeWoodBlockTags.WALL_SOUL_TORCHES);

        // noinspection unchecked
        this
            .tag(BlockTags.MINEABLE_WITH_AXE)
            .addTags(ILikeWoodBlockTags.PANELS,
                ILikeWoodBlockTags.PANELS_SLABS,
                ILikeWoodBlockTags.PANELS_STAIRS,
                ILikeWoodBlockTags.BARRELS,
                ILikeWoodBlockTags.CHESTS,
                ILikeWoodBlockTags.COMPOSTER,
                ILikeWoodBlockTags.BOOKSHELFS,
                ILikeWoodBlockTags.WALLS,
                ILikeWoodBlockTags.LADDERS,
                ILikeWoodBlockTags.CRAFTING_TABLES,
                ILikeWoodBlockTags.SCAFFOLDINGS,
                ILikeWoodBlockTags.LECTERNS,
                ILikeWoodBlockTags.POSTS,
                ILikeWoodBlockTags.STRIPPED_POSTS,
                ILikeWoodBlockTags.BEDS,
                ILikeWoodBlockTags.SAWMILLS,
                ILikeWoodBlockTags.CHAIRS,
                ILikeWoodBlockTags.TABLES,
                ILikeWoodBlockTags.STOOLS);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
