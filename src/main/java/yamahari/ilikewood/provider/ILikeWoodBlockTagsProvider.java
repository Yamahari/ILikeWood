package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ILikeWoodBlockTagsProvider extends BlockTagsProvider {
    public ILikeWoodBlockTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    private void registerTag(final ITag.INamedTag<Block> tag, final WoodenObjectType blocktype) {
        this.getOrCreateBuilder(tag).add(WoodenBlocks.getBlocks(blocktype).toArray(Block[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(ILikeWoodBlockTags.BARRELS, WoodenObjectType.BARREL);
        registerTag(ILikeWoodBlockTags.CHESTS, WoodenObjectType.CHEST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenObjectType.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenObjectType.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenObjectType.SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenObjectType.STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenObjectType.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenObjectType.POST);
        registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenObjectType.STRIPPED_POST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenObjectType.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenObjectType.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenObjectType.SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenObjectType.STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenObjectType.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenObjectType.POST);
        registerTag(ILikeWoodBlockTags.WALLS, WoodenObjectType.WALL);
        registerTag(BlockTags.WALLS, WoodenObjectType.WALL);
        registerTag(ILikeWoodBlockTags.LADDERS, WoodenObjectType.LADDER);
        registerTag(ILikeWoodBlockTags.TORCHES, WoodenObjectType.TORCH);
        registerTag(ILikeWoodBlockTags.WALL_TORCHES, WoodenObjectType.WALL_TORCH);
        registerTag(ILikeWoodBlockTags.CRAFTING_TABLES, WoodenObjectType.CRAFTING_TABLE);
        registerTag(ILikeWoodBlockTags.SCAFFOLDINGS, WoodenObjectType.SCAFFOLDING);
        registerTag(ILikeWoodBlockTags.LECTERNS, WoodenObjectType.LECTERN);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
