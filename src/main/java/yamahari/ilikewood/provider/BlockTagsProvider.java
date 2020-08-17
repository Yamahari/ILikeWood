package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import yamahari.ilikewood.data.tag.BlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodenObjectType;

public final class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {
    public BlockTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    private void registerTag(ITag.INamedTag<Block> tag, WoodenObjectType blocktype){
        this.func_240522_a_(tag).func_240534_a_(WoodenBlocks.getBlocks(blocktype).toArray(Block[]::new));
    }

    @Override
    protected void registerTags() {
       registerTag(BlockTags.BARRELS,WoodenObjectType.BARREL);
       registerTag(BlockTags.CHESTS,WoodenObjectType.CHEST);
       registerTag(BlockTags.COMPOSTER,WoodenObjectType.COMPOSTER);
       registerTag(BlockTags.BOOKSHELFS,WoodenObjectType.BOOKSHELF);
       registerTag(BlockTags.PANELS_SLABS,WoodenObjectType.SLAB);
       registerTag(BlockTags.PANELS_STAIRS,WoodenObjectType.STAIRS);
       registerTag(BlockTags.PANELS,WoodenObjectType.PANELS);
       registerTag(BlockTags.POSTS,WoodenObjectType.POST);
       registerTag(BlockTags.STRIPPED_POSTS,WoodenObjectType.STRIPPED_POST);
       registerTag(BlockTags.COMPOSTER,WoodenObjectType.COMPOSTER);
       registerTag(BlockTags.BOOKSHELFS,WoodenObjectType.BOOKSHELF);
       registerTag(BlockTags.PANELS_SLABS,WoodenObjectType.SLAB);
       registerTag(BlockTags.PANELS_STAIRS,WoodenObjectType.STAIRS);
       registerTag(BlockTags.PANELS,WoodenObjectType.PANELS);
       registerTag(BlockTags.POSTS,WoodenObjectType.POST);
       registerTag(BlockTags.WALLS,WoodenObjectType.WALL);
       registerTag(net.minecraft.tags.BlockTags.WALLS,WoodenObjectType.WALL);
       registerTag(BlockTags.LADDERS,WoodenObjectType.LADDER);
       registerTag(BlockTags.TORCHES,WoodenObjectType.TORCH);
       registerTag(BlockTags.WALL_TORCHES,WoodenObjectType.WALL_TORCH);
       registerTag(BlockTags.CRAFTING_TABLES,WoodenObjectType.CRAFTING_TABLE);
       registerTag(BlockTags.SCAFFOLDINGS,WoodenObjectType.SCAFFOLDING);
       registerTag(BlockTags.LECTERNS,WoodenObjectType.LECTERN);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
